package com.beertracker.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Orange500,
    onPrimary = White,
    primaryContainer = Orange200,
    secondary = Orange700,
    background = Background,
    surface = White,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun BeerTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = BeerTrackerTypography,
        content = content
    )
}
