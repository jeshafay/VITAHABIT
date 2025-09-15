package com.example.vitahabit.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = VitaHabitOrange,
    onPrimary = VitaHabitWhite,
    secondary = VitaHabitLightGrey,
    onSecondary = VitaHabitWhite,
    tertiary = VitaHabitYellow,
    onTertiary = VitaHabitWhite,
    background = VitaHabitDarkGray,
    surface = VitaHabitMediumGray,
    onBackground = VitaHabitGrayWhite,
    onSurface = VitaHabitGrayWhite
)

private val DarkColorScheme = LightColorScheme

@Composable
fun VitaHabitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        // You can add your Typography.kt and Shapes.kt back here later
        content = content
    )
}