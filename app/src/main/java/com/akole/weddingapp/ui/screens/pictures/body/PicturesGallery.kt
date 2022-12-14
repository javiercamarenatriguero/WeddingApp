package com.akole.weddingapp.ui.screens.pictures.body

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.screens.pictures.PictureScreenTestTags
import com.akole.weddingapp.ui.screens.pictures.PicturesViewModel
import com.akole.weddingapp.ui.theme.DarkPink

@Composable
internal fun PicturesGallery(
    isCollectionLoading: Boolean,
    isCollectionError: Boolean,
    imagesUriList: List<String>,
    onEventHandler: (PicturesViewModel.ViewEvent) -> Unit,
) {
    Column (
        Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp, max = 20000.dp)
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        if (isCollectionLoading) {
            CircularProgressIndicator(
                modifier = Modifier.testTag(PictureScreenTestTags.LOADING_COLLECTION)
            )
        } else if (isCollectionError) {
            Text(
                text = stringResource(id = R.string.pictures_error_message)
            )
        } else {
            if (imagesUriList.isNotEmpty()) {
                LazyVerticalGrid (
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .testTag(PictureScreenTestTags.COLLECTION_GRID),
                    columns = GridCells.Fixed(3)
                ) {
                    items(imagesUriList.size) { item ->
                        val painter = rememberAsyncImagePainter(imagesUriList[item])
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .padding(2.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .border(1.dp, DarkPink, RoundedCornerShape(5.dp))
                                .clickable {
                                    onEventHandler
                                        .invoke(
                                            PicturesViewModel.ViewEvent.ShowPictureDialog(
                                                imagesUriList[item]
                                            )
                                        )
                                }
                            )
                        }
                    }
            } else {
                Text(
                    text = stringResource(id = R.string.pictures_empty_message)
                )
            }
        }
    }
}
