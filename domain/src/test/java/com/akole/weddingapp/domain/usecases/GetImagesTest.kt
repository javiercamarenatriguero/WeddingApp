package com.akole.weddingapp.domain.usecases

import com.akole.weddingapp.domain.CoroutineTestRule
import com.akole.weddingapp.domain.repositories.ImagesRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

@OptIn(ExperimentalCoroutinesApi::class)
class GetImagesTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Test
    fun `GIVEN a list of images request WHEN the result is success THEN the response is Success with images`() {
        runTest {
            val imagesRepository: ImagesRepository = mockk()
            coEvery { imagesRepository.getImages() } returns flowOf(GetImagesResponse.Success(MOCK_SUCCESS_IMAGES_URI_LIST))

            val getImages = GetImages(imagesRepository = imagesRepository)
            val response: GetImagesResponse = getImages().single()
            val imageList = (response as GetImagesResponse.Success).images
            Assert.assertEquals(MOCK_SUCCESS_IMAGES_URI_LIST, imageList)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images request WHEN the result is loading THEN the response is Loading`() {
        runTest {
            val imagesRepository: ImagesRepository = mockk()
            coEvery { imagesRepository.getImages() } returns flowOf(GetImagesResponse.Loading)

            val getImages = GetImages(imagesRepository = imagesRepository)
            val response: GetImagesResponse = getImages().single()
            Assert.assertSame(response, GetImagesResponse.Loading)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images request WHEN the result is an error THEN the response is Error`() {
        runTest {
            val imagesRepository: ImagesRepository = mockk()
            val exception = RuntimeException(MOCK_ERROR_MESSAGE)
            coEvery { imagesRepository.getImages() } returns flowOf(GetImagesResponse.Error(exception))

            val getImages = GetImages(imagesRepository = imagesRepository)
            val response: GetImagesResponse = getImages().single()
            Assert.assertEquals(response, GetImagesResponse.Error(exception))
            clearAllMocks()
        }
    }

    companion object {
        private val MOCK_SUCCESS_IMAGES_URI_LIST = listOf(
            "",
            "",
        )
        private const val MOCK_ERROR_MESSAGE = "Error description"
    }
}
