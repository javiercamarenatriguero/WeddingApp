package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.ui.theme.Shapes

@Composable
fun CustomOutlinedTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
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
                Text( text = label)
            },
            enabled = enabled,
            isError = isError,
            singleLine = singleLine,
            shape = Shapes.small,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black
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
            leadingIcon = leadingIcon
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