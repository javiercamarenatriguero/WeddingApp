package com.akole.weddingapp.ui.screens.map.footer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.akole.weddingapp.ui.screens.map.body.LocationUIItem
import com.akole.weddingapp.ui.theme.DarkPink


@Composable
internal fun CarouselItemCard(
    position: Int,
    UIItem: LocationUIItem
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()

    ) {
        val (image, label) = createRefs()
        val painter = rememberAsyncImagePainter(UIItem.image)
        Image(
            painter = painter,
            contentDescription = stringResource(id = UIItem.title),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(400.dp)
                .aspectRatio(1 / 1.5f)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .border(4.dp, DarkPink)
        )
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .padding(10.dp)
                .background(DarkPink)
                .clip(CircleShape)
                .constrainAs(label) {
                    top.linkTo(image.top)
                    start.linkTo(image.start)
                }
        ) {
            Text(
                text = position.toString(),
                color = Color.White,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}