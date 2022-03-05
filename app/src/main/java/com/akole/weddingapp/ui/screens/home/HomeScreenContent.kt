package com.akole.weddingapp.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenContent(
    viewState: HomeViewModel.UiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
    ) {
        Header()
        Carousel()
        CountDownTimer(
            daysLeft = viewState.daysLeft,
            hoursLeft = viewState.hoursLeft,
            minutesLeft = viewState.minutesLeft,
            secondsLeft = viewState.secondsLeft
        )
    }
}

@Composable
private fun Header() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Leyre & Javier",
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontFamily = FontFamily.Cursive
            )
            Divider(color = Black, thickness = 1.dp)
        }
    }
}

@Composable
private fun CountDownTimer(
    daysLeft: Int,
    hoursLeft: Int,
    minutesLeft: Int,
    secondsLeft: Int
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Dias: $daysLeft",
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive
            )
            Text(
                text = "Horas: $hoursLeft",
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive
            )
            Text(
                text = "Min.: $minutesLeft",
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive
            )
            Text(
                text = "Seg.: $secondsLeft",
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive
            )
        }
    }
}
