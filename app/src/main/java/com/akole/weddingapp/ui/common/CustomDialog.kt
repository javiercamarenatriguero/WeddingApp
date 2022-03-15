package com.akole.weddingapp.ui.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun CustomDialog(
    message: String,
    buttonText: String,
    onDismissDialog: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismissDialog()
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismissDialog()
                },
            ) {
                Text(
                    text = buttonText,
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp
                )
            }
        },
        text = {
            Text(
                text = message,
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp
            )
        },
    )
}