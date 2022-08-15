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
class SaveImagesTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Test
    fun `GIVEN a list of images saving process WHEN the result is success THEN the response is Success`() {
        runTest {
            val imagesRepository: ImagesRepository = mockk()
            coEvery { imagesRepository.saveImages(any()) } returns flowOf(SaveImagesResponse.Success)

            val saveImages = SaveImages(imagesRepository = imagesRepository)
            val response: SaveImagesResponse = saveImages(listOf()).single()
            Assert.assertSame(response, SaveImagesResponse.Success)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images saving process request WHEN the result is loading THEN the response is Loading`() {
        runTest {
            val imagesRepository: ImagesRepository = mockk()
            val loadingResponse = SaveImagesResponse.Loading(0)
            coEvery { imagesRepository.saveImages(any()) } returns flowOf(loadingResponse)

            val saveImages = SaveImages(imagesRepository = imagesRepository)
            val response: SaveImagesResponse = saveImages(listOf()).single()
            Assert.assertSame(response, loadingResponse)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images saving process WHEN the result is an error THEN the response is Error`() {
        runTest {
            val imagesRepository: ImagesRepository = mockk()
            val exception = RuntimeException(MOCK_ERROR_MESSAGE)
            coEvery { imagesRepository.saveImages(any()) } returns flowOf(SaveImagesResponse.Error(exception))

            val saveImages = SaveImages(imagesRepository = imagesRepository)
            val response: SaveImagesResponse = saveImages(listOf()).single()
            Assert.assertEquals(response, SaveImagesResponse.Error(exception))
            clearAllMocks()
        }
    }

    companion object {
        private const val MOCK_ERROR_MESSAGE = "Error description"
    }
}
