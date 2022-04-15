package com.akole.weddingapp.ui.screens.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PicturesScreenContent(
    viewState: PicturesViewModel.UiState,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Header()
        Body(
            viewState = viewState,
            onClick = onClick
        )
    }
}
