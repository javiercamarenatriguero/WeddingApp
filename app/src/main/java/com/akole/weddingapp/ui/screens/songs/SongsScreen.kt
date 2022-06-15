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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SongsScreen(
    viewModel: SongsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        SongsScreenContent(
            viewState = viewModel.state,
            onEventHandler = viewModel::on
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