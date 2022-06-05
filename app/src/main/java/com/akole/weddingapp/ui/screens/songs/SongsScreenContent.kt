package com.akole.weddingapp.ui.screens.songs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.common.CustomDialog
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SongsScreenContent(
    viewState: SongsViewModel.UiState,
    onSongTextChange: (String) -> Unit,
    onArtistTextChange: (String) -> Unit,
    onSubmitButtonClicked: () -> Unit,
    onDismissDialog: () -> Unit,
    onArtistCompleted: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Header()
        Body(
            viewState = viewState,
            onSongValueChanged = onSongTextChange,
            onArtistValueChanged = onArtistTextChange,
            onSubmitClicked = onSubmitButtonClicked,
            onArtistCompleted = onArtistCompleted
        )
        if (viewState.isDialogShown) {
            CustomDialog(
                message = stringResource(id = R.string.songs_item_saved_message),
                buttonText = stringResource(id = R.string.songs_submit_button_text),
                onDismissDialog = onDismissDialog
            )
        }
    }
}
