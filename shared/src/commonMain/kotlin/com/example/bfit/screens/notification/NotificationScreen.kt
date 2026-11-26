package com.example.bfit.screens.notification

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotificationScreen() {
    val statusBarTopInset = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
    Scaffold(
        topBar = {
            TopAppBar(
                contentPadding = PaddingValues(
                    top = statusBarTopInset,
                ),
                title = {
                    Text("Notifications")
                },
                navigationIcon = {
                    FilledTonalIconButton(
                        onClick = {},
                        shape = CircleShape
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                }
            )
        },
    ) {

    }
}

@Preview
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}