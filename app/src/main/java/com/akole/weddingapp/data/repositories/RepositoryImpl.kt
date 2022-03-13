package com.akole.weddingapp.data.repositories

import androidx.compose.ui.text.capitalize
import com.akole.weddingapp.data.models.Song
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

object RepositoryImpl: Repository {
    private val db = Firebase.firestore
    private val songsCollection = db.collection("songs")

    override fun getSongList(
        onSuccessListener: OnSuccessListener<in QuerySnapshot>,
        onFailureListener: OnFailureListener
    ) {
        songsCollection
            .orderBy("name")
            .get()
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override fun saveSong(song: Song) {
        songsCollection.add(song)
    }
}

fun String.upperAsTitle(): String? {
    return this
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}