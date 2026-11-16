package com.example.bfit.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bfit.components.home.HomeAppBar

@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val listState = rememberLazyListState()
    val showFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 || !listState.canScrollForward
        }
    }


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            LogWorkoutFabButton(
                showFab = showFab,
                onClick = {}
            )
        },
        topBar = {
            HomeAppBar(
                "Pranish",
                scrollBehaviour = scrollBehavior,
                onCalendarClick = {},
                onNotificationClick = {},
                onProfileClick = {}
            )
        },
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(horizontal = 8.dp),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    "Home",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(4.dp)
                )
            }
            items((1..100).toList()) { item ->
                Text(
                    item.toString() + "1233333334566666666666",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(8.dp),
                )
            }
        }
    }
}

@Composable
fun LogWorkoutFabButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    showFab: Boolean
) {
    var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

    val items = listOf(
        FabMenuItems("Workout", Icons.Default.SportsGymnastics),
        FabMenuItems("Gym Payment", Icons.Default.Payment),
        FabMenuItems("Progress", Icons.Default.MonitorWeight),
        FabMenuItems("Stopwatch", Icons.Default.Timer)
    )

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
        items.forEach { item ->
            FloatingActionButtonMenuItem(
                onClick = {
                    fabMenuExpanded = false
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


data class FabMenuItems(
    val label: String,
    val icon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

