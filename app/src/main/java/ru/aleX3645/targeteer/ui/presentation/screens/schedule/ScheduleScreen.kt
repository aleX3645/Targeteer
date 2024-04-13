package ru.aleX3645.targeteer.ui.presentation.screens.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.aleX3645.targeteer.ui.presentation.NavigationEvent

@Composable
internal fun ScheduleScreen(
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    Column {
        Text(
            text = "schedule"
        )
        Button(
            onClick = { onNavigationEvent(NavigationEvent.Profile) }
        ) {
            Text(
                text = "button"
            )
        }
    }
}