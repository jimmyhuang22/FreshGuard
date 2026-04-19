package com.example.freshguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.freshguard.ui.FreshGuardApp
import com.example.freshguard.ui.theme.FreshGuardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreshGuardTheme {
                FreshGuardApp()
            }
        }
    }
}