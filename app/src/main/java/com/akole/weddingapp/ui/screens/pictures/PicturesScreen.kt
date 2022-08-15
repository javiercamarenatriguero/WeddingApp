package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun PicturesScreen(
    viewModel: PicturesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    Box(modifier = modifier
        .fillMaxWidth()
        .background(Color.White)
    ) {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            viewModel.onViewEvent(PicturesViewModel.ViewEvent.GetImagesResponse(it))
        }
        PicturesScreenContent(
            viewState = viewModel.state,
            onEventHandler = viewModel::onViewEvent
        )

        LaunchedEffect(viewModel.oneShotEvents) {
            viewModel.oneShotEvents.collect { event ->
                when (event) {
                    PicturesViewModel.OneShotEvent.GoToImageGallery -> goToImageGallery(launcher)
                }
            }
        }
    }
}

private fun goToImageGallery(
    launcher: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>
) {
    launcher.launch("image/*")
}