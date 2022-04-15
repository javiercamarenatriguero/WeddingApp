package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.ui.theme.DarkPink

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PicturesGallery(
    isCollectionLoading: Boolean,
    imagesUriList: List<Uri>
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
            CircularProgressIndicator()
        } else {
            if (imagesUriList.isNotEmpty()) {
                LazyVerticalGrid (
                    modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                    cells = GridCells.Fixed(3)
                ) {
                    items (imagesUriList.size) { item ->
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
                        )
                    }
                }
            }
        }

    }
}