package com.akole.weddingapp.ui.screens.map.header

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.ui.theme.LightPink

@Composable
internal fun MapTitle(
    @StringRes title: Int,
    @StringRes details: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(vertical = 10.dp)
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
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 10.dp),
                )
                Text(
                    text= stringResource(id = details),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 10.dp)
                )
            }
        }
    }
}