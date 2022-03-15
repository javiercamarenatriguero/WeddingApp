package com.akole.weddingapp.ui.screens.songs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.R

@Composable
fun Header() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ){
        val painter = rememberAsyncImagePainter(R.drawable.concert)
        val (image, title) = createRefs()
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
        )
        Surface(
            elevation = 1.dp,
            shape = MaterialTheme.shapes.medium,
            color = Color.Black.copy(alpha = 0.7f),
            modifier = Modifier.constrainAs(title) {
                centerHorizontallyTo(image)
                centerVerticallyTo(image)
            }
        ) {
            Text(
                text = stringResource(id = R.string.add_music_text),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }





    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {


    }
}