package com.example.vitahabit.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = VitaHabitYellow,
    onPrimary = VitaHabitWhite,
    secondary = VitaHabitGrey,
    onSecondary = VitaHabitWhite,
    tertiary = VitaHabitOrange,
    onTertiary = VitaHabitWhite,
    background = VitaHabitDarkGray,
    surface = VitaHabitMediumGray,
    onBackground = VitaHabitGrayWhite,
    onPrimaryContainer = VitaHabitLightGrey,
    onSurface = VitaHabitGrayWhite,
    surfaceVariant = VitaHabitLightGrey2,
    outline = VitaHabitTopBarDivider,
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
            window.statusBarColor = Color.Black.toArgb()

            // The corrected line: status bar icons should be light
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}