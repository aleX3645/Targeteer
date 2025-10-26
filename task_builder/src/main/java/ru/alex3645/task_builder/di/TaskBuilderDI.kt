package ru.alex3645.task_builder.di

import dagger.Component
import ru.alex3645.task_builder.solution.TaskBuilderContractSolutionFlow

@Component
internal interface TaskBuilderSolutionComponent {
    val flow: TaskBuilderContractSolutionFlow

    @Component.Factory
    interface Factory{
        fun create(): TaskBuilderSolutionComponent
    }
}