package ru.alex3645.task_builder.solution

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.alex3645.foundation.soluion.ComposableNavGraphBuilderDecorator
import ru.alex3645.foundation.soluion.ComposableSolution
import ru.alex3645.task_builder.navigation.TaskBuilderNavigationBuilderProvider
import ru.alex3645.task_builder.navigation.TaskBuilderSolutionRoute
import ru.alex3645.task_builder.ui.presentaion.TaskBuilderScreenIO

class TaskBuilderFlowContainer {

    @Composable
    fun Flow(modifier: Modifier){
        ComposableSolution.FlowContainer(
            startDestination = TaskBuilderSolutionRoute.TaskBuilderScreen.id,
            flowFactory = ComposableSolution.FlowFactory {
                //TODO заменить на реализцию из daggger
                TaskBuilderContractSolutionFlow(
                    navigationBuilderProvider = TaskBuilderNavigationBuilderProvider(
                        navGraphBuilderDecorator = ComposableNavGraphBuilderDecorator(
                            TaskBuilderSolutionFactory()
                        )
                    ),
                    coordinator = TaskBuilderCoordinator(TaskBuilderScreenIO()),
                    io = TaskBuilderScreenIO()
                )
            },
            modifier = modifier
        )
    }
}