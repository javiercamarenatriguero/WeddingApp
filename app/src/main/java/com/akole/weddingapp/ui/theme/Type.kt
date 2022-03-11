package com.akole.weddingapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R

val fonts = FontFamily(
        Font(R.font.league_spartan_black),
        Font(R.font.league_spartan_bold, weight = FontWeight.Bold),
        Font(R.font.league_spartan_light, weight = FontWeight.Light),
        Font(R.font.league_spartan_thin, weight = FontWeight.Thin),
        Font(R.font.league_spartan_extrabold, weight = FontWeight.ExtraBold),
        Font(R.font.league_spartan_extralight, weight = FontWeight.ExtraLight),
        Font(R.font.league_spartan_medium, weight = FontWeight.Medium),
        Font(R.font.league_spartan_semibold, weight = FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
        body1 = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
