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

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.FakeData
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
internal fun ChartLine(
    maxPaths: List<Int>,
    minPaths: List<Int>,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    lineWidth: Float = 4f
) {
    val brush = Brush.verticalGradient(listOf(color.copy(alpha = .2f), color.copy(.05f)))
    val maxPathElements = listOf(maxPaths[0]) + maxPaths + listOf(maxPaths[maxPaths.size - 1])
    val minPathElements = listOf(minPaths[0]) + minPaths + listOf(minPaths[minPaths.size - 1])
    val xSize = maxPathElements.lastIndex
    val ySize = maxPathElements.maxOf { it } + 3
    val minX = 1
    val minY = 0
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .semantics(mergeDescendants = true) {
                val temperaturesFormatted = maxPaths
                    .mapIndexed { index, point -> "max $point and min ${minPaths[index]}" }
                    .joinToString(", ")
                contentDescription = "Temperatures during the day will be $temperaturesFormatted"
            }
            .drawBehind {
                val strokePath = Path()
                val path = Path()
                maxPathElements.forEachIndexed { index, point ->
                    val x = (index + 1 - minX) * size.width / xSize
                    val y = (point - minY) * size.height / ySize
                    if (index == 0) {
                        path.moveTo(x, size.height - y)
                        strokePath.moveTo(x, size.height - y)
                    } else {
                        path.lineTo(x, size.height - y)
                        strokePath.lineTo(x, size.height - y)
                    }
                    if (index == 0 || index == minPathElements.lastIndex) return@forEachIndexed
                    drawCircle(
                        color = color,
                        radius = 5f,
                        center = Offset(x, size.height - y)
                    )
                }
                minPathElements
                    .reversed()
                    .forEachIndexed { index, point ->
                        val x =
                            ((minPathElements.lastIndex - index) + 1 - minX) * size.width / xSize
                        val y = (point - minY) * size.height / ySize
                        path.lineTo(x, size.height - y)
                        if (index == 0) {
                            strokePath.moveTo(x, size.height - y)
                        } else {
                            strokePath.lineTo(x, size.height - y)
                        }
                        if (index == 0 || index == minPathElements.lastIndex) return@forEachIndexed
                        drawCircle(
                            color = color,
                            radius = 5f,
                            center = Offset(x, size.height - y)
                        )
                    }
                path.lineTo(1f, size.height - maxPathElements[0])
                drawPath(path = path, brush = brush, style = Fill)
                drawPath(
                    path = strokePath,
                    color = color.copy(alpha = .5f),
                    style = Stroke(width = lineWidth)
                )
            },
        content = {
            val heightTextStyle = textStyle.fontSize.value.dp
            val textPadding = 10.dp
            maxPathElements.forEachIndexed { index, point ->
                if (index == 0) return@forEachIndexed
                val x = this.maxWidth / (maxPathElements.size - 1) * index
                val y = this.maxHeight - (this.maxHeight / ySize * point)
                Text(
                    text = "$point°",
                    modifier = Modifier
                        .padding(start = x, top = y)
                        .offset(y = -heightTextStyle - textPadding, x = (-7).dp),
                    color = color,
                    style = textStyle
                )
            }
            minPathElements.forEachIndexed { index, point ->
                if (index == 0) return@forEachIndexed
                val x = this.maxWidth / (minPathElements.size - 1) * index
                val y = this.maxHeight - (this.maxHeight / ySize * point)
                Text(
                    text = "$point°",
                    modifier = Modifier
                        .padding(start = x, top = y + (textPadding / 2))
                        .offset(x = (-3.5).dp),
                    color = color,
                    style = textStyle
                )
            }
        }
    )
}

@Preview
@Composable
fun ChartLinePreview() {
    MyTheme {
        ChartLine(
            maxPaths = FakeData.weather.maxTemperatures,
            minPaths = FakeData.weather.minTemperatures,
            modifier = Modifier.height(200.dp)
        )
    }
}
