package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R

@Composable
fun SongsDescription() {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.songs_description_text),
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}