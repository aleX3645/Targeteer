package ru.aleX3645.targeteer.ui.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.aleX3645.targeteer.ui.presentation.NavigationEvent
import ru.aleX3645.targeteer.ui.presentation.screens.profile.ProfileScreen
import ru.aleX3645.targeteer.ui.presentation.screens.schedule.ScheduleScreen

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TrageteerNavigationScreen.startDestination.id,
    ) {
        TrageteerNavigationScreen.entries.forEach { screen ->
            composable(screen.id) {
                screen.screen(PaddingValues()) { event ->
                    when (event) {
                        is NavigationEvent.Profile -> navController.navigate(event.destination.id)
                    }
                }
            }
        }
    }
}

internal enum class TrageteerNavigationScreen(
    val id: String,
    val screen: @Composable (PaddingValues, (NavigationEvent) -> Unit) -> Unit
) {
    Schedule(
        id = "Schedule",
        screen = { _, onNavigationEvent -> ScheduleScreen(onNavigationEvent) }
    ),
    Profile(
        id = "Profile",
        screen = { _, onNavigationEvent -> ProfileScreen(onNavigationEvent) }
    );

    companion object {
        val startDestination get() = Schedule
    }
}