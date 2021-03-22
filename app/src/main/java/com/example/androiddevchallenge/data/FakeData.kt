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
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

enum class TimeWeather(@DrawableRes val icon: Int, val text: String) {
    SUN(R.drawable.sun, "Sunny"),
    RAIN(R.drawable.rain, "Rainy"),
    CLOUDY(R.drawable.cloudy, "Cloudy"),
    HAZY(R.drawable.haze, "Haze")
}

data class Weather(
    val current: Int,
    val min: Int,
    val max: Int,
    val feels: Int,
    val time: TimeWeather,
    val maxTemperatures: List<Int>,
    val minTemperatures: List<Int>,
    val days: List<WeatherDay>
)

data class WeatherDay(
    val day: String,
    val min: Int,
    val max: Int,
    val time: TimeWeather
)

object FakeData {
    val weather = Weather(
        current = 28,
        min = 17,
        max = 29,
        feels = 27,
        time = TimeWeather.SUN,
        maxTemperatures = arrayListOf(11, 10, 13, 13, 14, 13),
        minTemperatures = arrayListOf(4, 4, 4, 5, 7, 6),
        days = listOf(
            WeatherDay("Monday", 13, 31, TimeWeather.SUN),
            WeatherDay("Tuesday", 12, 28, TimeWeather.RAIN),
            WeatherDay("Wednesday", 13, 26, TimeWeather.RAIN),
            WeatherDay("Thursday", 10, 21, TimeWeather.CLOUDY),
            WeatherDay("Friday", 8, 17, TimeWeather.HAZY),
            WeatherDay("Saturday", 15, 25, TimeWeather.SUN),
        )
    )
}
