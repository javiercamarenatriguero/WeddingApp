package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import com.akole.weddingapp.ui.common.CustomPictureDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
internal fun PictureDialogContent(
    uri: Uri,
    onEventHandler: (PicturesViewModel.ViewEvent) -> Unit,
) {
    CustomPictureDialog(
        onDismissRequest = {
            onEventHandler.invoke(PicturesViewModel.ViewEvent.DismissDialog)
        },
        content = {
            Column (
                modifier =
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val painter = rememberAsyncImagePainter(uri)
                var angle by remember { mutableStateOf(0f) }
                var zoom by remember { mutableStateOf(1f) }
                var offsetX by remember { mutableStateOf(0f) }
                var offsetY by remember { mutableStateOf(0f) }
                Image(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.picture_photo_dialog),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .fillMaxWidth()
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .graphicsLayer(
                            scaleX = zoom,
                            scaleY = zoom,
                            rotationZ = angle
                        )
                        .pointerInput(Unit) {
                            detectTransformGestures(
                                onGesture = { _, pan, gestureZoom, _ ->
                                    zoom *= gestureZoom
                                    val x = pan.x * zoom
                                    val y = pan.y * zoom
                                    val angleRad = angle * PI / 180.0
                                    offsetX += (x * cos(angleRad) - y * sin(angleRad)).toFloat()
                                    offsetY += (x * sin(angleRad) + y * cos(angleRad)).toFloat()
                                }
                            )
                        }
                )
                Button(onClick = {
                    onEventHandler.invoke(PicturesViewModel.ViewEvent.DismissDialog)
                }) {
                    Text(stringResource(id = R.string.picture_close_button_text))
                }
            }
        }
    )
}
