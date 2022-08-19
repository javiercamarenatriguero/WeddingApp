package com.akole.weddingapp.ui.screens.home.body

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.Constants.CHARACTER_IMAGE_1
import com.akole.weddingapp.Constants.CHARACTER_IMAGE_2
import com.akole.weddingapp.Constants.CHARACTER_NAME_1
import com.akole.weddingapp.Constants.CHARACTER_NAME_2
import com.akole.weddingapp.ui.theme.DarkPink

@Composable
internal fun WeddingPicks() {
    Row (
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Pick(CHARACTER_IMAGE_1, CHARACTER_NAME_1)
        Pick(CHARACTER_IMAGE_2, CHARACTER_NAME_2)
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
            style = MaterialTheme.typography.body1
        )
    }
}
