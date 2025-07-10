package com.example.funnycombination.data.repository

import com.example.funnycombination.data.dao.HighScoreDao
import com.example.funnycombination.data.model.HighScore
import com.example.funnycombination.data.model.toDomainModel
import com.example.funnycombination.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class HighScoreRepository @Inject constructor(private val highScoreDao: HighScoreDao) {

    fun getHighScores(): Flow<List<HighScore>> {
        return highScoreDao.getAllHighScores().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    suspend fun saveScore(score: Int): Boolean {
        if (score <= 0) return false
        val currentHighScores = highScoreDao.getBestScore() ?: 0
        val isHighScore = score > currentHighScores

        val newHighScore = HighScore(score = score, date = Date())
        highScoreDao.insertHighScore(newHighScore.toEntity())

        return isHighScore
    }

    private suspend fun isNewHighScore(score: Int): Boolean {
        val bestScore = highScoreDao.getBestScore() ?: 0
        return score >= bestScore
    }

    suspend fun getBestScore(): Int {
        return highScoreDao.getBestScore() ?: 0
    }
}