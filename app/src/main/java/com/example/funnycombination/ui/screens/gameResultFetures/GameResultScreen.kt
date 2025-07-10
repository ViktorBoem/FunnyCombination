package com.example.funnycombination.ui.screens.gameResultFetures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.funnycombination.ui.screens.components.FulledButton
import com.example.funnycombination.ui.screens.components.OutlinedButton
import com.example.funnycombination.ui.screens.gameResultFetures.gameResultComponents.ConfettiAnimation
import com.example.funnycombination.ui.theme.CrystalWhite
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.custom

@Composable
fun GameResultScreen(
    score: Int,
    isNewRecord: Boolean,
    onPlayAgain: () -> Unit,
    onMainMenu: () -> Unit
) {
    Surface(
        color = CrystalWhite,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isNewRecord) {
                ConfettiAnimation()
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "Game Over!",
                            style = MaterialTheme.typography.custom.cardTitle,
                            color = JuicyOrange
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "$score",
                            style = MaterialTheme.typography.custom.ScoreResult
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (isNewRecord) {
                            Text(
                                text = "New Record! 🎉",
                                style = MaterialTheme.typography.custom.cardContent
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        FulledButton(
                            text = "🎮 Play Again",
                            onClick = onPlayAgain,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        OutlinedButton(
                            text = "🏠 Main Menu",
                            onClick = onMainMenu,
                        )
                    }
                }
            }
        }
    }
}