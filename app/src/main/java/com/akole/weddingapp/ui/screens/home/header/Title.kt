package com.akole.weddingapp.ui.screens.home.header

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R

@Composable
internal fun Title() {
    Text(
        text = stringResource(id = R.string.home_head_title),
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        fontFamily = FontFamily.Cursive
    )
    Text(
        text = stringResource(id = R.string.left_title),
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Start,
        color = Color.Gray,
        fontSize = 14.sp,
        fontFamily = FontFamily.Cursive
    )
}
