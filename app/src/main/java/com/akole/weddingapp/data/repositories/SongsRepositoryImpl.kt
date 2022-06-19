package com.akole.weddingapp.data.repositories

import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.domain.repositories.SongsRepository
import com.akole.weddingapp.domain.usecases.GetSongsResponse
import com.akole.weddingapp.domain.usecases.SaveSongResponse
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class SongsRepositoryImpl @Inject constructor(): SongsRepository {
    private val db = Firebase.firestore
    private val songsCollection = db.collection("songs")

    override suspend fun getSongList(): Flow<GetSongsResponse> {
        return callbackFlow {
            trySend(GetSongsResponse.Loading)
            songsCollection
                .orderBy("name")
                .get()
                .addOnSuccessListener { response ->
                    if (response != null && !response.isEmpty) {
                        val songList: MutableList<Song> = mutableListOf()
                        val documents = response.documents
                        documents.forEach { document ->
                            document.toObject(Song::class.java)?.apply {
                                songList.add(this)
                            }
                        }
                        trySend(GetSongsResponse.Success(songList))
                        close()
                    } else {
                        trySend(GetSongsResponse.Success(emptyList()))
                        close()
                    }
                }
                .addOnFailureListener { exception ->
                    trySend(GetSongsResponse.Error(exception))
                    close()
                }
            awaitClose {
                close()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveSong(song: Song): Flow<SaveSongResponse> {
        return callbackFlow {
            songsCollection
                .add(song)
                .addOnSuccessListener {
                    trySend(SaveSongResponse.Success)
                    close()
                }
                .addOnFailureListener {
                    trySend(SaveSongResponse.Success)
                    close()
                }
            awaitClose {
                close()
            }
        }
    }
}
