package ru.alex3645.task_builder.navigation

import ru.alex3645.foundation.soluion.ComposableSolutionRoute

class TaskBuilderSolutionRoute {
    data object TaskBuilderScreen: ComposableSolutionRoute {
        override val id: ComposableSolutionRoute.Id
            get() = ComposableSolutionRoute.Id("TaskBuilder.TaskBuilder")

        override val config: ComposableSolutionRoute.Config?
            get() = null
    }
}