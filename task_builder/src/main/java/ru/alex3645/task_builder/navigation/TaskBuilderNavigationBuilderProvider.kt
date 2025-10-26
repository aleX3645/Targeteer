package ru.alex3645.task_builder.navigation

import androidx.navigation.NavGraphBuilder
import ru.alex3645.foundation.soluion.ComposableNavGraphBuilderDecorator
import ru.alex3645.foundation.soluion.ComposableSolution

class TaskBuilderNavigationBuilderProvider(
    private val navGraphBuilderDecorator: ComposableNavGraphBuilderDecorator
): ComposableSolution.NavigationBuilderProvider {
    override fun provide(): NavGraphBuilder.() -> Unit  = {
        with(navGraphBuilderDecorator){
            simpleComposable(TaskBuilderSolutionRoute.TaskBuilderScreen.id)
        }
    }
}