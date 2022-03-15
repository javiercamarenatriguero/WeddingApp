package com.akole.weddingapp.ui.screens.pictures

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PicturesScreen(
    viewModel: PicturesViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        PicturesScreenContent(
            viewState = viewModel.state
        )
    }
}