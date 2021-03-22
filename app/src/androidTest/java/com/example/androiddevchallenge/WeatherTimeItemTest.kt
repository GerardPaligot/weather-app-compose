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
package com.example.androiddevchallenge

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androiddevchallenge.components.WeatherTimeItem
import com.example.androiddevchallenge.data.FakeData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherTimeItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            WeatherTimeItem(
                weather = FakeData.weather
            )
        }
    }

    @Test
    fun checkIfCurrentDegreeHaveContentDescription() {
        composeTestRule
            .onNodeWithText("${FakeData.weather.current}°")
            .assertContentDescriptionEquals("${FakeData.weather.current}°")
    }

    @Test
    fun checkIfMinMaxDegreeHaveAdaptedContentDescription() {
        val max = FakeData.weather.max
        val min = FakeData.weather.min
        composeTestRule
            .onNodeWithContentDescription("Maximum temperature $max, Minimum temperature $min")
            .assertExists()
    }

    @Test
    fun checkIfFeelsWeatherHaveAdaptedContentDescription() {
        composeTestRule
            .onNodeWithContentDescription("${FakeData.weather.time.text}, feels like ${FakeData.weather.feels}")
            .assertExists()
    }
}
