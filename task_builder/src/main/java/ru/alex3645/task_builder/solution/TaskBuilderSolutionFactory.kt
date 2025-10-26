package ru.alex3645.task_builder.solution

import ru.alex3645.foundation.soluion.ComposableSolution
import javax.inject.Inject
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import ru.alex3645.foundation.soluion.ComposableSolutionRoute
import ru.alex3645.task_builder.navigation.TaskBuilderSolutionRoute
import ru.alex3645.task_builder.ui.presentaion.TaskBuilderScreen

class TaskBuilderSolutionFactory @Inject constructor(): ComposableSolution.ComposableFactory {
    override fun create(routeId: ComposableSolutionRoute.Id): @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit =
        { navBackStackEntry ->
            when (routeId) {
                TaskBuilderSolutionRoute.TaskBuilderScreen.id ->
                    TaskBuilderScreen()

                else -> {}
            }
        }
}