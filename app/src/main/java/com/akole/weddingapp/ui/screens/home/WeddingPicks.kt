package com.akole.weddingapp.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.theme.DarkPink

@Composable
fun WeddingPicks() {
    Row (
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Pick(R.drawable.javier1, "Javier")
        Pick(R.drawable.leyre1, "Leyre")
    }
}

@Composable
private fun Pick(@DrawableRes imageRes: Int, label: String) {
    val painter = rememberAsyncImagePainter(imageRes)
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(4.dp, DarkPink, CircleShape)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = label,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )
    }
}