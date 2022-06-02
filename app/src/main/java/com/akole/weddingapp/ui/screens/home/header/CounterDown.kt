package com.akole.weddingapp.ui.screens.home.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R

@Composable
internal fun CounterDown(
    daysLeft: Int,
    hoursLeft: Int,
    minutesLeft: Int,
    secondsLeft: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        CounterBox(
            count = daysLeft,
            label = stringResource(id = R.string.days)
        )
        CounterBox(
            count = hoursLeft,
            label = stringResource(id = R.string.hours)
        )
        CounterBox(
            count = minutesLeft,
            label = stringResource(id = R.string.minutes)
        )
        CounterBox(
            count = secondsLeft,
            label = stringResource(id = R.string.seconds)
        )
    }
}

@Composable
private fun CounterBox(count: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$count",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = label,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(2.dp)
        )
    }
}