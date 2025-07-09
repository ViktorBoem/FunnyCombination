package com.example.funnycombination.ui.screens.splashScreenFeatures.splashScreenComponents

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.SunshineYellow

@Composable
fun PulsingDotsIndicator() {
    val animationDuration = 1000
    val animationDelay = 200

    val infiniteTransition = rememberInfiniteTransition(label = "dots_transition")

    @Composable
    fun animateDotAlpha(delay: Int) = infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                delayMillis = delay,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dot_alpha"
    )

    val alpha1 by animateDotAlpha(0)
    val alpha2 by animateDotAlpha(animationDelay)
    val alpha3 by animateDotAlpha(animationDelay * 2)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .alpha(alpha1)
                .background(color = JuicyOrange, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .alpha(alpha2)
                .background(color = SunshineYellow, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .alpha(alpha3)
                .background(color = JuicyOrange, shape = CircleShape)
        )
    }
}