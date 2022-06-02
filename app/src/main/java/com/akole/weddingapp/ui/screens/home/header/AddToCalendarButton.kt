package com.akole.weddingapp.ui.screens.home.header

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.R

@Composable
internal fun AddToCalendarButton(
    onAddCalendarClicked: () -> Unit
) {
    Button(
        onClick = onAddCalendarClicked,
        modifier = Modifier.padding(vertical = 5.dp)) {
        Text(text = stringResource(id = R.string.add_to_calendar))
    }
}