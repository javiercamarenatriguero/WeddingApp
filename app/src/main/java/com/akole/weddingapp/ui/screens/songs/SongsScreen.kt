package com.akole.weddingapp.ui.screens.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SongsScreen(
    viewModel: SongsViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        SongsScreenContent(
            viewState = viewModel.state,
            onSongTextChange = { song ->
                viewModel.on(SongsViewModel.ViewEvent.SongTextChange(song))
            },
            onArtistTextChange = { artist ->
                viewModel.on(SongsViewModel.ViewEvent.ArtistTextChange(artist))
            },
            onSubmitButtonClicked = {
                viewModel.on(SongsViewModel.ViewEvent.AddClicked)
            },
            onDismissDialog = {
                viewModel.on(SongsViewModel.ViewEvent.DialogClicked)
            },
            onArtistCompleted = {
                viewModel.on(SongsViewModel.ViewEvent.ArtistCompleted)
            }
        )
    }

    LaunchedEffect(viewModel.oneShotEvents) {
        viewModel.oneShotEvents.collect { event ->
            when (event) {
                SongsViewModel.OneShotEvent.HideKeyboard -> keyboardController?.hide()
            }
        }
    }
}