package com.example.bfit.components.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
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
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class FabMenus(
    val label: String,
    val icon: ImageVector
) {
    WORKOUT("Workout", Icons.Default.SportsGymnastics),
    GYM_PAYMENT("Gym Payment", Icons.Filled.Payment),
    PROGRESS("Progress", Icons.Default.Analytics),
    STOPWATCH("Stopwatch", Icons.Default.Timer)
}

@Composable
fun LogWorkoutFabButton(
    onItemClick: (FabMenus) -> Unit,
    visible: Boolean
) {
    var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

    FloatingActionButtonMenu(
        modifier = Modifier.animateFloatingActionButton(
            visible = visible,
            alignment = Alignment.BottomEnd
        ),
        expanded = fabMenuExpanded,
        button = {
            ToggleFloatingActionButton(

                containerSize = ToggleFloatingActionButtonDefaults.containerSize(64.dp),
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
                text = {
                    Text(
                        item.label,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                },
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

