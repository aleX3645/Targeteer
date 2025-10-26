package ru.alex3645.foundation.soluion

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow as CoroutineFlow

public object ComposableSolution {

    public interface Navigator {
        public fun navigate(route: ComposableSolutionRoute): Boolean

        @Deprecated("use backWithResult")
        public fun back()

        @Deprecated("use backWithResult")
        public fun back(routeId: ComposableSolutionRoute.Id, inclusive: Boolean)

        public fun backWithResult(): Boolean
        public fun backWithResult(routeId: ComposableSolutionRoute.Id, inclusive: Boolean): Boolean
        public fun navigateUp()
        public fun currentRoute(): ComposableSolutionRoute.Id?
    }

    public interface Coordinator<Input, Output> {
        public var navigator: Navigator?
        public var onOutput: ((Output) -> Unit)?

        public fun onCreate()
        public fun onInput(input: Input)
        public fun onDestroy()
    }

    public class Flow<I, O>(
        public val navigationBuilderProvider: NavigationBuilderProvider,
        private val coordinator: Coordinator<I, O>,
        private val io: IO<I, O>,
    ) : ViewModel() {

        public fun setNavigator(navigator: Navigator) {
            coordinator.navigator = navigator
            viewModelScope.launch {
                io.inputFlow.collect(coordinator::onInput)
            }
            coordinator.onOutput = { output ->
                viewModelScope.launch { io.output(output) }
            }
            coordinator.onCreate()
        }

        override fun onCleared() {
            coordinator.onOutput = null
            coordinator.navigator = null
            coordinator.onDestroy()
        }
    }

    public class FlowFactory<T : ViewModel>(
        private val creator: () -> T
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return creator() as T
        }
    }

    @Composable
    public fun <Input, Output> FlowContainer(
        startDestination: ComposableSolutionRoute.Id,
        flowFactory: FlowFactory<Flow<Input, Output>>,
        modifier: Modifier = Modifier
    ) {
        val flow: Flow<Input, Output> = viewModel(factory = flowFactory)
        val navController = rememberNavController()
        // TODO flow.setNavigator(NavHostControllerNavigator(navController))
        NavHost(
            navController = navController,
            startDestination = startDestination.value,
            builder = flow.navigationBuilderProvider.provide(),
            modifier = modifier
        )
    }

    public abstract class IO<Input, Output> {
        private val _inputFlow: MutableSharedFlow<Input> = MutableSharedFlow()

        /**
         * Важно учитывать, что в некоторых вью-моделях могут быть стартовые проверки,
         * после которых сразу отправляется output, что иногда опережает саму подписку
         * на обработку output'ов. Дабы избежать этого, можно использовать
         * [ComposableBaseScreenCoordinatorDelegate], в котором используется
         * Dispatchers.Main.immediate
         */
        private val _outputFlow: MutableSharedFlow<Output> = MutableSharedFlow()

        public val inputFlow: CoroutineFlow<Input> = _inputFlow
        public val outputFlow: CoroutineFlow<Output> = _outputFlow

        public suspend fun input(event: Input) {
            _inputFlow.emit(event)
        }

        public suspend fun output(event: Output) {
            _outputFlow.emit(event)
        }
    }

    public interface ComposableFactory {
        public fun create(
            routeId: ComposableSolutionRoute.Id
        ): @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    }

    public interface NavigationBuilderProvider {
        public fun provide(): NavGraphBuilder.() -> Unit
    }
}