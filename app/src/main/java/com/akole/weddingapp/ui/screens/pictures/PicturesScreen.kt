package com.akole.weddingapp.ui.screens.pictures

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
        .fillMaxWidth()
        .background(Color.White)
    ) {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            viewModel.on(PicturesViewModel.ViewEvent.GetImagesResponse(it))
        }
        PicturesScreenContent(
            viewState = viewModel.state,
            onClick = {
                launcher.launch("image/*")
            },
            onClickImage = { uri ->
                viewModel.on(PicturesViewModel.ViewEvent.ShowPictureDialog(uri))
            },
            onDismissDialog = {
                viewModel.on(PicturesViewModel.ViewEvent.DismissDialog)
            }
        )
    }
}