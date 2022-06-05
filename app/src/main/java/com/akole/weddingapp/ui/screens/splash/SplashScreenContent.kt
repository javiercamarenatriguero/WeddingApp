package com.akole.weddingapp.ui.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier
) {
    val heartTransition = rememberInfiniteTransition()

    val volumeHeart by heartTransition.animateFloat(
        initialValue = 40f,
        targetValue = 60f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                delayMillis = 100,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = rememberAsyncImagePainter(com.akole.weddingapp.R.drawable.gp_graphic)
            Image(
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(300.dp)
            )
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier.size(volumeHeart.dp)
            )
        }
    }
}