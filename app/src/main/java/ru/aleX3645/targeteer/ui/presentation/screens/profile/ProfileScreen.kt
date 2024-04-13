package ru.aleX3645.targeteer.ui.presentation.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.aleX3645.targeteer.ui.presentation.NavigationEvent

@Composable
internal fun ProfileScreen(
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    Text(
        text = "Hello profile"
    )
}