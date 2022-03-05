package com.akole.weddingapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.akole.weddingapp.ui.screens.splash.SplashScreen

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        setContent {
            SplashScreen(
                onDelayed = {
                    this.startActivity(intent)
                    finish()
                }
            )
        }
    }
}