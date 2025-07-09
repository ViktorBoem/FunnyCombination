package com.example.funnycombination.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = SunshineYellow,
    secondary = JuicyOrange,
    background = CrystalWhite,
    surface = CrystalWhite,
    onPrimary = DeepCharcoal,
    onSecondary = DeepCharcoal,
    onBackground = DeepCharcoal,
    onSurface = DeepCharcoal,
    error = WarningRed,
    onError = CrystalWhite
)

@Composable
fun FunnyCombinationTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicLightColorScheme(context)
        }

        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}