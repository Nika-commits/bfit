package com.example.bfit.components.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


enum class FabMenus(
    val label: String,
    val icon: ImageVector
) {
    WORKOUT("Workout", Icons.Default.SportsGymnastics),
    GYM_PAYMENT("Gym Payment", Icons.Default.Payment),
    PROGRESS("Progress", Icons.AutoMirrored.Default.TrendingUp),
    STOPWATCH("Stopwatch", Icons.Default.Timer)
}

@Composable
fun LogWorkoutFabButton(
    modifier: Modifier = Modifier,
    onItemClick: (FabMenus) -> Unit,
    showFab: Boolean
) {
    var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

    FloatingActionButtonMenu(
        expanded = fabMenuExpanded,
        modifier = modifier,
        button = {
            ToggleFloatingActionButton(
                modifier = Modifier.animateFloatingActionButton(
                    visible = showFab,
                    alignment = Alignment.BottomEnd,
                ),
                checked = fabMenuExpanded,
                onCheckedChange = { fabMenuExpanded = it }
            ) {
                Icon(
                    modifier = Modifier.animateIcon(
                        checkedProgress = { checkedProgress },
                    ),
                    imageVector = if (fabMenuExpanded) Icons.Outlined.Close else Icons.Outlined.Add,
                    contentDescription = "Fab Button",
                    tint = if (fabMenuExpanded) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    ) {
        FabMenus.entries.forEach { item ->
            FloatingActionButtonMenuItem(
                onClick = {
                    fabMenuExpanded = false
                    onItemClick(item)
                },
                text = { Text(item.label) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                }
            )
        }
    }
}

