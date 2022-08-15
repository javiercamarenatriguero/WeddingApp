package com.akole.weddingapp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.ui.theme.Shapes

@Composable
fun CustomOutlinedTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    focusRequester: FocusRequester,
    onFocusEvent: (FocusState) -> Unit = {},
    onKeyboardNext: () -> Unit = {},
    onKeyboardDone: () -> Unit = {},
    label: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorText: String = "",
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it)},
            label = {
                Text(
                    text = label,
                    fontFamily = FontFamily.Cursive
                )
            },
            enabled = enabled,
            isError = isError,
            singleLine = singleLine,
            shape = Shapes.small,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black
            ),
            textStyle = TextStyle(
                fontFamily = FontFamily.Cursive,
                fontSize = 24.sp
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onNext = { onKeyboardNext() },
                onDone = { onKeyboardDone() },
            ),
            visualTransformation = visualTransformation,
            trailingIcon = {
                if (value.isNotEmpty() && enabled) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear text icon",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .clickable { onValueChange("") }
                    )
                }
            },
            leadingIcon = leadingIcon,
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusEvent { focusState ->
                    onFocusEvent(focusState)
                }
        )
        if (isError) {
            val errorTextPadding = 2.dp
            Text(
                text = errorText,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = errorTextPadding)
            )
        }
    }
}
