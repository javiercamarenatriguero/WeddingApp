package com.akole.weddingapp.ui.screens.home.header

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.R

@Composable
internal fun Title() {
    Text(
        text = stringResource(id = R.string.home_head_title),
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(4.dp),
    )
    Text(
        text = stringResource(id = R.string.left_title),
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(4.dp)
    )
}
