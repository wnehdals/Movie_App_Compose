package com.jdm.design.theme

import android.util.Log
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun HToggleButton(
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit = icon,
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = IconButtonDefaults.iconToggleButtonColors(
            checkedContentColor = JdmTheme.colors.black,
            contentColor = JdmTheme.colors.gray300,
        )
    ) {
        if (checked) checkedIcon() else icon()
    }
}