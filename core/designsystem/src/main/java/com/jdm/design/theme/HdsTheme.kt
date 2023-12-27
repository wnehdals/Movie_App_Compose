package com.jdm.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun JdmTheme(
    colors: HdsColors = JdmTheme.colors,
    typography: HdsTypography = JdmTheme.typography,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalColors provides colors,
    ) {
        content()
    }
}

object JdmTheme {
    val colors: HdsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val typography: HdsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}