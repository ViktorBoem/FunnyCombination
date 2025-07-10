package com.example.funnycombination.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.funnycombination.data.model.HighScoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HighScoreDao {
    @Query("SELECT * FROM high_scores ORDER BY score DESC")
    fun getAllHighScores(): Flow<List<HighScoreEntity>>

    @Insert
    suspend fun insertHighScore(highScore: HighScoreEntity)

    @Query("SELECT MAX(score) FROM high_scores")
    suspend fun getBestScore(): Int?
}