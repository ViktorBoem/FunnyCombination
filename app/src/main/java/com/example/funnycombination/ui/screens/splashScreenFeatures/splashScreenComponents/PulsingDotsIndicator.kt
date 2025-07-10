package com.example.funnycombination.ui.screens.splashScreenFeatures.splashScreenComponents

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.SunshineYellow

@Composable
fun PulsingDotsIndicator() {
    val animationDuration = 1000
    val pulsingDelay = 50
    val appearanceDelay = 50L

    val infiniteTransition = rememberInfiniteTransition(label = "dots_transition")

    val appearanceAnimations = remember {
        List(3) { Animatable(0f) }
    }

    LaunchedEffect(Unit) {
        appearanceAnimations.forEachIndexed { index, animatable ->
            kotlinx.coroutines.delay(index * appearanceDelay)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

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
    val alpha2 by animateDotAlpha(pulsingDelay)
    val alpha3 by animateDotAlpha(pulsingDelay * 2)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .scale(appearanceAnimations[0].value)
                .alpha(alpha1 * appearanceAnimations[0].value)
                .background(color = JuicyOrange, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .scale(appearanceAnimations[1].value)
                .alpha(alpha2 * appearanceAnimations[1].value)
                .background(color = SunshineYellow, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .scale(appearanceAnimations[2].value)
                .alpha(alpha3 * appearanceAnimations[2].value)
                .background(color = JuicyOrange, shape = CircleShape)
        )
    }
}