package com.example.bfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.bfit.navigation.BottomNavigationBar
import com.example.bfit.navigation.BottomNavigationConfig
import com.example.bfit.navigation.Route
import com.example.bfit.screens.home.HomeScreen
import com.example.bfit.screens.profile.ProfileScreen
import com.example.bfit.screens.workouts.WorkoutScreen
import com.example.bfit.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme {
        val backStack = rememberNavBackStack(
            BottomNavigationConfig,
            Route.HomeRoute
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        selectedKey = backStack.last(),
                        onSelectKey = {
                            if (it == Route.HomeRoute) {
                                backStack.clear()
                                backStack.add(it)
                            } else {
                                backStack.remove(it)
                                backStack.add(it)
                            }
                        }
                    )
                }
            ) { innerPadding ->
                NavDisplay(
                    modifier = Modifier
                        .padding(
                            bottom = innerPadding.calculateBottomPadding(),
                        )
                        .consumeWindowInsets(innerPadding),
                    backStack = backStack,
                    onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
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
            StatusBarProtection(
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
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
                        color.copy(alpha = 0.5f),
                        Color.Transparent
                    )
                )
            )
    )
}