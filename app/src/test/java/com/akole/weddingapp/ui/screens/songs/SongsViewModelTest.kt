package com.akole.weddingapp.ui.screens.songs

import com.akole.weddingapp.CoroutineTestRule
import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.domain.usecases.GetSongs
import com.akole.weddingapp.domain.usecases.GetSongsResponse
import com.akole.weddingapp.domain.usecases.SaveSong
import com.akole.weddingapp.domain.usecases.SaveSongResponse
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class SongsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Test
    fun `GIVEN a list of songs request WHEN the result is success THEN the ViewState is updated with songs`() {
        runTest {
            val saveSong: SaveSong = mockk()
            val getSongs: GetSongs = mockk()
            coEvery { getSongs() } returns flowOf(GetSongsResponse.Success(MOCK_SUCCESS_SONG_LIST))

            val viewModel = SongsViewModel(getSongs = getSongs, saveSong = saveSong)
            viewModel.loadSongList()
            Assert.assertEquals(MOCK_SUCCESS_SONG_LIST, viewModel.state.songList, )
            Assert.assertEquals(false, viewModel.state.isLoading)
            // Check TextFields' values are reset
            Assert.assertEquals("", viewModel.state.song,)
            Assert.assertEquals("", viewModel.state.artist)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of songs request WHEN the result is an exception THEN the ViewState is updated with no songs`() {
        runTest {
            val saveSong: SaveSong = mockk()
            val getSongs: GetSongs = mockk()
            coEvery { getSongs() } returns flowOf(GetSongsResponse.Error(Exception()))

            val viewModel = SongsViewModel(getSongs = getSongs, saveSong = saveSong)
            viewModel.loadSongList()
            Assert.assertEquals(emptyList<Song>(), viewModel.state.songList)
            Assert.assertEquals(false, viewModel.state.isLoading)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of songs request WHEN the result is as loading THEN the ViewState is updated with no songs and loading state`() {
        runTest {
            val saveSong: SaveSong = mockk()
            val getSongs: GetSongs = mockk()
            coEvery { getSongs() } returns flowOf(GetSongsResponse.Loading)

            val viewModel = SongsViewModel(getSongs = getSongs, saveSong = saveSong)
            viewModel.loadSongList()
            Assert.assertEquals(emptyList<Song>(), viewModel.state.songList)
            Assert.assertEquals(true, viewModel.state.isLoading)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a song to upload request WHEN the result is success THEN the ViewState is updated with dialog shown`() {
        runTest {
            val saveSong: SaveSong = mockk()
            val getSongs: GetSongs = mockk()
            coEvery { saveSong(any()) } returns flowOf(SaveSongResponse.Success)
            // It is call once the song has been saved
            coEvery { getSongs() } returns flowOf(GetSongsResponse.Loading)

            val viewModel = SongsViewModel(getSongs = getSongs, saveSong = saveSong)
            viewModel.uploadSong(MOCK_UPLOADED_SONG)
            // Loading process to true because of getSongs() call
            Assert.assertEquals(true, viewModel.state.isLoading)
            Assert.assertEquals(true, viewModel.state.isDialogShown)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a song to upload request WHEN the result is as loading THEN the ViewState is updated with loading state as true`() {
        runTest {
            val saveSong: SaveSong = mockk()
            val getSongs: GetSongs = mockk()
            coEvery { saveSong(any()) } returns flowOf(SaveSongResponse.Loading)

            val viewModel = SongsViewModel(getSongs = getSongs, saveSong = saveSong)
            viewModel.uploadSong(MOCK_UPLOADED_SONG)
            Assert.assertEquals(true, viewModel.state.isLoading)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a song to upload request WHEN the result is an exception THEN the ViewState the loading state is false`() {
        runTest {
            val saveSong: SaveSong = mockk()
            val getSongs: GetSongs = mockk()
            coEvery { saveSong(any()) } returns flowOf(SaveSongResponse.Error(Exception()))

            val viewModel = SongsViewModel(getSongs = getSongs, saveSong = saveSong)
            viewModel.uploadSong(MOCK_UPLOADED_SONG)
            Assert.assertEquals(false, viewModel.state.isLoading)
            clearAllMocks()
        }
    }

    companion object {
        private val MOCK_SUCCESS_SONG_LIST = listOf(
            Song("name1", "artist1"),
            Song("name2", "artist2"),
        )

        private val MOCK_UPLOADED_SONG = Song("name", "artist")
    }
}
