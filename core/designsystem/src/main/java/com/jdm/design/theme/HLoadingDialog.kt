package com.jdm.design.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Surface() {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 0.dp,
                        color = JdmTheme.colors.gray200,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

        }
    }
}

@Preview
@Composable
fun previewLoadingDialog() {
    JdmTheme {
        LoadingDialog {

        }
    }
}