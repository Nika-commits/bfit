package com.example.bfit.components.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class FabMenus(
    val label: String,
    val icon: ImageVector
) {
    WORKOUT("Workout", Icons.Outlined.SportsGymnastics),
    GYM_PAYMENT("Gym Payment", Icons.Outlined.Payment),
    PROGRESS("Progress", Icons.Outlined.Analytics),
    STOPWATCH("Stopwatch", Icons.Outlined.Timer)
}

@Composable
fun LogWorkoutFabButton(
    onItemClick: (FabMenus) -> Unit,
    visible: Boolean
) {
    var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

    FloatingActionButtonMenu(
        modifier = Modifier
            .animateFloatingActionButton(
                visible = visible,
                alignment = Alignment.BottomEnd
            )
            .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 64.dp),
        expanded = fabMenuExpanded,
        button = {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                    if (fabMenuExpanded) {
                        TooltipAnchorPosition.Start
                    } else {
                        TooltipAnchorPosition.Above
                    }
                ),
                tooltip = {
                    PlainTooltip(
                        modifier = Modifier
                            .semantics {
                                liveRegion = LiveRegionMode.Assertive
                                paneTitle = "Toggle Menu"
                            }
                    ) {
                        Text("Toggle Menu")
                    }
                },
                state = rememberTooltipState()
            ) {
                val focusRequester = remember { FocusRequester() }
                val haptic = LocalHapticFeedback.current
                ToggleFloatingActionButton(
                    modifier = Modifier
                        .animateFloatingActionButton(
                            visible = visible || fabMenuExpanded,
                            alignment = Alignment.BottomEnd
                        )
                        .focusRequester(focusRequester = focusRequester),
                    containerColor = ToggleFloatingActionButtonDefaults.containerColor(
                        initialColor = MaterialTheme.colorScheme.tertiaryContainer,
                        finalColor = MaterialTheme.colorScheme.tertiary
                    ),
                    containerSize = ToggleFloatingActionButtonDefaults.containerSize(
                        initialSize = 64.dp,
                        finalSize = 64.dp
                    ),
                    checked = fabMenuExpanded,
                    onCheckedChange = {
                        haptic.performHapticFeedback(
                            if(fabMenuExpanded) HapticFeedbackType.ToggleOff else HapticFeedbackType.ToggleOn
                        )
                        fabMenuExpanded = !fabMenuExpanded
                    }
                ) {

                    Icon(
                        modifier = Modifier
                            .animateIcon(
                                checkedProgress = { checkedProgress },
                                color = ToggleFloatingActionButtonDefaults.iconColor(
                                    initialColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    finalColor = MaterialTheme.colorScheme.onTertiary
                                ),
                            )
                            .graphicsLayer {
                                rotationZ = 45f * checkedProgress
                                scaleX = if (fabMenuExpanded) 1.15f else 1f
                                scaleY = if (fabMenuExpanded) 1.15f else 1f
                            },
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        FabMenus.entries.forEach { item ->
            FloatingActionButtonMenuItem(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                onClick = {
                    fabMenuExpanded = false
                    onItemClick(item)
                },
                text = {
                    Text(
                        item.label,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
            )
        }
    }
}

