package com.akole.weddingapp.ui.songs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SongsViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents: Flow<OneShotEvent> = _oneShotEvents.receiveAsFlow()

    fun on(event: ViewEvent): Unit = with(event) {
        when (this) {
            is ViewEvent.SongTextChange -> {
                updateState(song = value)
            }
            is ViewEvent.ArtistTextChange -> {
                updateState(artist = value)
            }
            ViewEvent.AddClicked -> onAddClicked()
        }
    }

    private fun onAddClicked() {
        emit(OneShotEvent.HideKeyboard)
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
    }

    data class UiState(
        val song: String = "",
        val artist: String = "",
        val isButtonReady: Boolean = false
    )

    private fun updateState(
        song: String = state.song,
        artist: String = state.artist,
        isButtonReady: Boolean = checkSubmitButton(song)
    ) {
        state = UiState(
            song,
            artist,
            isButtonReady
        )
    }

    private fun checkSubmitButton(song: String) = !song.isNullOrEmpty()

}