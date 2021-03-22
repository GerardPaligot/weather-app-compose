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

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Sunshine(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = backgroundColor.copy(alpha = .2f), shape = CircleShape)
    ) {
        Rays(
            startRadius = Offset(.45f, 1.5f),
            animation = tween(1000, easing = LinearEasing),
            color = contentColor
        )
        Rays(
            startRadius = Offset(.10f, 1.1f),
            animation = tween(1000, easing = LinearEasing),
            color = contentColor
        )
        Sun(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    }
}

@Composable
internal fun Sun(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary
) {
    Box(
        modifier
            .fillMaxSize()
            .drawBehind {
                drawCircle(
                    color = contentColor,
                    radius = size.minDimension / 7
                )
                drawCircle(
                    color = backgroundColor,
                    radius = size.minDimension / 9
                )
            }
    )
}

@Composable
internal fun Rays(
    startRadius: Offset,
    animation: DurationBasedAnimationSpec<Float>,
    color: Color = MaterialTheme.colors.onPrimary
) {
    val infiniteTransition = rememberInfiniteTransition()
    val startRadiusPos by infiniteTransition.animateFloat(
        initialValue = startRadius.x,
        targetValue = startRadius.y,
        animationSpec = infiniteRepeatable(
            animation = animation,
            repeatMode = RepeatMode.Restart
        )
    )
    (-90 until 241 step 30).forEach {
        Ray(
            angle = it,
            startRadiusPos = startRadiusPos,
            color = color
        )
    }
}

@Composable
internal fun Ray(
    angle: Int,
    modifier: Modifier = Modifier,
    startRadiusPos: Float = .45f,
    strokeWidth: Float = 35f,
    color: Color = MaterialTheme.colors.onPrimary
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                val theta = angle * PI.toFloat() / 180f
                val startRadius = size.width / 2 * startRadiusPos
                val endRadius = size.width / 2 * (startRadiusPos + 0.15f)
                val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
                val endPos = Offset(cos(theta) * endRadius, sin(theta) * endRadius)
                drawLine(
                    color = color,
                    start = center + startPos,
                    end = center + endPos,
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Square
                )
            }
    )
}

@Preview
@Composable
fun RayPreview() {
    MyTheme {
        Sunshine()
    }
}
