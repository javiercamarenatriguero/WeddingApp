package com.akole.weddingapp.domain

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImages(): Flow<GetImagesResponse>

    fun saveImages(
        list: List<Uri>,
        index: Int = 0
    ): Flow<SaveImagesResponse>
}