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

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androiddevchallenge.components.ChartLine
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChartLineTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkIfContentDescriptionContainsMaxAndMinTemperatures() {
        composeTestRule.setContent {
            ChartLine(
                maxPaths = listOf(10, 11),
                minPaths = listOf(3, 4)
            )
        }
        val contentDescription =
            "Temperatures during the day will be max 10 and min 3, max 11 and min 4"
        composeTestRule
            .onNodeWithContentDescription(contentDescription)
            .assertExists()
    }
}
