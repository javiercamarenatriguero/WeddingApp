package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SongsScreen(
    viewModel: SongsViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        SongsScreenContent(viewState = viewModel.state)
    }
}