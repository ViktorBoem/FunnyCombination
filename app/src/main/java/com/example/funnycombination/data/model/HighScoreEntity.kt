package com.example.funnycombination.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "high_scores")
data class HighScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val score: Int,
    val date: Long
)

fun HighScoreEntity.toDomainModel(): HighScore {
    return HighScore(
        score = this.score,
        date = Date(this.date)
    )
}

fun HighScore.toEntity(): HighScoreEntity {
    return HighScoreEntity(
        score = this.score,
        date = this.date.time
    )
}