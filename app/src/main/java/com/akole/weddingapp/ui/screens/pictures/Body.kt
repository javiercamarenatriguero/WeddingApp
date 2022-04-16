package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import android.widget.Space
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

@Composable
fun Body(
    viewState: PicturesViewModel.UiState,
    onClick: () -> Unit,
    onClickImage: (Uri) -> Unit
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
                        isLoading = viewState.isLoading,
                        progress = viewState.uploadingProgress,
                        numImages = viewState.uploadingImages,
                        onClick = onClick
                    )
                    PicturesGallery(
                        isCollectionLoading = viewState.isCollectionLoading,
                        imagesUriList = viewState.imageUrlList,
                        onClick = onClickImage
                    )
                }
            }
        }
    }
}
