package com.example.funnycombination.domain.usecase

import com.example.funnycombination.data.model.Emoji
import javax.inject.Inject

class GenerateSequenceUseCase @Inject constructor() {
    fun execute(level: Int): List<Emoji> {
        return List(level) { Emoji.values().random() }
    }
}