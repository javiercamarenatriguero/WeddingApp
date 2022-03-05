package com.akole.weddingapp.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.R
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel() {
    val imageList = listOf(
        CarouselItem("Image1", R.drawable.image1),
        CarouselItem("Image2",  R.drawable.image2),
        CarouselItem("Image3",  R.drawable.image3),
        CarouselItem("Image4",  R.drawable.image4)
    )
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 4,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 60.dp),
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Card(
                Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
                    .aspectRatio(1.75f)
            ) {
                CarouselItemCard(item = imageList[page])
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
private fun CarouselItemCard(
    item: CarouselItem
) {
    Box {
        val painter = rememberAsyncImagePainter(item.drawable)
        Image(
            painter = painter,
            contentDescription = item.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(500.dp)
                .aspectRatio(1 / 1.5f)
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            startY = size.height / 4,
                            endY = size.height,
                            colors = listOf(
                                Color.Transparent,
                                Color.Black,
                            )
                        )
                    )
                }
        )
    }
}

private data class CarouselItem(val title: String, @DrawableRes val drawable: Int)