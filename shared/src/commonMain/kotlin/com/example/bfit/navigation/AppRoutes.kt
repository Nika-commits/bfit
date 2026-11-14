package com.example.bfit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface Route

@Serializable
sealed interface TopLevelRoute : Route {
    val label: String
    val icon: ImageVector
    val activeIcon: ImageVector
    val contentDescription: String
}

@Serializable
data object HomeRoute : TopLevelRoute, NavKey {
    override val label = "Home"
    override val icon = Icons.Outlined.Home
    override val activeIcon = Icons.Filled.Home
    override val contentDescription = "Home"
}

@Serializable
data object WorkoutRoute : TopLevelRoute, NavKey {
    override val label = "Workout"
    override val icon = Icons.Outlined.FitnessCenter
    override val activeIcon = Icons.Filled.FitnessCenter
    override val contentDescription = "Workout"
}

@Serializable
data object ProfileRoute : TopLevelRoute, NavKey {
    override val label = "Profile"
    override val icon = Icons.Outlined.Person
    override val activeIcon = Icons.Filled.Person
    override val contentDescription = "Profile"
}

@Serializable
data object Settings : Route, NavKey

val TopLevelRoutes: List<TopLevelRoute> = listOf(HomeRoute, WorkoutRoute, ProfileRoute)

val navigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeRoute::class, HomeRoute.serializer())
            subclass(WorkoutRoute::class, WorkoutRoute.serializer())
            subclass(ProfileRoute::class, ProfileRoute.serializer())
        }
    }
}