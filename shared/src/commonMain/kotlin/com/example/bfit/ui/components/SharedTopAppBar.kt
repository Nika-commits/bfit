package com.example.bfit.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigation: () -> Unit,

    ) {

    val statusBarTopInset = TopAppBarDefaults.windowInsets.add(
        WindowInsets(
            top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding(),
        )
    )
    LargeTopAppBar(
        windowInsets = statusBarTopInset,
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            FilledTonalIconButton(
                onClick = onNavigation,
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SharedTopAppBarPreview() {
    Scaffold(
        topBar = {
            SharedTopAppBar(
                title = "Settings",
                onNavigation = {}
            )
        }
    ) {

    }
}