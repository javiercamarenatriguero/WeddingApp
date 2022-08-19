package com.akole.weddingapp.ui.screens.songs

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.akole.weddingapp.R
import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.ui.theme.WeddingAppTheme
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
            WeddingAppTheme {
                SongsScreenContent(
                    viewState = MOCK_INITIAL_STATE,
                    onEventHandler = {}
                )
            }
        }
        onNodeWithTag(SongsScreenTestTags.SONG_TEXT_FIELD).assertExists()
        onNodeWithTag(SongsScreenTestTags.SONG_TEXT_FIELD).assertIsEnabled()
        onNodeWithTag(SongsScreenTestTags.SONG_TEXT_FIELD).assertTextContains("")

        onNodeWithTag(SongsScreenTestTags.ARTIST_TEXT_FIELD).assertExists()
        onNodeWithTag(SongsScreenTestTags.ARTIST_TEXT_FIELD).assertIsEnabled()
        onNodeWithTag(SongsScreenTestTags.ARTIST_TEXT_FIELD).assertTextContains("")

        onNodeWithText(context.getString(R.string.submit_song)).assertIsNotEnabled()
    }

    @Test
    fun show_add_song_button_enable_when_text_fields_are_filled(): Unit = with(composeTestRule) {
        var wasCalled = false
        setContent {
            WeddingAppTheme {
                SongsScreenContent(
                    viewState = MOCK_INITIAL_STATE.copy(
                        song = MOCK_SONG_NAME,
                        artist = MOCK_SINGER_NAME,
                        isButtonReady = true
                    ),
                    onEventHandler = { wasCalled = it == SongsViewModel.ViewEvent.AddClicked }
                )
            }
        }
        onNodeWithText(context.getString(R.string.submit_song)).assertHasClickAction()
        onNodeWithText(context.getString(R.string.submit_song)).performClick()
        assert(value = wasCalled)
    }

    @Test
    fun show_a_list_of_songs_and_scroll_to_index(): Unit = with(composeTestRule) {
        setContent {
            WeddingAppTheme {
                SongsScreenContent(
                    viewState = MOCK_INITIAL_STATE.copy(
                        songList = List(MOCK_NUMBER_SONGS) { Song(MOCK_SONG_NAME) }
                    ),
                    onEventHandler = {}
                )
            }
        }
        onNodeWithTag(SongsScreenTestTags.SONGS_COLUMN).assertExists()
        onNodeWithTag(SongsScreenTestTags.SONGS_COLUMN).performScrollToIndex(MOCK_NUMBER_SONGS - 1)
    }

    @Test
    fun show_loading_view(): Unit = with(composeTestRule) {
        setContent {
            WeddingAppTheme {
                SongsScreenContent(
                    viewState = MOCK_INITIAL_STATE.copy(
                        isLoading = true
                    ),
                    onEventHandler = {}
                )
            }
        }
        onNodeWithTag(SongsScreenTestTags.LOADING_VIEW).assertExists()
    }

    @Test
    fun show_success_dialog(): Unit = with(composeTestRule) {
        setContent {
            WeddingAppTheme {
                SongsScreenContent(
                    viewState = MOCK_INITIAL_STATE.copy(
                        isDialogShown = true
                    ),
                    onEventHandler = {}
                )
            }
        }
        onNodeWithTag(SongsScreenTestTags.SUCCESS_DIALOG).assertExists()
        onNodeWithTag(SongsScreenTestTags.SUCCESS_DIALOG).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.songs_item_saved_message)).assertIsDisplayed()
    }

    companion object {
        private const val MOCK_SONG_NAME = "Master of Puppets"
        private const val MOCK_SINGER_NAME = "Metallica"
        private const val MOCK_NUMBER_SONGS = 5

        private val MOCK_INITIAL_STATE = ViewState()
    }
}