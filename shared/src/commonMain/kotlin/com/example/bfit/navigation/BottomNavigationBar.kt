package com.example.bfit.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey

@Composable
fun BottomNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit
) {

    NavigationBar(
        modifier = Modifier,
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (route, routeInfo) ->
            val isSelected = route == selectedKey
            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.05f else 1f,
                animationSpec = spring(
                    dampingRatio = 0.5f,
                    stiffness = 400f
                ),
                label = "NavigationIconScale"
            )
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onSelectKey(route)
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) routeInfo.activeIcon else routeInfo.icon,
                        contentDescription = routeInfo.label
                    )
                },
            )
        }
    }
}

