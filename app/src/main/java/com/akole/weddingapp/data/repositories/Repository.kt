package com.akole.weddingapp.data.repositories

import com.akole.weddingapp.data.models.Song

interface Repository {
    fun getSongList(): List<Song>
    fun saveSong(song: Song)
}