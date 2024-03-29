package ar.edu.itba.hci.fiit_mobile.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFD7CE),
    secondary = Color(0xFFFF8B6F),
    tertiary = Color(0xFFFB6F5D),
    background = Color(0xFF202020),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF424242),
    error = Color(0xFFE90000),
    outlineVariant = Color(0xFF888880),
    outline = Color(0xFF000000),
    scrim = Color(0xFFFFFFFF),
    onSurface = Color(0x44000000)
    )

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFD7CE),
    secondary = Color(0xFFFFCCBD),
    tertiary = Color(0xFFFFA589),
    background = Color(0xFFFFF3F0),
    surface = Color(0xFFCEC5C3),
    onBackground = Color(0xFF000000),
    outlineVariant = Color(0xFF888880),
    outline = Color(0xFF000000),
    scrim = Color(0xDD000000),
    error = Color(0xFFE90000),
    onSurface = Color(0x22000000)
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun FiitmobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
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
        typography = Typography,
        content = content
    )
}