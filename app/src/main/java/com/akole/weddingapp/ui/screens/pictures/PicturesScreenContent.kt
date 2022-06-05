package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PicturesScreenContent(
    viewState: PicturesViewModel.UiState,
    onClick: () -> Unit,
    onClickImage: (Uri) -> Unit,
    onDismissDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Header()
        Body(
            viewState = viewState,
            onClick = onClick,
            onClickImage = onClickImage
        )
        if (viewState.isShownPictureDialog && viewState.pictureUri != null) {
            PictureDialogContent(
                uri = viewState.pictureUri,
                onDismissListener = onDismissDialog
            )
        }
    }
}
