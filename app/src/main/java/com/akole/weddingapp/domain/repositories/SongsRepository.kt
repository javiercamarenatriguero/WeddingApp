package com.akole.weddingapp.domain.repositories

import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.domain.usecases.GetSongsResponse
import com.akole.weddingapp.domain.usecases.SaveSongResponse
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    suspend fun getSongList(): Flow<GetSongsResponse>
    suspend fun saveSong(song: Song): Flow<SaveSongResponse>
}
