package ru.alex3645.foundation.soluion

import android.os.Parcelable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

public class ComposableNavGraphBuilderDecorator(
    public val composableFactory: ComposableSolution.ComposableFactory
) {

    public fun NavGraphBuilder.simpleComposable(routeId: ComposableSolutionRoute.Id) {
        composable(
            route = routeId.value,
            content = composableFactory.create(routeId)
        )
    }

    public inline fun <reified T : Parcelable> NavGraphBuilder.composableWithConfig(
        routeId: ComposableSolutionRoute.Id
    ) {
        composable(
            route = "${routeId.value}/{$ROUTE_ARGUMENT_CONFIG}",
            arguments = T::class.navType.arguments,
            content = composableFactory.create(routeId)
        )
    }

    public companion object {
        public const val ROUTE_ARGUMENT_CONFIG: String = "config"

        @PublishedApi
        internal inline val <reified Config : Parcelable> NavType<Config>.arguments: List<NamedNavArgument>
            get() = listOf(navArgument(ROUTE_ARGUMENT_CONFIG) { type = this@arguments })
    }
}