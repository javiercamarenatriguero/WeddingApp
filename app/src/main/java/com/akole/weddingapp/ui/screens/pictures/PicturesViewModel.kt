package com.akole.weddingapp.ui.screens.pictures

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PicturesViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    data class UiState(
        val mapItemPosition: Int = 0
    )

    fun onPositionChanged(position: Int) {
        updateState(position)
    }

    private fun updateState(
        mapItemPosition: Int = state.mapItemPosition
    ) {
        state = UiState(
            mapItemPosition
        )
    }
}