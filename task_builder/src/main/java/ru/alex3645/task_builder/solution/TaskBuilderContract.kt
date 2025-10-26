package ru.alex3645.task_builder.solution

import ru.alex3645.foundation.soluion.ComposableSolution

typealias TaskBuilderContractSolutionFlow = ComposableSolution.Flow<TaskBuilderInput, TaskBuilderOutput>

class TaskBuilderIO: ComposableSolution.IO<TaskBuilderInput, TaskBuilderOutput>()

sealed interface TaskBuilderInput
sealed interface TaskBuilderOutput