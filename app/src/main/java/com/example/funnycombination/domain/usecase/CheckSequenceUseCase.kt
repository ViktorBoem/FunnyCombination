package com.example.funnycombination.domain.usecase

import com.example.funnycombination.data.model.Emoji
import javax.inject.Inject

class CheckSequenceUseCase @Inject constructor() {
    fun execute(original: List<Emoji>, playerInput: List<Emoji>): Boolean {
        if (playerInput.size > original.size) return false

        for (i in playerInput.indices) {
            if (playerInput[i] != original[i]) return false
        }

        return playerInput.size == original.size
    }
}