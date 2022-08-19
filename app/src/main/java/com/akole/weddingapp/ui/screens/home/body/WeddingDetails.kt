package com.akole.weddingapp.ui.screens.home.body

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.Constants.DATETIME_FORMAT
import com.akole.weddingapp.Constants.WEDDING_TIMESTAMP
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.utils.formatTimestamp

@Composable
internal fun WeddingDetails() {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailBox(
            text = WEDDING_TIMESTAMP.formatTimestamp(DATETIME_FORMAT),
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.body2
        )
    }
}
