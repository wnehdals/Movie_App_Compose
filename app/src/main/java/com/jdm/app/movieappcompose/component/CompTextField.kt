package com.jdm.app.movieappcompose.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jdm.app.movieappcompose.ui.theme.C_252525
import com.jdm.app.movieappcompose.ui.theme.C_acb5bd



@Composable
fun BasicTextField(
    modifier: Modifier,
    text: String,
    maxLength: Int,
    hint: String,
    onInputChanged: (String) -> Unit,
    textState: TextInputState = getTextState(text = text, maxLength = maxLength),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    TextField(
        value = text,
        onValueChange = { onInputChanged(it) },
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = when (textState) {
                    TextInputState.EMPTY -> C_acb5bd
                    TextInputState.NORMAL -> C_252525
                    TextInputState.OVERFLOW -> C_252525
                },
                shape = RoundedCornerShape(8.dp)
            ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                color = C_acb5bd,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = C_252525
        )
    )
}
enum class TextInputState {
    EMPTY,
    OVERFLOW,
    NORMAL
}

fun getTextState(text: String, maxLength: Int) = when {
    text.isBlank() -> TextInputState.EMPTY
    text.length > maxLength -> TextInputState.OVERFLOW
    else -> TextInputState.NORMAL
}