package com.akole.weddingapp.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R

@Composable
fun WeddingDetails() {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailBox(
            text = stringResource(id = R.string.date_title),
            imageVector = Icons.Default.DateRange
        )
        Spacer(modifier = Modifier.height(12.dp))
        DetailBox(
            text = stringResource(id = R.string.location_title),
            imageVector = Icons.Default.LocationOn
        )
    }
}

@Composable
private fun DetailBox(
    text: String,
    imageVector: ImageVector
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily.Cursive
        )
    }
}