package com.example.funnycombination.ui.screens.gameResultFetures.gameResultComponents

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.SunshineYellow
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun ConfettiAnimation() {
    val density = LocalDensity.current
    val confettiPieces = remember {
        List(50) { ConfettiPiece() }
    }

    val animationProgress by rememberInfiniteTransition(label = "confetti").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "confettiProgress"
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val canvasHeight = size.height
        val canvasWidth = size.width

        confettiPieces.forEach { piece ->
            val progress = (animationProgress + piece.delay) % 1f
            val y = -50f + (canvasHeight + 100f) * progress
            val x = piece.startX * canvasWidth + sin(progress * piece.swayAmount) * piece.swayStrength

            if (progress > 0f && progress < 1f) {
                rotate(
                    degrees = progress * piece.rotationSpeed,
                    pivot = androidx.compose.ui.geometry.Offset(x, y)
                ) {
                    drawRect(
                        color = piece.color,
                        topLeft = androidx.compose.ui.geometry.Offset(x - piece.size / 2, y - piece.size / 2),
                        size = androidx.compose.ui.geometry.Size(piece.size, piece.size)
                    )
                }
            }
        }
    }
}

private data class ConfettiPiece(
    val startX: Float = Random.nextFloat(),
    val size: Float = Random.nextFloat() * 20f + 10f,
    val color: Color = listOf(
        JuicyOrange,
        SunshineYellow,
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFF9C27B0),
        Color(0xFFE91E63)
    ).random(),
    val swayAmount: Float = Random.nextFloat() * 10f + 5f,
    val swayStrength: Float = Random.nextFloat() * 100f + 50f,
    val rotationSpeed: Float = Random.nextFloat() * 720f + 360f,
    val delay: Float = Random.nextFloat() * 0.5f
)