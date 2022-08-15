package com.akole.weddingapp.domain.usecases

import com.akole.weddingapp.domain.repositories.ImagesRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class GetImages(
    private val imagesRepository: ImagesRepository
) {
    suspend operator fun invoke(): Flow<GetImagesResponse> =
        imagesRepository.getImages()
}

sealed class GetImagesResponse {
    object Loading: GetImagesResponse()
    data class Success(val images: List<String>): GetImagesResponse()
    data class Error(val exception: Exception): GetImagesResponse()
}