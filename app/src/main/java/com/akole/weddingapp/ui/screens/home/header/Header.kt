package com.akole.weddingapp.ui.screens.home.header

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.screens.home.HomeViewModel

@Composable
fun Header(
    daysLeft: Int,
    hoursLeft: Int,
    minutesLeft: Int,
    secondsLeft: Int,
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ){
        val (image, box) = createRefs()
        HeaderImage(
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        HeaderBox(
            daysLeft = daysLeft,
            hoursLeft = hoursLeft,
            minutesLeft = minutesLeft,
            secondsLeft = secondsLeft,
            onEventHandler = onEventHandler,
            modifier = Modifier.constrainAs(box) {
                top.linkTo(image.top)
            }
        )
    }
}

@Composable
private fun HeaderImage(modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(R.drawable.home_main)
    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier
            .aspectRatio(1.75f)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun HeaderBox(
    daysLeft: Int,
    hoursLeft: Int,
    minutesLeft: Int,
    secondsLeft: Int,
    onEventHandler: (HomeViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 170.dp, end = 20.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(BorderStroke(3.dp, MaterialTheme.colors.secondary))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        CounterDown(daysLeft, hoursLeft, minutesLeft, secondsLeft)
        Spacer(modifier = Modifier.height(8.dp))
        AddToCalendarButton( onEventHandler = onEventHandler)
    }
}
