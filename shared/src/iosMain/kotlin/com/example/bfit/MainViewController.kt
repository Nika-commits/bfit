package com.example.bfit

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import createDataStore

fun MainViewController() {
    ComposeUIViewController {
        App(
            preferences = remember {
                createDataStore()
            }
        )
    }
}