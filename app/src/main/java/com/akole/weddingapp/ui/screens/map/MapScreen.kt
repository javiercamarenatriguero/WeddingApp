package com.akole.weddingapp.ui.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun MapScreen(viewModel: MapViewModel = viewModel()){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        MapScreenContent(
            viewState = viewModel.state,
            markers = viewModel.markers,
            onViewEvent = viewModel::onViewEvent
        )
    }
}
