package com.akole.weddingapp.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenContent(
    viewState: HomeViewModel.UiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Header(
            daysLeft = viewState.daysLeft,
            hoursLeft = viewState.hoursLeft,
            minutesLeft = viewState.minutesLeft,
            secondsLeft = viewState.secondsLeft
        )
        Carousel()
    }
}
