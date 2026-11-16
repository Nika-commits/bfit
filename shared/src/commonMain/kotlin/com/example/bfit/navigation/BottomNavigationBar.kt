package com.example.bfit.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit
) {

    NavigationBar(
        modifier = modifier,
        windowInsets = NavigationBarDefaults.windowInsets,
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (route, routeInfo) ->
            NavigationBarItem(
                selected = route == selectedKey,
                onClick = {
                    onSelectKey(route)
                },
                label = {
                    Text(
                        routeInfo.label,
                    )
                },
                icon = {
                    AnimatedContent(
                        targetState = route == selectedKey,
                        transitionSpec = {
                            (fadeIn(tween(300)) + scaleIn(
                                initialScale = 0.8f,
                                animationSpec = tween(200)
                            )) togetherWith
                                    (fadeOut(tween(300)) + scaleOut(
                                        targetScale = 0.8f,
                                        animationSpec = tween(300)
                                    ))
                        },
                        label = "NavigationIcon"
                    ) { isSelected ->
                        Icon(
                            imageVector = if (isSelected) routeInfo.activeIcon else routeInfo.icon,
                            contentDescription = routeInfo.label
                        )
                    }
                },
            )
        }
    }
}