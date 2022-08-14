package com.akole.weddingapp.ui.screens.pictures

import android.content.Context
import android.net.Uri
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.akole.weddingapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PicturesScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun show_empty_list_message_when_view_is_loaded(): Unit = with(composeTestRule) {
        setContent {
            PicturesScreenContent(
                viewState = MOCK_INITIAL_STATE,
                onEventHandler = {}
            )
        }
        onNodeWithText(context.getString(R.string.pictures_empty_message)).assertExists()
        onNodeWithText(context.getString(R.string.submit_photo)).assertExists()
        onNodeWithText(context.getString(R.string.submit_photo)).assertIsNotEnabled()
    }

    @Test
    fun add_photo_button_enable_when_service_is_ready(): Unit = with(composeTestRule) {
        var wasCalled = false
        setContent {
            PicturesScreenContent(
                viewState = MOCK_INITIAL_STATE.copy(
                    isPhotoServiceEnabled = true
                ),
                onEventHandler = { wasCalled = it == PicturesViewModel.ViewEvent.AddPhotosClicked }
            )
        }
        onNodeWithText(context.getString(R.string.submit_photo)).assertExists()
        onNodeWithText(context.getString(R.string.submit_photo)).assertIsEnabled()
        onNodeWithText(context.getString(R.string.submit_photo)).performClick()
        assert(value = wasCalled)
    }

    @Test
    fun show_loading_view_when_is_loading_collection(): Unit = with(composeTestRule) {
        setContent {
            PicturesScreenContent(
                viewState = MOCK_INITIAL_STATE.copy(
                    isCollectionLoading = true
                ),
                onEventHandler = {}
            )
        }
        onNodeWithTag(LOADING_COLLECTION_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun show_loading_error_dialog_view_when_loading_collection_fails(): Unit = with(composeTestRule) {
        setContent {
            PicturesScreenContent(
                viewState = MOCK_INITIAL_STATE.copy(
                    isCollectionError = true
                ),
                onEventHandler = {}
            )
        }
        onNodeWithText(context.getString(R.string.pictures_error_message)).assertIsDisplayed()
    }

    @Test
    fun show_uploading_photo_progress_info_view_when_uploading_photo(): Unit = with(composeTestRule) {
        setContent {
            PicturesScreenContent(
                viewState = MOCK_INITIAL_STATE.copy(
                    isUploadingImagesLoading = true,
                    uploadingImages = 2,
                    uploadingProgress = 1,
                    imageUrlList = listOf(Uri.EMPTY, Uri.EMPTY)
                ),
                onEventHandler = {}
            )
        }
        onNodeWithTag(UPLOADING_PHOTO_PROGRESS_TEST_TAG).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.pictures_uploading_progress_text) + " 1 / 2").assertIsDisplayed()
    }

    @Test
    fun show_photo_dialog_info_view_and_hide_after_clicking_on_button(): Unit = with(composeTestRule) {
        var wasCalled = false
        setContent {
            PicturesScreenContent(
                viewState = MOCK_INITIAL_STATE.copy(
                    isShownPictureDialog = true,
                    pictureUri = Uri.EMPTY
                ),
                onEventHandler = { wasCalled = it == PicturesViewModel.ViewEvent.DismissDialog }
            )
        }
        onNodeWithContentDescription(context.getString(R.string.picture_photo_dialog)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.picture_close_button_text)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.picture_close_button_text)).assertIsEnabled()
        onNodeWithText(context.getString(R.string.picture_close_button_text)).performClick()
        assert(value = wasCalled)
    }

    companion object {
        private val MOCK_INITIAL_STATE = ViewState()
    }
}