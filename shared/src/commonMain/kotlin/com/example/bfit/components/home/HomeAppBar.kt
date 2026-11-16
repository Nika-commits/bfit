package com.example.bfit.components.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeAppBar(
    username: String,
    scrollBehaviour: TopAppBarScrollBehavior? = null,
    onCalendarClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val statusBars = WindowInsets.statusBars

    val appBarInsets = WindowInsets(
        left = statusBars.getLeft(density, layoutDirection),
        top = statusBars.getTop(density) + with(density) { 12.dp.roundToPx() },
        right = statusBars.getRight(density, layoutDirection),
        bottom = statusBars.getBottom(density)
    )

    LargeTopAppBar(
        windowInsets = appBarInsets,
        title = {
            Text(
                "Hello, $username"
            )
        },
        actions = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.CalendarMonth),
                    contentDescription = username
                )
            }

            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.Notifications),
                    contentDescription = "Notification Button"
                )
            }
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.Person),
                    contentDescription = username
                )
            }

        },
        scrollBehavior = scrollBehaviour
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeAppBarPreview() {
    HomeAppBar(
        username = "Pranish",
        onCalendarClick = {},
        onProfileClick = {},
        onNotificationClick = {}
    )
}
