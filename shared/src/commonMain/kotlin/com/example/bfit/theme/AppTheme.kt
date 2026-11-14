package com.example.bfit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

internal val LightColorScheme = lightColorScheme()
internal val DarkColorScheme = darkColorScheme()

@Composable
expect fun platformColorScheme(darkTheme: Boolean): ColorScheme

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = platformColorScheme(darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}