package com.akole.weddingapp.ui.screens.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    locationUIItems: List<LocationUIItem>,
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
            count = locationUIItems.size,
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
                CarouselItemCard(UIItem = locationUIItems[page], position = (page + 1))
            }
        }
    }
}

@Composable
private fun CarouselItemCard(
    position: Int,
    UIItem: LocationUIItem
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()

    ) {
        val (image, label) = createRefs()
        val painter = rememberAsyncImagePainter(UIItem.image)
        Image(
            painter = painter,
            contentDescription = stringResource(id = UIItem.title),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(400.dp)
                .aspectRatio(1 / 1.5f)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .border(4.dp, DarkPink)
        )
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .padding(10.dp)
                .background(DarkPink)
                .clip(CircleShape)
                .constrainAs(label) {
                    top.linkTo(image.top)
                    start.linkTo(image.start)
                }
        ) {
            Text(
                text = position.toString(),
                color = Color.White,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}
