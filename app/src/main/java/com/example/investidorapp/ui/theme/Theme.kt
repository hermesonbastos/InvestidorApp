package com.example.investidorapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Definição de cores baseadas na cor primária #1D9627
private val GreenPrimary = Color(0xFF1D9627)
private val GreenDark = Color(0xFF166B1E)
private val GreenLight = Color(0xFF42B64E)

// Cores adicionais para fundo e superfície
private val BackgroundLight = Color(0xFFFFFBFE)
private val SurfaceLight = Color(0xFFFFFBFE)
private val OnPrimaryLight = Color.White
private val OnSecondaryLight = Color.White
private val OnTertiaryLight = Color.White
private val OnBackgroundLight = Color(0xFF1C1B1F)
private val OnSurfaceLight = Color(0xFF1C1B1F)

// Cores do tema escuro
private val BackgroundDark = Color(0xFF1C1B1F)
private val SurfaceDark = Color(0xFF1C1B1F)
private val OnPrimaryDark = Color.White
private val OnSecondaryDark = Color.White
private val OnTertiaryDark = Color.White
private val OnBackgroundDark = Color(0xFFFFFBFE)
private val OnSurfaceDark = Color(0xFFFFFBFE)

private val DarkColorScheme = darkColorScheme(
    primary = GreenDark,
    secondary = GreenPrimary,
    tertiary = GreenLight,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnPrimaryDark,
    onSecondary = OnSecondaryDark,
    onTertiary = OnTertiaryDark,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenLight,
    tertiary = GreenDark,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = OnPrimaryLight,
    onSecondary = OnSecondaryLight,
    onTertiary = OnTertiaryLight,
    onBackground = OnBackgroundLight,
    onSurface = OnSurfaceLight
)

@Composable
fun InvestidorAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
