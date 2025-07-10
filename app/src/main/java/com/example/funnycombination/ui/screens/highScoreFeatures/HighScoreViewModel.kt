package com.example.funnycombination.ui.screens.highScoreFeatures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnycombination.data.repository.HighScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

data class HighScoreUiItem(
    val rank: Int,
    val score: Int,
    val date: String,
    val isBest: Boolean
)

@HiltViewModel
class HighScoreViewModel @Inject constructor(
    private val repository: HighScoreRepository
) : ViewModel() {

    private val _highScores = MutableStateFlow<List<HighScoreUiItem>>(emptyList())
    val highScores: StateFlow<List<HighScoreUiItem>> = _highScores.asStateFlow()

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    init {
        loadHighScores()
    }

    private fun loadHighScores() {
        viewModelScope.launch {
            repository.getHighScores().collectLatest { scores ->
                val bestScore = repository.getBestScore()

                _highScores.value = scores
                    .sortedByDescending { it.score }
                    .mapIndexed { index, highScore ->
                        HighScoreUiItem(
                            rank = index + 1,
                            score = highScore.score,
                            date = dateFormat.format(highScore.date),
                            isBest = highScore.score == bestScore
                        )
                    }
            }
        }
    }
}