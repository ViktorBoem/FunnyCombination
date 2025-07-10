package com.example.funnycombination.ui.screens.splashScreenFeatures

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.funnycombination.ui.screens.splashScreenFeatures.splashScreenComponents.PulsingDotsIndicator
import com.example.funnycombination.ui.theme.CrystalWhite
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.SunshineYellow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomSplashScreen(navController: NavController) {
    val funnyScales = remember { "Funny".map { Animatable(0f) } }
    val funnyOffsets = remember { "Funny".map { Animatable(-100f) } }
    val combinationScales = remember { "Combination".map { Animatable(0f) } }
    val combinationOffsets = remember { "Combination".map { Animatable(-100f) } }

    var showPulsingDots by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        coroutineScope {
            animateWord(
                scales = funnyScales,
                offsets = funnyOffsets,
                delayPerLetter = 100L
            )

            showPulsingDots = true

            animateWord(
                scales = combinationScales,
                offsets = combinationOffsets,
                delayPerLetter = 80L
            )
        }

        delay(1000L)

        navController.navigate("main_menu") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CrystalWhite),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DisplayTextWord(
                text = "Funny",
                color = JuicyOrange,
                scales = funnyScales,
                offsets = funnyOffsets
            )

            DisplayTextWord(
                text = "Combination",
                color = SunshineYellow,
                scales = combinationScales,
                offsets = combinationOffsets
            )

            if (showPulsingDots) {
                Spacer(modifier = Modifier.height(32.dp))
                PulsingDotsIndicator()
            }
        }
    }
}

@Composable
private fun DisplayTextWord(
    text: String,
    color: Color,
    scales: List<Animatable<Float, AnimationVector1D>>,
    offsets: List<Animatable<Float, AnimationVector1D>>
) {
    Row(horizontalArrangement = Arrangement.Center) {
        text.forEachIndexed { index, char ->
            Text(
                text = char.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = color,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .scale(scales[index].value)
                    .offset(y = offsets[index].value.dp)
            )
        }
    }
}

private suspend fun animateWord(
    scales: List<Animatable<Float, AnimationVector1D>>,
    offsets: List<Animatable<Float, AnimationVector1D>>,
    delayPerLetter: Long
) {
    coroutineScope {
        scales.indices.forEach { index ->
            launch {
                delay(index * delayPerLetter)
                launch { scales[index].animateTo(1f, animationSpec = spring(0.4f, 500f)) }
                launch { offsets[index].animateTo(0f, animationSpec = spring(0.4f, 500f)) }
            }
        }
    }
}