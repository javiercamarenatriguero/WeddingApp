package com.akole.weddingapp.ui.screens.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PicturesScreenContent(
    viewState: ViewState,
    onEventHandler: (PicturesViewModel.ViewEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Header()
        Body(
            viewState = viewState,
            onEventHandler = onEventHandler
        )
        if (viewState.isShownPictureDialog && viewState.pictureUri != null) {
            PictureDialogContent(
                uri = viewState.pictureUri,
                onEventHandler = onEventHandler
            )
        }
    }
}
