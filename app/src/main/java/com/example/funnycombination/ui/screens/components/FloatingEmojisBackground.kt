package com.example.funnycombination.ui.screens.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalWindowInfo

data class FloatingEmoji(
    val id: Int,
    val emoji: String,
    val x: Float,
    var y: Float,
    val size: Float,
    val speed: Float,
    val opacity: Float,
    var rotation: Float,
    val rotationSpeed: Float
)

@Composable
fun FloatingEmojisBackground() {
    val emojiSet = listOf(
        "😂", "👍", "🥳", "🤔", "🤯",
        "😍", "🥰", "😋", "😎", "🤩",
        "😊", "😄", "🤗", "🥺", "😌",
        "🤪", "🤭", "😉", "🙃", "😇"
    )
    var emojis by remember { mutableStateOf(emptyList<FloatingEmoji>()) }
    var nextId by remember { mutableIntStateOf(0) }

    val windowInfo = LocalWindowInfo.current
    val screenWidthPx = windowInfo.containerSize.width.toFloat()
    val screenHeightPx = windowInfo.containerSize.height.toFloat()

    fun createRandomEmoji(id: Int, startFromBottom: Boolean = false): FloatingEmoji {
        return FloatingEmoji(
            id = id,
            emoji = emojiSet.random(),
            x = Random.nextFloat() * 100f,
            y = if (startFromBottom) {
                Random.nextFloat() * 20f + 105f
            } else {
                Random.nextFloat() * 100f
            },
            size = Random.nextFloat() * 1.2f + 0.4f,
            speed = Random.nextFloat() * 0.4f + 0.05f,
            opacity = Random.nextFloat() * 0.35f + 0.08f,
            rotation = Random.nextFloat() * 360f,
            rotationSpeed = Random.nextFloat() * 0.6f + 0.1f
        )
    }

    LaunchedEffect(Unit) {
        emojis = List(35) { index ->
            createRandomEmoji(index, startFromBottom = false)
        }
        nextId = 35

        while (true) {
            delay(40)

            emojis = emojis.mapNotNull { emoji ->
                val newY = emoji.y - emoji.speed

                if (newY < -15f) {
                    null
                } else {
                    emoji.copy(
                        y = newY,
                        rotation = (emoji.rotation + emoji.rotationSpeed) % 360f
                    )
                }
            }

            val targetCount = 35
            val currentCount = emojis.size

            if (currentCount < targetCount) {
                val newEmojis = List(targetCount - currentCount) {
                    createRandomEmoji(nextId++, startFromBottom = true)
                }
                emojis = emojis + newEmojis
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        emojis.forEach { emoji ->
            FloatingEmojiItem(
                emoji = emoji,
                screenWidthPx = screenWidthPx,
                screenHeightPx = screenHeightPx
            )
        }
    }
}

@Composable
fun FloatingEmojiItem(
    emoji: FloatingEmoji,
    screenWidthPx: Float,
    screenHeightPx: Float
) {
    val density = LocalDensity.current

    val xOffset = with(density) { (screenWidthPx * emoji.x / 100f).toDp() }
    val yOffset = with(density) { (screenHeightPx * emoji.y / 100f).toDp() }

    Text(
        text = emoji.emoji,
        fontSize = (emoji.size * 28).sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .offset(x = xOffset, y = yOffset)
            .rotate(emoji.rotation)
            .graphicsLayer {
                alpha = emoji.opacity
            }
    )
}