package com.nxide.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val NxDarkColorScheme = darkColorScheme(
    primary = NxGreen,
    secondary = NxBlue,
    tertiary = NxPurple,
    background = NxBgPrimary,
    surface = NxBgSecondary,
    surfaceVariant = NxBgTertiary,
    onPrimary = NxBgPrimary,
    onSecondary = NxBgPrimary,
    onTertiary = NxBgPrimary,
    onBackground = NxTextPrimary,
    onSurface = NxTextPrimary,
    onSurfaceVariant = NxTextSecondary,
    outline = NxBorder,
    error = NxRed,
    onError = NxTextPrimary,
    inverseSurface = NxTextPrimary,
    inverseOnSurface = NxBgPrimary,
)

@Composable
fun NXIDETheme(
    content: @Composable () -> Unit
) {
    val colorScheme = NxDarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = NxTypography,
        content = content
    )
}
