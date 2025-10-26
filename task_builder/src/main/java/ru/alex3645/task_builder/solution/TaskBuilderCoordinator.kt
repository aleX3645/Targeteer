package ru.alex3645.task_builder.solution

import ru.alex3645.soluion.ComposableBaseScreenCoordinatorDelegate
import ru.alex3645.foundation.soluion.ComposableSolution
import ru.alex3645.task_builder.ui.presentaion.TaskBuilderScreenIO

internal class TaskBuilderCoordinator(
    private val taskBuilderScreenIO: TaskBuilderScreenIO
): ComposableSolution.Coordinator<TaskBuilderInput, TaskBuilderOutput> {

    private val delegates: List<ComposableBaseScreenCoordinatorDelegate<*,*,TaskBuilderOutput>> =
        emptyList()

    override var navigator: ComposableSolution.Navigator? = null
    override var onOutput: ((TaskBuilderOutput) -> Unit)? = null

    override fun onCreate() {
        navigator?.let { navigator ->
            onOutput?.let { outputHandler ->
                delegates.forEach { delegate ->
                    delegate.onCreate(navigator, outputHandler)
                }
            }
        }
    }

    private fun handleTaskBuilderOutput(output: TaskBuilderOutput){}

    override fun onDestroy() {
        delegates.forEach{it.onDestroy()}
    }

    override fun onInput(input: TaskBuilderInput) = Unit
}