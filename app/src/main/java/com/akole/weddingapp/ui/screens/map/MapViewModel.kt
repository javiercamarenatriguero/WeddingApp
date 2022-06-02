package com.akole.weddingapp.ui.screens.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akole.weddingapp.R
import com.google.android.gms.maps.model.LatLng

class MapViewModel: ViewModel() {

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

    companion object {
        private val councilMarker = LocationItem(
            position = LatLng(43.2640404644175, -2.9231387425674176),
            title = R.string.council_title,
            snippet = R.string.council_snippet,
            image = R.drawable.marker1,
            details = R.string.council_details,
        )
        private val cocktelMarker = LocationItem(
            position = LatLng(43.263880486320836, -2.9260678485581844),
            title = R.string.cocktel_title,
            snippet = R.string.cocktel_snippet,
            image = R.drawable.marker2,
            details = R.string.cocktel_details,

        )
        private val restaurantMarker = LocationItem(
            position = LatLng(43.29085987514852, -2.925274743006676),
            title = R.string.restaurant_title,
            snippet = R.string.restaurant_snippet,
            image = R.drawable.marker3,
            details = R.string.restaurant_details,
        )
    }

    val markers = listOf(councilMarker, cocktelMarker, restaurantMarker)
}