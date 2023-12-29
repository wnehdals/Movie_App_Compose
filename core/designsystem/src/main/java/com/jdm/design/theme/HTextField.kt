package com.jdm.design.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    onClickSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    Box(modifier = modifier.background(color = Color.Transparent)) {
        Box(
            modifier = if (!isFocused) {
                Modifier
                    .matchParentSize()
                    .border(
                        width = 1.dp,
                        color = JdmTheme.colors.gray200,
                        shape = RoundedCornerShape(8.dp)
                    )
            } else {
                Modifier
                    .matchParentSize()
                    .border(
                        width = 1.dp,
                        color = JdmTheme.colors.black,
                        shape = RoundedCornerShape(8.dp)
                    )
            }

        ) {
            TextField(
                modifier = Modifier.matchParentSize(),
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                maxLines = 1,
                textStyle = JdmTheme.typography.P_M,
                placeholder = {
                    Text(
                        text = hint,
                        style = TextStyle(
                            color = JdmTheme.colors.gray400,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                        )
                    )
                },
                interactionSource = interactionSource,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onClickSearch(text)
                        },
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                }

            )
        }
    }
}