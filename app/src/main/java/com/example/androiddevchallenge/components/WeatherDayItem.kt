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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.FakeData
import com.example.androiddevchallenge.data.WeatherDay
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun WeatherDayItem(
    weather: WeatherDay,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.body1,
    color: Color = MaterialTheme.colors.onPrimary
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .semantics(mergeDescendants = true) {
                contentDescription =
                    "The weather for ${weather.day} will be ${weather.time.text} with temperatures between ${weather.max} and ${weather.min}"
            }
    ) {
        Text(text = weather.day, style = style, color = color)
        Icon(
            painter = painterResource(id = weather.time.icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .width(style.fontSize.value.dp)
                .aspectRatio(1f)
        )
        WeatherDayMinMaxItem(
            min = weather.min,
            max = weather.max,
            modifier = Modifier.align(alignment = Alignment.CenterEnd),
            style = style,
            color = color
        )
    }
}

@Composable
fun WeatherDayMinMaxItem(
    min: Int,
    max: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.body1,
    color: Color = MaterialTheme.colors.onPrimary
) {
    Row(modifier = modifier) {
        Text(
            text = "$max",
            color = color,
            style = style
        )
        Spacer(modifier = Modifier.width(15.dp))
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium,
            LocalContentColor provides color
        ) {
            Text(
                text = "$min",
                style = style
            )
        }
    }
}

@Preview
@Composable
fun WeatherDayItemPreview() {
    MyTheme {
        WeatherDayItem(weather = FakeData.weather.days[0])
    }
}
