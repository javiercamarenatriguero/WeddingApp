package com.akole.weddingapp.ui.screens.home.body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.R

@Composable
internal fun WeddingDescription() {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.description_title),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(2.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.description_text),
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )
    }
}