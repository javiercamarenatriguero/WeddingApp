package com.akole.weddingapp.ui.screens.map

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import com.akole.weddingapp.ui.theme.LightPink

@Composable
fun MapTitle(
    title: Int,
    details: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(vertical = 20.dp)
    ) {
        Surface(
            elevation = 1.dp,
            shape = MaterialTheme.shapes.small,
            color = LightPink
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text= stringResource(id = title),
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 10.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text= stringResource(id = details),
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 10.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}