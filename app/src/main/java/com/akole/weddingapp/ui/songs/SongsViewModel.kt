package com.akole.weddingapp.ui.songs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SongsViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    data class UiState(
        val song: String = "",
        val artist: String = "",
        val isButtonReady: Boolean = false
    )

    private fun updateState(
        song: String = state.song,
        artist: String = state.artist,
        isButtonReady: Boolean = state.isButtonReady
    ) {
        state = UiState(
            song,
            artist,
            isButtonReady
        )
    }
}