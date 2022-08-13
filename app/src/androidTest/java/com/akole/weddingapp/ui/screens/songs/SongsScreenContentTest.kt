package com.akole.weddingapp.ui.screens.songs

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.akole.weddingapp.R
import com.akole.weddingapp.domain.models.Song
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongsScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun show_empty_text_fields_and_empty_list_on_initial_state(): Unit = with(composeTestRule) {
        setContent {
            SongsScreenContent(
                viewState = MOCK_INITIAL_STATE,
                onEventHandler = {}
            )
        }
        onNodeWithTag(SONG_TEXT_FIELD_TEST_TAG).assertExists()
        onNodeWithTag(SONG_TEXT_FIELD_TEST_TAG).assertIsEnabled()
        onNodeWithTag(SONG_TEXT_FIELD_TEST_TAG).assertTextContains("")

        onNodeWithTag(ARTIST_TEXT_FIELD_TEST_TAG).assertExists()
        onNodeWithTag(ARTIST_TEXT_FIELD_TEST_TAG).assertIsEnabled()
        onNodeWithTag(ARTIST_TEXT_FIELD_TEST_TAG).assertTextContains("")

        onNodeWithText(context.getString(R.string.submit_song)).assertIsNotEnabled()
    }

    @Test
    fun show_add_song_button_enable_when_text_fields_are_filled(): Unit = with(composeTestRule) {
        var wasCalled = false
        setContent {
            SongsScreenContent(
                viewState = MOCK_TEXT_FIELD_COMPLETED_STATE,
                onEventHandler = { wasCalled = it == SongsViewModel.ViewEvent.AddClicked }
            )
        }
        onNodeWithText(context.getString(R.string.submit_song)).assertHasClickAction()
        onNodeWithText(context.getString(R.string.submit_song)).performClick()
        assert(value = wasCalled)
    }

    @Test
    fun show_a_list_of_songs_and_scroll_to_index(): Unit = with(composeTestRule) {
        setContent {
            SongsScreenContent(
                viewState = MOCK_SONG_LIST_STATE,
                onEventHandler = {}
            )
        }
        onNodeWithTag(SONGS_COLUMN_TEST_TAG).assertExists()
        onNodeWithTag(SONGS_COLUMN_TEST_TAG).performScrollToIndex(MOCK_SONG_LIST_STATE.songList.size - 1)
    }

    @Test
    fun show_loading_view(): Unit = with(composeTestRule) {
        setContent {
            SongsScreenContent(
                viewState = MOCK_LOADING_STATE,
                onEventHandler = {}
            )
        }
        onNodeWithTag(LOADING_VIEW_TEST_TAG).assertExists()
        onNodeWithTag(LOADING_VIEW_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun show_success_dialog(): Unit = with(composeTestRule) {
        setContent {
            SongsScreenContent(
                viewState = MOCK_SUCCESS_DIALOG_STATE,
                onEventHandler = {}
            )
        }
        onNodeWithTag(SUCCESS_DIALOG_TEST_TAG).assertExists()
        onNodeWithTag(SUCCESS_DIALOG_TEST_TAG).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.songs_item_saved_message)).assertIsDisplayed()
    }

    companion object {
        private const val MOCK_SONG_NAME = "Master of Puppets"
        private const val MOCK_SINGER_NAME = "Metallica"

        private val MOCK_INITIAL_STATE = SongsViewModel.UiState()
        private val MOCK_TEXT_FIELD_COMPLETED_STATE = SongsViewModel.UiState(
            song = MOCK_SONG_NAME,
            artist = MOCK_SINGER_NAME,
            isButtonReady = true
        )
        private val MOCK_SONG_LIST_STATE = SongsViewModel.UiState(
            songList = List(5) { Song(MOCK_SONG_NAME) }
        )
        private val MOCK_LOADING_STATE = SongsViewModel.UiState(
            isLoading = true
        )
        private val MOCK_SUCCESS_DIALOG_STATE = SongsViewModel.UiState(
            isDialogShown = true
        )
    }
}