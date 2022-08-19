package com.akole.weddingapp.ui.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.ui.screens.songs.SongsViewModel

@Composable
fun CustomDialog(
    message: String,
    buttonText: String,
    onEventHandler: (SongsViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier
    ) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEventHandler.invoke(SongsViewModel.ViewEvent.DialogClicked)
        },
        confirmButton = {
            Button(
                onClick = {
                    onEventHandler.invoke(SongsViewModel.ViewEvent.DialogClicked)
                },
            ) {
                Text(
                    text = buttonText
                )
            }
        },
        text = {
            Text(
                text = message,
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp
            )
        }
    )
}