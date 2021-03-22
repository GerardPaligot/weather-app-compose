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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.FakeData
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun WeatherTimeItem(
    weather: Weather,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary
) {
    val size = 360.dp
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(size)
    ) {
        Sunshine(
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .size(size)
                .offset(x = size / 2),
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.CenterVertically)
        ) {
            Text(
                text = "${weather.current}°",
                style = MaterialTheme.typography.h1,
                color = contentColor,
                modifier = Modifier.semantics { contentDescription = "${weather.current}°" }
            )
            WeatherMaxMinTime(max = weather.max, min = weather.min)
        }
        WeatherFeelTime(
            feels = weather.feels,
            time = weather.time.text,
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp),
            color = contentColor
        )
    }
}

@Composable
internal fun WeatherMaxMinTime(
    max: Int,
    min: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .semantics(mergeDescendants = true) {
                contentDescription = "Maximum temperature $max, Minimum temperature $min"
            }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowUpward,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = color
        )
        Text(
            text = "$max°",
            color = color
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Filled.ArrowDownward,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = color
        )
        Text(
            text = "$min°",
            color = color
        )
    }
}

@Composable
internal fun WeatherFeelTime(
    feels: Int,
    time: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary
) {
    Column(
        modifier = modifier.semantics(true) {
            contentDescription = "$time, feels like $feels"
        }
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.subtitle1,
            color = color
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium,
            LocalContentColor provides color
        ) {
            Text(
                text = "Feels like $feels°",
                color = color
            )
        }
    }
}

@Preview
@Composable
fun WeatherTimeItemPreview() {
    MyTheme(false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant),
            content = {
                WeatherTimeItem(FakeData.weather)
            }
        )
    }
}
