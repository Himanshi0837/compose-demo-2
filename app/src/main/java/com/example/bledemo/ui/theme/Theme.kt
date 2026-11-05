package com.example.bledemo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = AccentBlue,
    background = BackgroundColor,
    surface = CardBackground,
    error = ErrorRed
)

@Composable
fun BLEDemoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}