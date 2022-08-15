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
class GetSongsTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Test
    fun `GIVEN a list of songs request WHEN the result is success THEN the response is Success with songs`() {
        runTest {
            val songsRepository: SongsRepository = mockk()
            coEvery { songsRepository.getSongList() } returns flowOf(GetSongsResponse.Success(MOCK_SUCCESS_SONGS_LIST))

            val getSongs = GetSongs(songsRepository = songsRepository)
            val response: GetSongsResponse = getSongs().single()
            val songList = (response as GetSongsResponse.Success).songs
            Assert.assertEquals(MOCK_SUCCESS_SONGS_LIST, songList)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of songs request WHEN the result is loading THEN the response is Loading`() {
        runTest {
            val songsRepository: SongsRepository = mockk()
            coEvery { songsRepository.getSongList() } returns flowOf(GetSongsResponse.Loading)

            val getSongs = GetSongs(songsRepository = songsRepository)
            val response: GetSongsResponse = getSongs().single()
            Assert.assertSame(response, GetSongsResponse.Loading)
            clearAllMocks()
        }
    }

    @Test
    fun `GIVEN a list of images request WHEN the result is an error THEN the response is Error`() {
        runTest {
            val songsRepository: SongsRepository = mockk()
            val exception = RuntimeException(MOCK_ERROR_MESSAGE)
            coEvery { songsRepository.getSongList() } returns flowOf(GetSongsResponse.Error(exception))

            val getSongs = GetSongs(songsRepository = songsRepository)
            val response: GetSongsResponse = getSongs().single()
            Assert.assertEquals(response, GetSongsResponse.Error(exception))
            clearAllMocks()
        }
    }

    companion object {
        private val MOCK_SUCCESS_SONGS_LIST = listOf(
            Song("Song 1", "Artist 1"),
            Song("Song 2", "Artist 2")
        )
        private const val MOCK_ERROR_MESSAGE = "Error description"
    }
}
