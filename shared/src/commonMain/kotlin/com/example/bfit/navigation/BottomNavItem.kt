package com.example.bfit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val icon: ImageVector,
    val activeIcon: ImageVector,
    val label: String
)

val TOP_LEVEL_DESTINATIONS = mapOf(

    Route.HomeRoute to BottomNavItem(
        icon = Icons.Outlined.Home,
        activeIcon = Icons.Filled.Home,
        label = "Home"
    ),

    Route.WorkoutRoute to BottomNavItem(
        icon = Icons.Outlined.FitnessCenter,
        activeIcon = Icons.Filled.FitnessCenter,
        label = "Workouts"
    ),

    Route.ProgressRoute to BottomNavItem(
        icon = Icons.Outlined.Timeline,
        activeIcon = Icons.Filled.Timeline,
        label = "Progress"
    ),

    Route.Settings to BottomNavItem(
        icon = Icons.Outlined.Settings,
        activeIcon = Icons.Filled.Settings,
        label = "Settings"
    )
)