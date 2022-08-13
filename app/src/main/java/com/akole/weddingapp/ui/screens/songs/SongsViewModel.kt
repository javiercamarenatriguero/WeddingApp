package com.akole.weddingapp.ui.screens.songs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.domain.usecases.GetSongs
import com.akole.weddingapp.domain.usecases.GetSongsResponse
import com.akole.weddingapp.domain.usecases.SaveSong
import com.akole.weddingapp.domain.usecases.SaveSongResponse
import com.akole.weddingapp.ui.utils.upperAsTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val getSongs: GetSongs,
    private val saveSong: SaveSong
): ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents: Flow<OneShotEvent> = _oneShotEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadSongList()
        }
    }

    suspend fun loadSongList() {
        getSongs().collect { response ->
            when (response) {
                is GetSongsResponse.Loading -> {
                    updateState(
                        isLoading = true,
                        isButtonReady = false
                    )
                }
                is GetSongsResponse.Success -> {
                    updateState(
                        isLoading = false,
                        isButtonReady = true,
                        songList = response.songs
                    )
                }
                is GetSongsResponse.Error -> {
                    updateState(
                        isLoading = false,
                        isButtonReady = true
                    )
                }
            }
        }
    }

    suspend fun uploadSong(song: Song) {
        saveSong(song).collect { response ->
            when (response) {
                is SaveSongResponse.Loading -> {
                    updateState(isLoading = true)
                }
                is SaveSongResponse.Success -> {
                    loadSongList()
                    updateState(
                        song = "",
                        artist = "",
                        isDialogShown = true
                    )
                }
                is SaveSongResponse.Error -> {
                    updateState(isLoading = false)
                }
            }
        }
    }

    fun onViewEvent(event: ViewEvent): Unit = with(event) {
        when (this) {
            is ViewEvent.SongTextChange -> {
                updateState(song = value)
            }
            is ViewEvent.ArtistTextChange -> {
                updateState(artist = value)
            }
            ViewEvent.AddClicked -> onAddClicked()
            ViewEvent.DialogClicked -> updateState(isDialogShown = false)
            ViewEvent.ArtistCompleted -> emit(OneShotEvent.HideKeyboard)
        }
    }

    private fun onAddClicked() {
        viewModelScope.launch {
            emit(OneShotEvent.HideKeyboard)
            uploadSong(
                Song(
                    name = state.song.upperAsTitle(),
                    artist = state.artist?.upperAsTitle()
                )
            )
        }
    }

    private fun emit(event: OneShotEvent) {
        viewModelScope.launch {
            _oneShotEvents.send(event)
        }
    }

    sealed interface OneShotEvent {
        object HideKeyboard : OneShotEvent
    }

    sealed interface ViewEvent {
        data class SongTextChange(val value: String) : ViewEvent
        data class ArtistTextChange(val value: String) : ViewEvent
        object AddClicked: ViewEvent
        object DialogClicked: ViewEvent
        object ArtistCompleted: ViewEvent
    }

    private fun updateState(
        song: String = state.song,
        artist: String? = state.artist,
        songList: List<Song> = state.songList,
        isLoading: Boolean = state.isLoading,
        isButtonReady: Boolean = checkSubmitButton(song),
        isDialogShown: Boolean = state.isDialogShown
    ) {
        state = UiState(
            song,
            artist,
            songList,
            isLoading,
            isButtonReady,
            isDialogShown
        )
    }

    // Check if song name is null or empty. Artist name is not mandatory
    private fun checkSubmitButton(song: String) = song.isNotEmpty()
}
