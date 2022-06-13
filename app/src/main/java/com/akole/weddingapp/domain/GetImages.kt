package com.akole.weddingapp.domain

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class GetImages(
    private val imagesRepository: ImagesRepository
) {
    operator fun invoke(): Flow<GetImagesResponse> =
        imagesRepository.getImages()
}

sealed class GetImagesResponse {
    object Loading: GetImagesResponse()
    data class Success(val images: List<Uri>): GetImagesResponse()
    data class Error(val exception: Exception): GetImagesResponse()
}