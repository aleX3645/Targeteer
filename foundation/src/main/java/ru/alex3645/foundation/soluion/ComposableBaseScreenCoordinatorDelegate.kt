package ru.alex3645.foundation.soluion

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

public abstract class ComposableBaseScreenCoordinatorDelegate<ScreenInput, ScreenOutput, SolutionOutput>(
    private val io: ComposableSolution.IO<ScreenInput, ScreenOutput>,
    protected val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
) {

    public fun onInput(input: ScreenInput) {
        scope.launch { io.input(input) }
    }

    public fun onCreate(
        navigator: ComposableSolution.Navigator,
        solutionOutputHandler: (SolutionOutput) -> Unit
    ) {
        scope.launch {
            io.outputFlow.collect { output ->
                handleScreenOutput(output, navigator, solutionOutputHandler)
            }
        }
    }

    public fun onDestroy() {
        scope.cancel()
    }

    public abstract fun handleScreenOutput(
        screenOutput: ScreenOutput,
        solutionNavigator: ComposableSolution.Navigator,
        solutionOutputHandler: (SolutionOutput) -> Unit
    )
}