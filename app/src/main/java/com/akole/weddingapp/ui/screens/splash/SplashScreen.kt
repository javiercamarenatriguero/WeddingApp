package com.akole.weddingapp.ui.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    modifier: Modifier = Modifier.fillMaxSize(),
    onDelayed: () -> Unit
) {
    SplashScreenContent(modifier)

    LaunchedEffect(key1 = true) {
        delay(timeMillis = 3000)
        onDelayed()
    }
}