package ru.aleX3645.targeteer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.aleX3645.targeteer.ui.presentation.screens.MainScreen
import ru.aleX3645.targeteer.ui.theme.TargeteerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TargeteerTheme {
                MainScreen()
            }
        }
    }
}