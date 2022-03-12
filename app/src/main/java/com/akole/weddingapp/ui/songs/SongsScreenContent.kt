package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SongsScreenContent(
    viewState: SongsViewModel.UiState,
    onSongTextChange: (String) -> Unit,
    onArtistTextChange: (String) -> Unit,
    onSubmitButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Header()
        Body(
            viewState = viewState,
            onSongValueChanged = onSongTextChange,
            onArtistValueChanged = onArtistTextChange,
            onSubmitClicked = onSubmitButtonClicked
        )
    }
}
