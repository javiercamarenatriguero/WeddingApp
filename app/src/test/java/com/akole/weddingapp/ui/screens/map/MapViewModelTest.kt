package com.akole.weddingapp.ui.screens.map

import org.junit.Assert
import org.junit.Test

class MapViewModelTest {

    @Test
    fun `GIVEN a list of map locations request WHEN the position is changed from UI THEN the ViewState is updated to position`() {
        val viewModel = MapViewModel()
        viewModel.onViewEvent(MapViewModel.ViewEvent.PositionChanged(MOCK_MAP_POSITION_INDEX))
        Assert.assertEquals(MOCK_MAP_POSITION_INDEX, viewModel.state.mapItemPosition)
    }

    companion object {
        private const val MOCK_MAP_POSITION_INDEX = 2
    }
}
