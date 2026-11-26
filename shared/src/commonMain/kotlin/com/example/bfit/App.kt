package com.example.bfit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.bfit.navigation.BottomNavigationBar
import com.example.bfit.navigation.BottomNavigationConfig
import com.example.bfit.navigation.Route
import com.example.bfit.navigation.TOP_LEVEL_DESTINATIONS
import com.example.bfit.screens.home.HomeScreen
import com.example.bfit.screens.profile.ProfileScreen
import com.example.bfit.screens.progress.ProgressScreen
import com.example.bfit.screens.settings.SettingsScreen
import com.example.bfit.screens.workouts.WorkoutScreen
import com.example.bfit.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val backStack = rememberNavBackStack(
            BottomNavigationConfig,
            Route.HomeRoute
        )

        val tabs = TOP_LEVEL_DESTINATIONS.keys.toList()
        var slideDirection by remember { mutableStateOf(SlideDirection.RIGHT) }

        var bottomBarVisible by remember { mutableStateOf(true) }
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                    if (consumed.y < 0) {
                        bottomBarVisible = false
                    } else if (consumed.y > 0) {
                        bottomBarVisible = true
                    }
                    return Offset.Zero
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier.nestedScroll(nestedScrollConnection),
                bottomBar = {
//                    if (backStack.last() in TOP_LEVEL_DESTINATIONS) {
                    AnimatedVisibility(
                        visible = bottomBarVisible && backStack.last() in TOP_LEVEL_DESTINATIONS,
                        enter = slideInVertically { it },
                        exit = slideOutVertically { it }
                    ) {

                        BottomNavigationBar(
                            selectedKey = backStack.last(),
                            onSelectKey = {
                                slideDirection =
                                    if (tabs.indexOf(it) > tabs.indexOf(backStack.last())) SlideDirection.RIGHT
                                    else SlideDirection.LEFT

                                if (it == Route.HomeRoute) {
                                    backStack.clear()
                                    backStack.add(Route.HomeRoute)
                                } else {
                                    backStack.remove(it)
                                    backStack.add(it)
                                }
                            }
                        )
                    }
//                    }
                }
            ) { innerPadding ->
                NavDisplay(
                    modifier = Modifier
                        .padding(
//                            bottom = innerPadding.calculateBottomPadding(),
                        )
                        .consumeWindowInsets(innerPadding),
                    transitionSpec = { slide(slideDirection) },
                    popTransitionSpec = { slide(slideDirection) },
                    predictivePopTransitionSpec = {
                        slide(SlideDirection.LEFT)
                    },
                    backStack = backStack,
                    onBack = {
                        if (backStack.last() != Route.HomeRoute) {
                            backStack.clear()
                            backStack.add(Route.HomeRoute)
                        }
                    },
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = entryProvider {
                        entry<Route.HomeRoute> {
                            HomeScreen(
                                fabVisible = bottomBarVisible,
                                onNavigateToSettings = {
                                    slideDirection = SlideDirection.RIGHT
                                    backStack.add(Route.Settings)
                                },
                                onNavigateToProfile = {
                                    slideDirection = SlideDirection.RIGHT
                                    backStack.add(Route.ProfileRoute)
                                },
                                onNavigateToNotifications = {}
                            )
                        }
                        entry<Route.WorkoutRoute> {
                            WorkoutScreen()
                        }
                        entry<Route.ProfileRoute> {
                            ProfileScreen()
                        }
                        entry<Route.ProgressRoute> {
                            ProgressScreen()
                        }
                        entry<Route.Settings> {
                            SettingsScreen(
                                onGoBack = {
                                    slideDirection = SlideDirection.LEFT
                                    backStack.removeLastOrNull()
                                }
                            )
                        }
                    }
                )
            }
            StatusBarProtection(
                modifier = Modifier.align(Alignment.TopCenter)
            )
            NavigationBarProtection(
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

enum class SlideDirection {
    RIGHT, LEFT
}

fun slide(direction: SlideDirection): ContentTransform {
    return when (direction) {
        SlideDirection.RIGHT -> slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
        SlideDirection.LEFT -> slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
    }
}

@Composable
private fun StatusBarProtection(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceContainer,
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(
                with(LocalDensity.current) {
                    (WindowInsets.statusBars.getTop(this) * 1.2f).toDp()
                }
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 1f),
                        color.copy(alpha = 0.7f),
                        Color.Transparent
                    )
                )
            )
    )
}

@Composable
private fun NavigationBarProtection(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceContainer,
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(
                with(LocalDensity.current) {
                    (WindowInsets.navigationBars.getBottom(this)).toDp()
                }
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        color.copy(alpha = 0.5f),
                        color.copy(alpha = 1f),
                    )
                )
            )
    )
}