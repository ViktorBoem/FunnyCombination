package com.example.funnycombination.ui.screens.gameCombinationFeatures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnycombination.data.model.Emoji
import com.example.funnycombination.data.model.GamePhase
import com.example.funnycombination.data.model.GameState
import com.example.funnycombination.domain.usecase.CheckSequenceUseCase
import com.example.funnycombination.domain.usecase.GenerateSequenceUseCase
import com.example.funnycombination.domain.usecase.SaveHighScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameCombinationViewModel @Inject constructor(
    private val generateSequenceUseCase: GenerateSequenceUseCase,
    private val checkSequenceUseCase: CheckSequenceUseCase,
    private val saveHighScoreUseCase: SaveHighScoreUseCase
) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _currentEmojiDisplay = MutableStateFlow<Emoji?>(null)
    val currentEmojiDisplay: StateFlow<Emoji?> = _currentEmojiDisplay.asStateFlow()

    private val _message = MutableStateFlow("Memorize!")
    val message: StateFlow<String> = _message.asStateFlow()

    fun startGame() {
        _gameState.value = GameState(level = 1)
        startNewLevel()
    }

    fun startNewLevel() {
        viewModelScope.launch {
            _gameState.value = _gameState.value.copy(
                playerInputs = emptyList(),
                phase = GamePhase.DEMONSTRATING
            )

            val newSequence = generateSequenceUseCase.execute(_gameState.value.level)
            _gameState.value = _gameState.value.copy(currentSequence = newSequence)

            _message.value = "Memorize!"
            delay(1000)

            demonstrateSequence(newSequence)

            _message.value = "Your turn!"
            _gameState.value = _gameState.value.copy(phase = GamePhase.PLAYER_TURN)
        }
    }

    private suspend fun demonstrateSequence(sequence: List<Emoji>) {
        for (emoji in sequence) {
            _currentEmojiDisplay.value = emoji
            delay(1000)
            _currentEmojiDisplay.value = null
            delay(200)
        }
    }

    fun onEmojiSelected(emoji: Emoji) {
        if (_gameState.value.phase != GamePhase.PLAYER_TURN) return

        val updatedInputs = _gameState.value.playerInputs.toMutableList().apply { add(emoji) }
        _gameState.value = _gameState.value.copy(playerInputs = updatedInputs)

        val originalSequence = _gameState.value.currentSequence
        val playerInputs = updatedInputs

        for (i in playerInputs.indices) {
            if (playerInputs[i] != originalSequence[i]) {
                handleGameOver()
                return
            }
        }

        if (playerInputs.size == originalSequence.size) {
            viewModelScope.launch {
                delay(500)
                advanceToNextLevel()
            }
        }
    }

    private fun advanceToNextLevel() {
        _gameState.value = _gameState.value.copy(
            level = _gameState.value.level + 1
        )
        startNewLevel()
    }

    private fun handleGameOver() {
        viewModelScope.launch {
            val finalLevel = _gameState.value.level
            val isNewRecord = saveHighScoreUseCase.execute(finalLevel - 1)

            _gameState.value = _gameState.value.copy(
                phase = GamePhase.GAME_OVER,
                isNewRecord = isNewRecord
            )
        }
    }
}