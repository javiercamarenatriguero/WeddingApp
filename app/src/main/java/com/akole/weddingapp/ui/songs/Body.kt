package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.akole.weddingapp.ui.theme.DarkPink
import com.akole.weddingapp.ui.theme.LightPink

@Composable
fun Body() {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1000.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SongsDescription()
                    Spacer(modifier = Modifier.height(5.dp))
                    SongsForm()
                }
            }
        }
    }
}