package com.example.bfit.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun platformColorScheme(darkTheme: Boolean): ColorScheme {
    return if (darkTheme) DarkColorScheme else LightColorScheme
}