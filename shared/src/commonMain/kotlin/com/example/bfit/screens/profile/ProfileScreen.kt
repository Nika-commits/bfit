package com.example.bfit.screens.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen() {
    Scaffold { innerPadding ->
        val topInset = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        LazyColumn(
            contentPadding = PaddingValues(
                top = topInset
            )
        ) {
            item {
                Text("Profile")
            }

        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}