package com.example.bfit.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey

@Composable
fun BottomNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(horizontal = 34.dp, vertical = 8.dp),
        shape = RoundedCornerShape(34.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { (route, routeInfo) ->
                val isSelected = route == selectedKey
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        haptic.performHapticFeedback(
                            HapticFeedbackType.Confirm
                        )
                        onSelectKey(route)
                    },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) routeInfo.activeIcon else routeInfo.icon,
                            contentDescription = routeInfo.label
                        )
                    },
//                label = {
//                    Text(
//                        text = routeInfo.label,
//                        style = MaterialTheme.typography.labelSmallEmphasized
//                    )
//                }
                )
            }
        }
    }
//    NavigationBar(
//        modifier = Modifier
//            .navigationBarsPadding()
//            .padding(horizontal = 24.dp, vertical = 2.dp)
//            .clip(RoundedCornerShape(32.dp)),
//    ) {
//        TOP_LEVEL_DESTINATIONS.forEach { (route, routeInfo) ->
//            val isSelected = route == selectedKey
//            NavigationBarItem(
//                selected = isSelected,
//                onClick = {
//                    haptic.performHapticFeedback(
//                        HapticFeedbackType.Confirm
//                    )
//                    onSelectKey(route)
//                },
//                icon = {
//                    Icon(
//                        imageVector = if (isSelected) routeInfo.activeIcon else routeInfo.icon,
//                        contentDescription = routeInfo.label
//                    )
//                },
////                label = {
////                    Text(
////                        text = routeInfo.label,
////                        style = MaterialTheme.typography.labelSmallEmphasized
////                    )
////                }
//            )
//        }
//    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        selectedKey = Route.HomeRoute,
        onSelectKey = {}
    )
}

