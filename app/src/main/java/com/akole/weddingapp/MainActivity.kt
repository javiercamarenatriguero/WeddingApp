package com.akole.weddingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.akole.weddingapp.ui.screens.WeddingAppContent
import com.akole.weddingapp.ui.theme.WeddingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeddingApp {
                WeddingAppContent()
            }
        }
    }
}

@Composable
private fun WeddingApp(content: @Composable () -> Unit) {
    WeddingAppTheme {
        Surface(color = MaterialTheme.colors.background){
            content()
        }
    }
}
