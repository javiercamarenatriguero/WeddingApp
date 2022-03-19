package com.akole.weddingapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R

val fonts = FontFamily(
        Font(R.font.elmessiri_regular),
        Font(R.font.elmessiri_bold, weight = FontWeight.Bold),
        Font(R.font.elmessiri_regular, weight = FontWeight.Light),
        Font(R.font.elmessiri_regular, weight = FontWeight.Thin),
        Font(R.font.elmessiri_bold, weight = FontWeight.ExtraBold),
        Font(R.font.elmessiri_regular, weight = FontWeight.ExtraLight),
        Font(R.font.elmessiri_medium, weight = FontWeight.Medium),
        Font(R.font.elmessiri_semibold, weight = FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
        body1 = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)
