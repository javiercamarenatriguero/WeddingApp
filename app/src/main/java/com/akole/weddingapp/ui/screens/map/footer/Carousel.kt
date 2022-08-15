package com.akole.weddingapp.ui.screens.map.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.akole.weddingapp.ui.screens.map.body.LocationUIItem
import com.akole.weddingapp.ui.screens.map.MapScreenTestTags
import com.akole.weddingapp.ui.screens.map.MapViewModel
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun Carousel(
    locationUIItems: List<LocationUIItem>,
    modifier: Modifier = Modifier,
    onViewEvent: (MapViewModel.ViewEvent) -> Unit,
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onViewEvent.invoke(MapViewModel.ViewEvent.PositionChanged(page))
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
                .testTag(MapScreenTestTags.CAROUSEL)
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
