package com.akole.weddingapp.data.repositories

import com.akole.weddingapp.data.models.Song
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object RepositoryImpl: Repository {
    private val db = Firebase.firestore
    private val songsCollection = db.collection("songs")
    private var songList: MutableList<Song> = mutableListOf()
    override fun getSongList() = songList

    fun setDataListener(listener: OnSuccessListener<in QuerySnapshot>) {
        songsCollection.get().addOnSuccessListener(listener)
    }

    override fun saveSong(song: Song) {
        songsCollection.add(song)
    }



}