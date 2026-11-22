package com.example.bfit.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object HomeRoute : Route

    @Serializable
    data object WorkoutRoute : Route

    @Serializable
    data object ProgressRoute : Route

    @Serializable
    data object ProfileRoute : Route

    @Serializable
    data object Settings : Route
}


val BottomNavigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.HomeRoute::class, Route.HomeRoute.serializer())
            subclass(Route.ProgressRoute::class, Route.ProgressRoute.serializer())
            subclass(Route.WorkoutRoute::class, Route.WorkoutRoute.serializer())
        }
    }
}
