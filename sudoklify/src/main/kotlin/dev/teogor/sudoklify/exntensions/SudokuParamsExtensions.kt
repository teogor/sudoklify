package dev.teogor.sudoklify.exntensions

import dev.teogor.sudoklify.SudokuGenerator
import dev.teogor.sudoklify.model.Sudoku
import dev.teogor.sudoklify.model.SudokuParams
import kotlin.random.Random

fun SudokuParams.generateSudoku(): Sudoku {
  return SudokuGenerator(
    random = Random(seed),
    type = type,
    difficulty = difficulty,
  ).composeSudokuPuzzle()
}
