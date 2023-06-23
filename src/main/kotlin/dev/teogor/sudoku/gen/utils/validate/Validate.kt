package dev.teogor.sudoku.gen.utils.validate

import dev.teogor.sudoku.gen.constants.DIFFICULTY_LEVELS

fun validateDifficulty(difficulty: Any): Boolean {
    return DIFFICULTY_LEVELS.contains(difficulty)
}