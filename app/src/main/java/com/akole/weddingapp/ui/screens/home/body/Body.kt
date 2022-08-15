package com.akole.weddingapp.ui.screens.home.body

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun Body() {
    WeddingDescription()
    WeddingPicks()
    Spacer(modifier = Modifier.height(16.dp))
    WeddingDetails()
    Spacer(modifier = Modifier.height(16.dp))
}