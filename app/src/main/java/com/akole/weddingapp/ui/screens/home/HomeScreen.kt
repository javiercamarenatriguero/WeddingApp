package com.akole.weddingapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        HomeScreenContent(viewModel.state)
    }
}