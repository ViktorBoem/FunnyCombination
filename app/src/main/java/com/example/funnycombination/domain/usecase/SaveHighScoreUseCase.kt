package com.example.funnycombination.domain.usecase

import com.example.funnycombination.data.repository.HighScoreRepository
import javax.inject.Inject

class SaveHighScoreUseCase @Inject constructor(
    private val repository: HighScoreRepository
) {
    suspend fun execute(score: Int): Boolean {
        return repository.saveScore(score)
    }
}