package com.akole.weddingapp.domain.usecases

import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.domain.repositories.SongsRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class SaveSong(
    private val songsRepository: SongsRepository
) {
    suspend operator fun invoke(song: Song): Flow<SaveSongResponse> =
        songsRepository.saveSong(song)
}

sealed class SaveSongResponse {
    object Loading: SaveSongResponse()
    object Success: SaveSongResponse()
    data class Error(val exception: Exception): SaveSongResponse()
}