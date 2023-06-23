package dev.teogor.sudoku.gen

import dev.teogor.sudoku.gen.constants.BASE_LAYOUT
import dev.teogor.sudoku.gen.constants.DIFFICULTY_LEVELS
import dev.teogor.sudoku.gen.constants.SEEDS
import dev.teogor.sudoku.gen.types.Difficulty
import dev.teogor.sudoku.gen.types.Sudoku
import dev.teogor.sudoku.gen.utils.helper.getSequence
import dev.teogor.sudoku.gen.utils.layout.getLayout
import dev.teogor.sudoku.gen.utils.seed.getSeed
import dev.teogor.sudoku.gen.utils.token.getTokenMap
import dev.teogor.sudoku.gen.utils.validate.validateDifficulty

fun getSudoku2(difficulty: Difficulty?): Sudoku {
    if (difficulty != null && !validateDifficulty(difficulty)) {
        throw IllegalArgumentException("Invalid difficulty, expected one of: ${DIFFICULTY_LEVELS.joinToString(", ")}")
    }

    val seed = getSeed(SEEDS, difficulty)
    val layout = getLayout(BASE_LAYOUT)
    val tokenMap = getTokenMap()

    val puzzle = getSequence(layout, seed.puzzle, tokenMap)
    val solution = getSequence(layout, seed.solution, tokenMap)

    return Sudoku(puzzle, solution, seed.difficulty)
}
