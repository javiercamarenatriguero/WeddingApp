package com.akole.weddingapp.domain.repositories

import android.net.Uri
import com.akole.weddingapp.domain.usecases.GetImagesResponse
import com.akole.weddingapp.domain.usecases.SaveImagesResponse
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    suspend fun getImages(): Flow<GetImagesResponse>

    suspend fun saveImages(
        list: List<Uri>,
        index: Int = 0
    ): Flow<SaveImagesResponse>
}