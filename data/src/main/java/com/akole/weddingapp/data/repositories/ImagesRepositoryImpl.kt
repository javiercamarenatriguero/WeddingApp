package com.akole.weddingapp.data.repositories

import android.net.Uri
import com.akole.weddingapp.domain.usecases.GetImagesResponse
import com.akole.weddingapp.domain.repositories.ImagesRepository
import com.akole.weddingapp.domain.usecases.SaveImagesResponse
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(): ImagesRepository {
    private val storage = Firebase.storage
    private val storageRef = storage.reference

    override suspend fun saveImages(
        list: List<Uri>
    ): Flow<SaveImagesResponse> {
         return callbackFlow {
             list.forEachIndexed { index, uri ->
                 trySend(SaveImagesResponse.Loading(index + 1))
                 saveImage(uri = uri).collect { response ->
                     when (response) {
                         is SaveImageResponse.Success -> {
                             // Check if it is the latest image to upload
                             if (index == list.size - 1) {
                                 trySend(SaveImagesResponse.Success)
                                 close()
                             }
                         }
                         is SaveImageResponse.Error -> {
                             trySend(SaveImagesResponse.Error(response.exception))
                             close()
                         }
                     }
                }
            }
             awaitClose {
                 close()
             }
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun saveImage(uri: Uri): Flow<SaveImageResponse> {
        val fileRef = storageRef.child("images/${uri.lastPathSegment?.substringAfterLast('/')}")
        return callbackFlow {
            val uploadTask = fileRef.putFile(uri)
            uploadTask
                .addOnFailureListener { exception ->
                    trySend(SaveImageResponse.Error(exception))
                    close()

                }
                .addOnSuccessListener {
                    trySend(SaveImageResponse.Success)
                    close()
                }
            awaitClose {
                close()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getImages(): Flow<GetImagesResponse> {
        val listRef = storage.reference.child("images")
        return callbackFlow {
            trySend(
                GetImagesResponse.Loading
            )
            listRef.listAll()
                .addOnSuccessListener { itemList ->
                    CoroutineScope(Dispatchers.IO).launch {
                        trySend(
                            GetImagesResponse.Success(
                                getImagesUriList(itemList)
                            )
                        )
                        close()
                    }
                }
                .addOnFailureListener { exception ->
                    trySend(
                        GetImagesResponse.Error(exception)
                    )
                    close()
                }
            awaitClose {
                close()
            }
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getImagesUriList(itemList: ListResult): MutableList<Uri> {
        val list = mutableListOf<Uri>()
        var index = 0
        itemList.items.asFlow()
            .flatMapMerge {
                callbackFlow<Uri> {
                    it.downloadUrl
                        .addOnCompleteListener {
                            index++
                            it.result?.let { uri -> list.add(uri) }
                            close()
                        }
                        .addOnFailureListener {
                            index++
                            close()
                        }
                        .addOnCanceledListener {
                            index++
                            close()
                        }
                    awaitClose {
                        close()
                    }
                }.buffer(Channel.UNLIMITED)
            }
            .catch {
                index++
            }
            .collect { uri ->
                list.add(uri)
            }
        return list
    }
}

private sealed class SaveImageResponse {
    object Success: SaveImageResponse()
    data class Error(val exception: Exception): SaveImageResponse()
}