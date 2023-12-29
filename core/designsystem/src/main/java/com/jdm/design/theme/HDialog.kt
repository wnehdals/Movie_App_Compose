package com.jdm.design.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CommonDialog(
    title: String,
    message: String,
    leftText: String?,
    rightText: String,
    leftClick: (() -> Unit)?,
    rightClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss }) {
        Surface() {
            Box(
                modifier = Modifier
                    .border(
                        width = 0.dp,
                        color = JdmTheme.colors.gray200,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = title, style = JdmTheme.typography.L_M)
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        thickness = 1.dp,
                        color = JdmTheme.colors.gray300
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = message, style = JdmTheme.typography.H_L)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        if (!leftText.isNullOrEmpty()) {
                            if (leftClick != null) {
                                Button(
                                    onClick = leftClick,
                                    modifier = Modifier.weight(1.0f),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = JdmTheme.colors.gray100,
                                        contentColor = JdmTheme.colors.black
                                    )
                                ) {
                                    Text(text = leftText, style = JdmTheme.typography.L_M)
                                }
                                Spacer(modifier = Modifier.width(20.dp))
                            }
                        }
                        Button(
                            onClick = rightClick,
                            modifier = Modifier.weight(1.0f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = JdmTheme.colors.black,
                                contentColor = JdmTheme.colors.white
                            )
                        ) {
                            Text(text = rightText, style = JdmTheme.typography.L_M)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))


                }
            }

        }
    }
}

@Preview
@Composable
fun previewDialog() {
    JdmTheme {
        CommonDialog(
            title = "title",
            message = "It is message",
            leftText = "cancel",
            rightText = "confirm",
            leftClick = { },
            rightClick = { }) {

        }
    }
}