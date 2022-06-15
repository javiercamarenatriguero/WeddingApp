package com.akole.weddingapp.domain.usecases

import android.net.Uri
import com.akole.weddingapp.domain.repositories.ImagesRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class SaveImages(
    private val imagesRepository: ImagesRepository
) {
    suspend operator fun invoke(list: List<Uri>): Flow<SaveImagesResponse> =
        imagesRepository.saveImages(list)
}

sealed class SaveImagesResponse {
    data class Loading(val position: Int): SaveImagesResponse()
    object Success: SaveImagesResponse()
    data class Error(val exception: Exception): SaveImagesResponse()
}