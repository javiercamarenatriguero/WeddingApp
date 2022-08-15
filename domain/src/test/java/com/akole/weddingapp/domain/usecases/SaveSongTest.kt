package com.akole.weddingapp.domain.usecases

import com.akole.weddingapp.domain.CoroutineTestRule
import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.domain.repositories.SongsRepository
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
class SaveSongTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Test
    fun `GIVEN a song saving process WHEN the result is success THEN the response is Success`() {
        runTest {
            val songsRepository: SongsRepository = mockk()
            coEvery { songsRepository.saveSong(any()) } returns flowOf(SaveSongResponse.Success)

            val saveSong = SaveSong(songsRepository = songsRepository)
            val response: SaveSongResponse = saveSong(MOCK_SONG).single()
            Assert.assertSame(response, SaveSongResponse.Success)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a song saving process request WHEN the result is loading THEN the response is Loading`() {
        runTest {
            val songsRepository: SongsRepository = mockk()
            val loadingResponse = SaveSongResponse.Loading
            coEvery { songsRepository.saveSong(any()) } returns flowOf(loadingResponse)

            val saveSong = SaveSong(songsRepository = songsRepository)
            val response: SaveSongResponse = saveSong(MOCK_SONG).single()
            Assert.assertSame(response, loadingResponse)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a song saving process WHEN the result is an error THEN the response is Error`() {
        runTest {
            val songsRepository: SongsRepository = mockk()
            val exception = RuntimeException(MOCK_ERROR_MESSAGE)
            coEvery { songsRepository.saveSong(any()) } returns flowOf(SaveSongResponse.Error(exception))

            val saveSong = SaveSong(songsRepository = songsRepository)
            val response: SaveSongResponse = saveSong(MOCK_SONG).single()
            Assert.assertEquals(response, SaveSongResponse.Error(exception))
            clearAllMocks()
        }
    }

    companion object {
        private val MOCK_SONG = Song("song 1", "artist 1")
        private const val MOCK_ERROR_MESSAGE = "Error description"
    }
}
