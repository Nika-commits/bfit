package com.example.bfit

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import createDataStore

fun main() {
    val preferences = createDataStore()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Bfit",
        ) {
            App(
                preferences = preferences
            )
        }
    }
}
