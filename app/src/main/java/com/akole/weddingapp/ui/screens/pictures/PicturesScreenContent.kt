package com.akole.weddingapp.ui.screens.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PicturesScreenContent(
    viewState: PicturesViewModel.UiState,
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
