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
package com.example.androiddevchallenge.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.components.ChartLine
import com.example.androiddevchallenge.components.Divider
import com.example.androiddevchallenge.components.TextFieldCity
import com.example.androiddevchallenge.components.WeatherDayItem
import com.example.androiddevchallenge.components.WeatherTimeItem
import com.example.androiddevchallenge.data.FakeData
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues

@Composable
fun WeatherScreen(
    weather: Weather,
    brush: Brush = Brush.verticalGradient(
        listOf(
            MaterialTheme.colors.primaryVariant,
            MaterialTheme.colors.primary
        )
    ),
    onSubmit: (text: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        content = {
            LazyColumn(
                contentPadding = LocalWindowInsets.current.systemBars.toPaddingValues()
            ) {
                item {
                    TextFieldCity(onSubmit = onSubmit)
                }
                item {
                    WeatherTimeItem(
                        weather = weather,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                item {
                    Divider(
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                item {
                    ChartLine(
                        maxPaths = weather.maxTemperatures,
                        minPaths = weather.minTemperatures,
                        modifier = Modifier.height(200.dp),
                    )
                }
                item {
                    Divider(
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                items(weather.days) {
                    WeatherDayItem(
                        weather = it,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun WeatherScreenPreview() {
    MyTheme {
        ProvideWindowInsets {
            WeatherScreen(weather = FakeData.weather) {}
        }
    }
}

@Preview
@Composable
fun WeatherScreenDarkPreview() {
    MyTheme(true) {
        ProvideWindowInsets {
            WeatherScreen(weather = FakeData.weather) {}
        }
    }
}
