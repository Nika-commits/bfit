package com.example.bfit.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToNotifications:() -> Unit
) {
    val listState = rememberLazyListState()
    val showFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 || !listState.canScrollForward
        }
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        floatingActionButton = {
            LogWorkoutFabButton(
                visible = showFab,
                onItemClick = ::handleFabButtonCallback
            )
        },
        topBar = {
            HomeAppBar(
                username = "Pranish",
                scrollBehaviour = scrollBehavior,
                onChangelogsClick = {},
                onSettingsClick = onNavigateToSettings,
                onNotificationClick = onNavigateToNotifications,
                onProfileClick = onNavigateToProfile
            )
        },
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),

                ),
            modifier = Modifier
                .fillMaxWidth()
                .systemGesturesPadding(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(100) { index ->
                Text(
                    text = (index + 1).toString(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

fun handleFabButtonCallback(option: FabMenus) {
    when (option) {
        FabMenus.WORKOUT -> {

        }

        FabMenus.GYM_PAYMENT -> {

        }

        FabMenus.STOPWATCH -> {

        }

        FabMenus.PROGRESS -> {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToSettings = {},
        onNavigateToProfile = {},
        onNavigateToNotifications = {}
    )
}

