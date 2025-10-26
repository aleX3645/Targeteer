package ru.aleX3645.targeteer.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.aleX3645.targeteer.R
import ru.alex3645.task_builder.solution.TaskBuilderFlowContainer

data class NavigationTab(
    val icon: ImageVector,
    val contentDescriptionId: Int,
    val content: @Composable (PaddingValues) -> Unit
)

@Composable
fun MainScreen() {
    val navigationItems = provideNavigationItems()
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.width(380.dp),
        bottomBar = {
            NavigationBar {
                navigationItems.entries.forEachIndexed { index, navigationTab ->
                    val name = stringResource(navigationTab.value.contentDescriptionId)
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = navigationTab.value.icon,
                                contentDescription = name
                            )
                        },
                        label = {
                            Text(name)
                        },
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) { paddingValues ->
        navigationItems[selectedTabIndex]
            ?.content
            ?.invoke(paddingValues)
    }
}

private fun provideNavigationItems(): Map<Int, NavigationTab> =
    mapOf(
        0 to NavigationTab(
            icon = Icons.Filled.DateRange,
            contentDescriptionId = R.string.planning,
            content = { paddingValues ->
                //  Это должно уйти в di
                TaskBuilderFlowContainer().Flow(Modifier.padding(paddingValues))
            }
        ),
        1 to NavigationTab(
            icon = Icons.Filled.Share,
            contentDescriptionId = R.string.resources,
            content = {
                TODO()
            }
        ),
        2 to NavigationTab(
            icon = Icons.Filled.Settings,
            contentDescriptionId = R.string.settings,
            content = {
                TODO()
            }
        )
    )