package ru.aleX3645.targeteer.ui.presentation

import ru.aleX3645.targeteer.ui.presentation.screens.TrageteerNavigationScreen

internal sealed interface NavigationEvent {
    val destination: TrageteerNavigationScreen

    data object Profile : NavigationEvent {
        override val destination: TrageteerNavigationScreen = TrageteerNavigationScreen.Profile
    }
}