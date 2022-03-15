package com.akole.weddingapp.ui.screens.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PicturesScreenContent(
    viewState: PicturesViewModel.UiState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Header()
        Body(
            viewState = viewState
        )
        /*if (viewState.isDialogShown) {
            CustomDialog(
                message = stringResource(id = R.string.songs_item_saved_message),
                buttonText = "OK",
                onDismissDialog = onDismissDialog
            )
        }*/

    }
}
