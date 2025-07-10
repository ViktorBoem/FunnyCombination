package com.example.funnycombination.data.model

enum class GamePhase {
    DEMONSTRATING,
    PLAYER_TURN,
    GAME_OVER
}

data class GameState(
    val level: Int = 1,
    val currentSequence: List<Emoji> = emptyList(),
    val playerInputs: List<Emoji> = emptyList(),
    val phase: GamePhase = GamePhase.DEMONSTRATING,
    val isNewRecord: Boolean = false
)