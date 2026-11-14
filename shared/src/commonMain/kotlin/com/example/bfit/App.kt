package com.example.bfit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import androidx.savedstate.compose.serialization.serializers.SnapshotStateMapSerializer
import bfit.shared.generated.resources.Res
import bfit.shared.generated.resources.compose_multiplatform
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
        val backStack: MutableList<Route> = rememberSerializable(serializer = SnapshotStateListSerializer()){
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
                    entry<HomeRoute>{
                        HomeScreen()
                    }
                    entry<WorkoutRoute> {
                        WorkoutScreen()
                    }
                    entry<ProfileRoute>{
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
){
    var selectedRoute by remember{mutableStateOf<TopLevelRoute>(HomeRoute)}
    NavigationBar (
        windowInsets = NavigationBarDefaults.windowInsets
    ){
        topLevelRoutes.forEach { route ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = route.contentDescription
                    )
                },
                label = {
                    Text(
                        route.label,
                    )
                },
                selected = route == selectedRoute,
                onClick = {
                    selectedRoute = route
                    backStack.add(route)
                }
            )
        }
        }
}