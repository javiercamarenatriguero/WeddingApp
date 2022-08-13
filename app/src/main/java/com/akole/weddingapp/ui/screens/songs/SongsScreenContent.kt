package com.akole.weddingapp.ui.screens.songs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.common.CustomDialog

@Composable
fun SongsScreenContent(
    viewState: UiState,
    onEventHandler: (SongsViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Header()
        Body(
            viewState = viewState,
            onEventHandler = onEventHandler
        )
        if (viewState.isDialogShown) {
            CustomDialog(
                message = stringResource(id = R.string.songs_item_saved_message),
                buttonText = stringResource(id = R.string.songs_submit_button_text),
                onEventHandler = onEventHandler,
                modifier = Modifier.testTag(SUCCESS_DIALOG_TEST_TAG)
            )
        }
    }
}

internal const val SUCCESS_DIALOG_TEST_TAG = "SuccessDialogTestTag"
