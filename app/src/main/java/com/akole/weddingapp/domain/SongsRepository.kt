package com.akole.weddingapp.domain

import com.akole.weddingapp.data.models.Song
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot

interface SongsRepository {
    fun getSongList(
        onSuccessListener: OnSuccessListener<in QuerySnapshot>,
        onFailureListener: OnFailureListener
    )
    fun saveSong(song: Song)
}