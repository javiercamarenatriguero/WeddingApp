package com.akole.weddingapp.ui.screens.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akole.weddingapp.Constants.cocktelMarker
import com.akole.weddingapp.Constants.councilMarker
import com.akole.weddingapp.Constants.restaurantMarker

class MapViewModel: ViewModel() {

    var state by mutableStateOf(ViewState())
        private set

    internal val markers = listOf(councilMarker, cocktelMarker, restaurantMarker)

    sealed interface ViewEvent {
        data class PositionChanged(val position: Int): ViewEvent
    }

    fun onViewEvent(viewEvent: ViewEvent): Unit = with(viewEvent) {
        when (this) {
            is ViewEvent.PositionChanged ->
                onPositionChanged(position = position)
        }
    }

    private fun onPositionChanged(position: Int) {
        updateState(position)
    }

    private fun updateState(
        mapItemPosition: Int = state.mapItemPosition
    ) {
        state = ViewState(
            mapItemPosition
        )
    }
}