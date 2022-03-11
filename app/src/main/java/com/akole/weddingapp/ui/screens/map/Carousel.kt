package com.akole.weddingapp.ui.screens.map

import android.webkit.WebSettings
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.ui.theme.DarkPink
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel(
    mapPosition: Int,
    locationItems: List<LocationItem>,
    modifier: Modifier = Modifier,
    onPositionChanged: (Int) -> Unit
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPositionChanged(page)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = locationItems.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White.copy(alpha = 0.7f))
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
                CarouselItemCard(item = locationItems[page])
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
    item: LocationItem
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()

    ) {
        val (image) = createRefs()
        val painter = rememberAsyncImagePainter(item.image)
        Image(
            painter = painter,
            contentDescription = stringResource(id = item.title),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(400.dp)
                .aspectRatio(1 / 1.5f)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
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
