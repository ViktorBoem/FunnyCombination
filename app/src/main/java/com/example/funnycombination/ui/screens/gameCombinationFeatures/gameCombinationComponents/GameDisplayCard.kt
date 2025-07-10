package com.example.funnycombination.ui.screens.gameCombinationFeatures.gameCombinationComponents

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funnycombination.data.model.Emoji
import com.example.funnycombination.data.model.GamePhase
import com.example.funnycombination.data.model.GameState
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.custom

@Composable
fun GameCombinationDisplayCard(
    gameState: GameState,
    currentEmoji: Emoji?,
    message: String
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedContent(
                    targetState = message,
                    label = "messageAnimation",
                    transitionSpec = {
                        (slideInVertically { height -> height } + fadeIn())
                            .togetherWith(slideOutVertically { height -> -height } + fadeOut())
                    }
                ) { targetMessage ->
                    Text(
                        text = targetMessage,
                        style = MaterialTheme.typography.custom.cardTitle
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                GamePhaseContent(
                    gameState = gameState,
                    currentEmoji = currentEmoji
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GamePhaseContent(
    gameState: GameState,
    currentEmoji: Emoji?
) {
    AnimatedContent(
        targetState = gameState.phase,
        label = "contentSwitchAnimation",
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith
                    fadeOut(animationSpec = tween(300))
        }
    ) { targetPhase ->
        when (targetPhase) {
            GamePhase.DEMONSTRATING -> {
                AnimatedContent(
                    targetState = currentEmoji,
                    label = "emojiAnimation",
                    transitionSpec = {
                        (scaleIn(transformOrigin = TransformOrigin.Center, animationSpec = tween(300)) + fadeIn())
                            .togetherWith(scaleOut(transformOrigin = TransformOrigin.Center, animationSpec = tween(300)) + fadeOut())
                    }
                ) { targetEmoji ->
                    if (targetEmoji != null) {
                        Text(
                            text = targetEmoji.unicode,
                            fontSize = 72.sp
                        )
                    } else {
                        Box(modifier = Modifier.size(72.dp))
                    }
                }
            }
            GamePhase.PLAYER_TURN -> {
                FlowRow(
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    repeat(gameState.currentSequence.size) { index ->
                        val filled = index < gameState.playerInputs.size
                        val color = if (filled) JuicyOrange else Color.LightGray

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                .size(36.dp)
                                .background(color, CircleShape)
                        ) {
                            if (filled) {
                                Text(
                                    text = gameState.playerInputs[index].unicode,
                                    modifier = Modifier.align(Alignment.Center),
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
            else -> {
                Box(modifier = Modifier.height(72.dp))
            }
        }
    }
}