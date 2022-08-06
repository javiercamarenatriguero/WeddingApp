package com.akole.weddingapp.domain.usecases

import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.domain.repositories.SongsRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class GetSongs(
    private val songsRepository: SongsRepository
) {
    suspend operator fun invoke(): Flow<GetSongsResponse> =
        songsRepository.getSongList()
}

sealed class GetSongsResponse {
    object Loading: GetSongsResponse()
    data class Success(val songs: List<Song>): GetSongsResponse()
    data class Error(val exception: Exception): GetSongsResponse()
}