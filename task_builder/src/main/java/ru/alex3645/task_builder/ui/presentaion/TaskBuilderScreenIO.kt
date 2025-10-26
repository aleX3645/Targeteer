package ru.alex3645.task_builder.ui.presentaion

import ru.alex3645.foundation.soluion.ComposableSolution
import ru.alex3645.task_builder.solution.TaskBuilderInput
import ru.alex3645.task_builder.solution.TaskBuilderOutput
import javax.inject.Inject

internal sealed interface TaskBuilderScreenInput
internal sealed interface TaskBuilderScreenOutput

internal class TaskBuilderScreenIO @Inject constructor() :
        ComposableSolution.IO<TaskBuilderInput, TaskBuilderOutput>()