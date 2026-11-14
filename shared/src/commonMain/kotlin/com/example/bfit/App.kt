package com.example.bfit

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.example.bfit.navigation.HomeRoute
import com.example.bfit.navigation.ProfileRoute
import com.example.bfit.navigation.Route
import com.example.bfit.navigation.TopLevelRoute
import com.example.bfit.navigation.TopLevelRoutes
import com.example.bfit.navigation.WorkoutRoute
import com.example.bfit.screens.home.HomeScreen
import com.example.bfit.screens.profile.ProfileScreen
import com.example.bfit.screens.workouts.WorkoutScreen

@Composable
fun App() {
    MaterialTheme {
        val backStack: MutableList<Route> = rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(HomeRoute)
        }

        Scaffold(
            bottomBar = {
                BottomNavigationBar(topLevelRoutes = TopLevelRoutes, backStack = backStack)
            }
        ) {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<HomeRoute> {
                        HomeScreen()
                    }
                    entry<WorkoutRoute> {
                        WorkoutScreen()
                    }
                    entry<ProfileRoute> {
                        ProfileScreen()
                    }
                }
            )
        }
    }
}


@Composable
fun BottomNavigationBar(
    topLevelRoutes: List<TopLevelRoute>,
    backStack: MutableList<Route>
) {
    var selectedRoute = backStack.lastOrNull { it is TopLevelRoute } as? TopLevelRoute ?: HomeRoute

    NavigationBar(
        windowInsets = NavigationBarDefaults.windowInsets
    ) {
        topLevelRoutes.forEach { route ->
            NavigationBarItem(
                icon = {
                    AnimatedContent(
                        targetState = route == selectedRoute,
                        transitionSpec = {
                            (fadeIn(tween(200)) + scaleIn(
                                initialScale = 0.8f,
                                animationSpec = tween(200)
                            )) togetherWith
                                    (fadeOut(tween(150)) + scaleOut(
                                        targetScale = 0.8f,
                                        animationSpec = tween(150)
                                    ))
                        },
                        label = "NavigationIcon"
                    ) { isSelected ->
                        Icon(
                            imageVector = if (isSelected) route.activeIcon else route.icon,
                            contentDescription = route.contentDescription
                        )
                    }
                },
                label = {
                    Text(
                        route.label,
                    )
                },
                selected = route == selectedRoute,
                onClick = {
                    if (selectedRoute == route) return@NavigationBarItem
                    selectedRoute = route
                    backStack.remove(route)
                    backStack.add(route)
                },
            )
        }
    }
}