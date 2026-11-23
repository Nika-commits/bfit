package com.example.bfit.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemGestures
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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
    LargeFlexibleTopAppBar(
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