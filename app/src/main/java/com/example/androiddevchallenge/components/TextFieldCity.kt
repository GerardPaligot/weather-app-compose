/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun TextFieldCity(
    modifier: Modifier = Modifier,
    text: MutableState<String> = remember { mutableStateOf("") },
    textColor: Color = MaterialTheme.colors.onPrimary,
    shape: Shape = RoundedCornerShape(percent = 50),
    onSubmit: (text: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Place, contentDescription = null) },
        label = { Text(text = "Search Location") },
        shape = shape,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = textColor,
            textColor = textColor,
            leadingIconColor = textColor,
            focusedLabelColor = textColor,
            unfocusedLabelColor = textColor
        ),
        maxLines = 1,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus(true)
                onSubmit(text.value)
            }
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

@Preview
@Composable
fun TextFieldCityPreview() {
    MyTheme {
        TextFieldCity() {}
    }
}
