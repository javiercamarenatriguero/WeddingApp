package com.akole.weddingapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    modifier: Modifier = Modifier,
    onDelayed: () -> Unit
) {
    SplashScreenContent()

    LaunchedEffect(key1 = true) {
        delay(timeMillis = 3000)
        onDelayed()
    }
}