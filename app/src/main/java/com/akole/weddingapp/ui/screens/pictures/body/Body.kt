package com.akole.weddingapp.ui.screens.pictures.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.ui.screens.pictures.PicturesViewModel
import com.akole.weddingapp.ui.screens.pictures.ViewState

@Composable
internal fun Body(
    viewState: ViewState,
    onEventHandler: (PicturesViewModel.ViewEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    PicturesDescription(
                        isUploadingImagesLoading = viewState.isUploadingImagesLoading,
                        isCollectionLoading = viewState.isCollectionLoading,
                        isPhotoServiceEnabled = viewState.isPhotoServiceEnabled,
                        progress = viewState.uploadingProgress,
                        numImages = viewState.uploadingImages,
                        onEventHandler = onEventHandler
                    )
                    PicturesGallery(
                        isCollectionLoading = viewState.isCollectionLoading,
                        isCollectionError = viewState.isCollectionError,
                        imagesUriList = viewState.imageUrlList,
                        onEventHandler = onEventHandler
                    )
                }
            }
        }
    }
}
