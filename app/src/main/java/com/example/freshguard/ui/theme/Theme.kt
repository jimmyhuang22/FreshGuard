package com.example.freshguard.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = FreshGuardGreen,
    onPrimary = FreshGuardSurface,
    primaryContainer = FreshGuardGreenContainer,
    onPrimaryContainer = FreshGuardGreenDark,
    secondary = FreshGuardLavender,
    onSecondary = FreshGuardSurface,
    secondaryContainer = FreshGuardLavenderContainer,
    onSecondaryContainer = FreshGuardLavenderDark,
    tertiary = FreshGuardWarm,
    onTertiary = FreshGuardSurface,
    tertiaryContainer = FreshGuardWarmContainer,
    onTertiaryContainer = FreshGuardWarmText,
    background = FreshGuardBackground,
    onBackground = FreshGuardGreenDark,
    surface = FreshGuardSurface,
    onSurface = FreshGuardGreenDark,
    surfaceVariant = FreshGuardSurfaceVariant,
    onSurfaceVariant = FreshGuardLavenderDark,
    outline = FreshGuardOutline
)

private val DarkColorScheme = darkColorScheme(
    primary = FreshGuardGreenContainer,
    onPrimary = FreshGuardGreenDark,
    primaryContainer = FreshGuardGreen,
    onPrimaryContainer = FreshGuardSurface,
    secondary = FreshGuardLavenderContainer,
    onSecondary = FreshGuardLavenderDark,
    secondaryContainer = FreshGuardLavender,
    onSecondaryContainer = FreshGuardSurface,
    tertiary = FreshGuardWarmContainer,
    onTertiary = FreshGuardWarmText,
    tertiaryContainer = FreshGuardWarm,
    onTertiaryContainer = FreshGuardSurface,
    background = FreshGuardDarkBackground,
    onBackground = FreshGuardSurface,
    surface = FreshGuardDarkSurface,
    onSurface = FreshGuardSurface,
    surfaceVariant = FreshGuardDarkSurfaceVariant,
    onSurfaceVariant = FreshGuardLavenderContainer,
    outline = FreshGuardDarkOutline
)

@Composable
fun FreshGuardTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = FreshGuardShapes,
        content = content
    )
}
