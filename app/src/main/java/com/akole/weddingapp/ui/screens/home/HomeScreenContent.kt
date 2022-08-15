package com.akole.weddingapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akole.weddingapp.ui.screens.home.body.Body
import com.akole.weddingapp.ui.screens.home.header.Header

@Composable
fun HomeScreenContent(
    viewState: ViewState,
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
        Body()
    }
}
