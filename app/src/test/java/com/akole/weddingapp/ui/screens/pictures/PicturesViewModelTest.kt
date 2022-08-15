package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import com.akole.weddingapp.CoroutineTestRule
import com.akole.weddingapp.domain.usecases.*
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class PicturesViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Test
    fun `GIVEN a list of images request WHEN the result is success THEN the ViewState is updated with images`() {
        runTest {
            val saveImages: SaveImages = mockk()
            val getImages: GetImages = mockk()
            coEvery { getImages() } returns flowOf(GetImagesResponse.Success(MOCK_SUCCESS_IMAGES_URI_LIST))

            val viewModel = PicturesViewModel(getImages = getImages, saveImages = saveImages)
            viewModel.loadImages()
            Assert.assertEquals(MOCK_SUCCESS_IMAGES_URI_LIST, viewModel.state.imageUrlList)
            Assert.assertEquals(false, viewModel.state.isCollectionLoading)
            Assert.assertEquals(false, viewModel.state.isCollectionError)
            coVerify(exactly = 1) { getImages() }
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images request WHEN the result is an exception THEN the ViewState is updated with no images`() {
        runTest {
            val saveImages: SaveImages = mockk()
            val getImages: GetImages = mockk()
            coEvery { getImages() } returns flowOf(GetImagesResponse.Error(Exception()))

            val viewModel = PicturesViewModel(getImages = getImages, saveImages = saveImages)
            viewModel.loadImages()
            Assert.assertEquals(emptyList<Uri>(), viewModel.state.imageUrlList)
            Assert.assertEquals(false, viewModel.state.isCollectionLoading)
            Assert.assertEquals(true, viewModel.state.isCollectionError)
            coVerify(exactly = 1) { getImages() }
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images request WHEN the result is as loading THEN the ViewState is updated with no images and loading state`() {
        runTest {
            val saveImages: SaveImages = mockk()
            val getImages: GetImages = mockk()
            coEvery { getImages() } returns flowOf(GetImagesResponse.Loading)

            val viewModel = PicturesViewModel(getImages = getImages, saveImages = saveImages)
            viewModel.loadImages()
            Assert.assertEquals(emptyList<Uri>(), viewModel.state.imageUrlList)
            Assert.assertEquals(true, viewModel.state.isCollectionLoading)
            Assert.assertEquals(false, viewModel.state.isCollectionError)
            coVerify(exactly = 1) { getImages() }
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images to upload request WHEN the result is success THEN the ViewState is updated`() {
        runTest {
            val saveImages: SaveImages = mockk()
            val getImages: GetImages = mockk()
            coEvery { saveImages(any()) } returns flowOf(SaveImagesResponse.Success)
            // It is call once the images has been saved
            coEvery { getImages() } returns flowOf(GetImagesResponse.Loading)

            val viewModel = PicturesViewModel(getImages = getImages, saveImages = saveImages)
            viewModel.uploadImages(MOCK_SUCCESS_IMAGES_URI_LIST)
            Assert.assertEquals(emptyList<Uri>(), viewModel.state.imageUrlList)
            Assert.assertEquals(false, viewModel.state.isUploadingImagesLoading)
            Assert.assertEquals(0, viewModel.state.uploadingProgress)
            // Loading process to true because of getImages() call
            Assert.assertEquals(true, viewModel.state.isCollectionLoading)
            coVerify(exactly = 1) { saveImages(any()) }
            coVerify(exactly = 1) { getImages() }
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images to upload request WHEN the result is as loading THEN the ViewState is updated with loading state as true`() {
        runTest {
            val saveImages: SaveImages = mockk()
            val getImages: GetImages = mockk()
            coEvery { saveImages(any()) } returns flowOf(SaveImagesResponse
                .Loading(MOCK_LOADING_IMAGE_INDEX)
            )

            val viewModel = PicturesViewModel(getImages = getImages, saveImages = saveImages)
            viewModel.uploadImages(MOCK_SUCCESS_IMAGES_URI_LIST)
            Assert.assertEquals(emptyList<Uri>(), viewModel.state.imageUrlList)
            Assert.assertEquals(false, viewModel.state.isUploadingImagesLoading)
            Assert.assertEquals(MOCK_LOADING_IMAGE_INDEX, viewModel.state.uploadingProgress)
            Assert.assertEquals(false, viewModel.state.isCollectionLoading)
            coVerify(exactly = 1) { saveImages(any()) }
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images to upload request WHEN the result is an exception THEN the ViewState the loading state is false`() {
        runTest {
            val saveImages: SaveImages = mockk()
            val getImages: GetImages = mockk()
            coEvery { saveImages(any()) } returns flowOf(SaveImagesResponse
                .Error(Exception())
            )

            val viewModel = PicturesViewModel(getImages = getImages, saveImages = saveImages)
            viewModel.uploadImages(MOCK_SUCCESS_IMAGES_URI_LIST)
            Assert.assertEquals(emptyList<Uri>(), viewModel.state.imageUrlList)
            Assert.assertEquals(false, viewModel.state.isUploadingImagesLoading)
            Assert.assertEquals(0, viewModel.state.uploadingProgress)
            Assert.assertEquals(false, viewModel.state.isCollectionLoading)
            coVerify(exactly = 1) { saveImages(any()) }
            clearAllMocks()
        }
    }

    companion object {
        private val MOCK_SUCCESS_IMAGES_URI_LIST = listOf(
            Uri.EMPTY,
            Uri.EMPTY,
        )
        private const val MOCK_LOADING_IMAGE_INDEX = 2
    }
}
