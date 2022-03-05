package com.akole.weddingapp.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.theme.Purple200
import com.akole.weddingapp.ui.theme.Purple700

@Composable
fun Header(
    daysLeft: Int,
    hoursLeft: Int,
    minutesLeft: Int,
    secondsLeft: Int
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
            modifier = Modifier.constrainAs(box) {
                top.linkTo(image.top)
            }
        )
    }
}

@Composable
private fun HeaderImage(modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(R.drawable.image3)
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
        addToCalendarButton()
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.home_head_title),
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        fontFamily = FontFamily.Cursive
    )
    Text(
        text = stringResource(id = R.string.date_title),
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
        color = Color.Gray,
        fontSize = 14.sp,
        fontFamily = FontFamily.Cursive
    )
}

@Composable
private fun CounterDown(
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

@Composable
private fun addToCalendarButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(vertical = 5.dp)) {
        Text(text = stringResource(id = R.string.add_to_calendar))
    }
}