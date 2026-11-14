package com.example.bfit.theme

@androidx.compose.runtime.Composable
actual fun platformColorScheme(darkTheme: Boolean): androidx.compose.material3.ColorScheme {
    return if (darkTheme) DarkColorScheme else LightColorScheme
}