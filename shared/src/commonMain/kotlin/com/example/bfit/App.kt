package com.example.bfit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.bfit.navigation.BottomNavigationBar
import com.example.bfit.navigation.Route
import com.example.bfit.navigation.navigationConfig
import com.example.bfit.screens.home.HomeScreen
import com.example.bfit.screens.profile.ProfileScreen
import com.example.bfit.screens.workouts.WorkoutScreen
import com.example.bfit.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val backStack = rememberNavBackStack(navigationConfig, Route.HomeRoute)

        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    modifier = Modifier,
                    selectedKey = backStack.last(),
                    onSelectKey = {
                        if (it == Route.HomeRoute) {
                            backStack.clear()
                            backStack.add(it)
                        }
                        backStack.remove(it)
                        backStack.add(it)
                    }
                )
            }
        ) { innerPadding ->
            NavDisplay(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),

                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    entry<Route.HomeRoute> {
                        HomeScreen()
                    }
                    entry<Route.WorkoutRoute> {
                        WorkoutScreen()
                    }
                    entry<Route.ProfileRoute> {
                        ProfileScreen()
                    }
                    entry<Route.Settings> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text("Settings")
                        }
                    }
                }
            )
        }
    }
}

