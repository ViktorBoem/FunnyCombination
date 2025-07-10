package com.example.funnycombination.ui.screens.gameCombinationFeatures

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.funnycombination.data.model.GamePhase
import com.example.funnycombination.ui.screens.gameCombinationFeatures.gameCombinationComponents.EmojiButtonRow
import com.example.funnycombination.ui.screens.gameCombinationFeatures.gameCombinationComponents.GameCombinationDisplayCard
import com.example.funnycombination.ui.screens.gameCombinationFeatures.gameCombinationComponents.LevelIndicator
import com.example.funnycombination.ui.theme.CrystalWhite

@Composable
fun GameCombinationScreen(
    onGameOver: (Int, Boolean) -> Unit,
    viewModel: GameCombinationViewModel = hiltViewModel()
) {
    val gameState by viewModel.gameState.collectAsState()
    val currentEmoji by viewModel.currentEmojiDisplay.collectAsState()
    val message by viewModel.message.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startGame()
    }

    LaunchedEffect(gameState.phase) {
        if (gameState.phase == GamePhase.GAME_OVER) {
            onGameOver(gameState.level - 1, gameState.isNewRecord)
        }
    }

    Surface(
        color = CrystalWhite,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(16.dp)
        ) {
            LevelIndicator(level = gameState.level)

            Spacer(modifier = Modifier.weight(1f))

            GameCombinationDisplayCard(
                gameState = gameState,
                currentEmoji = currentEmoji,
                message = message
            )

            Spacer(modifier = Modifier.weight(1f))

            EmojiButtonRow(
                enabled = gameState.phase == GamePhase.PLAYER_TURN,
                onEmojiClick = { viewModel.onEmojiSelected(it) }
            )
        }
    }
}
