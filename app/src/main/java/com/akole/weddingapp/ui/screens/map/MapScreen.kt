package com.akole.weddingapp.ui.screens.map

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MapScreen(viewModel: MapViewModel = viewModel()){
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ){
        val (map, title, carousel) = createRefs()
        WeddingMap(
            modifier = Modifier.constrainAs(map) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            mapPosition = viewModel.state.mapItemPosition,
            locationItems = viewModel.markers
        )
        MapTitle(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            },
            title = viewModel.markers[viewModel.state.mapItemPosition].title,
            details = viewModel.markers[viewModel.state.mapItemPosition].details
        )
        Carousel(
            mapPosition = viewModel.state.mapItemPosition,
            locationItems = viewModel.markers,
            modifier = Modifier.constrainAs(carousel) {
                bottom.linkTo(parent.bottom)
            },
            onPositionChanged = { position ->
                viewModel.onPositionChanged(position = position)
            }
        )
    }
}
