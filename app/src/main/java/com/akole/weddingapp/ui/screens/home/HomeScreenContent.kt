package com.akole.weddingapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.ui.screens.home.header.Header
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenContent(
    viewState: HomeViewModel.UiState,
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit,
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
            secondsLeft = viewState.secondsLeft,
            onEventHandler = onEventHandler
        )
        WeddingDescription()
        WeddingPicks()
        Spacer(modifier = Modifier.height(16.dp))
        WeddingDetails()
        Spacer(modifier = Modifier.height(16.dp))
    }
}
