package com.akole.weddingapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Pink,
    primaryVariant = LightPink,
    secondary = DarkPink
)

@Composable
fun WeddingAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
            colors = LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}