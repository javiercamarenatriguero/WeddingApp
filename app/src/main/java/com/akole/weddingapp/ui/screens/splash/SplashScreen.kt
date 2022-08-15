package com.akole.weddingapp.ui.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen (
    modifier: Modifier = Modifier.fillMaxSize(),
    onDelayed: () -> Unit
) {
    SplashScreenContent(modifier)

    LaunchedEffect(key1 = true) {
        delay(timeMillis = SPLASH_SCREEN_TIMEOUT)
        onDelayed()
    }
}

internal const val SPLASH_SCREEN_TIMEOUT = 2000L