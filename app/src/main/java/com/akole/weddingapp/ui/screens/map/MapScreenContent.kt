package com.akole.weddingapp.ui.screens.map

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MapScreenContent(
    viewState: ViewState,
    markers: List<LocationUIItem>,
    onViewEvent: (MapViewModel.ViewEvent) -> Unit
) {
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
            mapPosition = viewState.mapItemPosition,
            locationUIItems = markers
        )
        MapTitle(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            },
            title = markers[viewState.mapItemPosition].title,
            details = markers[viewState.mapItemPosition].details
        )
        Carousel(
            locationUIItems = markers,
            modifier = Modifier.constrainAs(carousel) {
                bottom.linkTo(parent.bottom)
            },
            onViewEvent = onViewEvent
        )
    }
}