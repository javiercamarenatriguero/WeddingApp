import android.net.Uri
import com.akole.weddingapp.domain.GetImagesResponse
import com.akole.weddingapp.domain.ImagesRepository
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(): ImagesRepository {
    private val storage = Firebase.storage
    private val storageRef = storage.reference

    override fun getImageList(
        onFailureListener: OnFailureListener,
        onSuccessListener: OnSuccessListener<in ListResult>
    ) {
        val listRef = storage.reference.child("images")

        listRef.listAll()
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override fun saveImages(
        list: List<Uri>,
        index: Int,
        onFailureListener: OnFailureListener,
        onSuccessListener: OnSuccessListener<in UploadTask.TaskSnapshot>,
        onProgressListener: (progress: Int) -> Unit
    ) {
        if (list.isNotEmpty()) {
            val uri = list[index]
            val fileRef = storageRef.child("images/${uri.lastPathSegment?.substringAfterLast('/')}")
            onProgressListener(index + 1)
            var uploadTask = fileRef.putFile(uri)
            uploadTask
                .addOnFailureListener(onFailureListener)
                .addOnSuccessListener {
                    if (index == list.size - 1) {
                        // Finish loading images
                        onSuccessListener.onSuccess(it)
                    } else {
                        // Upload next image
                        saveImages(list, index + 1, onFailureListener, onSuccessListener, onProgressListener)
                    }
                }
        }
    }

    override fun getImages(): Flow<GetImagesResponse> {
        val listRef = storage.reference.child("images")

        return callbackFlow {
            trySend(
                GetImagesResponse.Loading
            )
            listRef.listAll()
                .addOnSuccessListener { itemList ->
                    GlobalScope.launch {
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
                            list.add(it.result)
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