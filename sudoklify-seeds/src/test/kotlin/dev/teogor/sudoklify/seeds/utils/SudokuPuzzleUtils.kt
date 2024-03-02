package dev.teogor.sudoklify.seeds.utils

import dev.teogor.sudoklify.common.model.SudokuPuzzle
import dev.teogor.sudoklify.ktx.generateGridWithGivens

fun List<List<Int>>.toSequenceString(): String {
  return flatten().joinToString("")
}

fun toSequenceString(sudokuPuzzle: SudokuPuzzle): String {
  return sudokuPuzzle.generateGridWithGivens().map {
    it.map {
      if (it == 0) {
        "-"
      } else {
        it.toString()
      }
    }
  }.flatten().joinToString(separator = "")
}

fun comparePuzzles(
  puzzle: Array<Array<String>>,
  solution: Array<Array<String>>,
): Boolean {
  if (puzzle.size != solution.size || puzzle[0].size != solution[0].size) {
    return false
  }

  for (row in puzzle.indices) {
    for (col in 0..<puzzle[row].size) {
      val puzzleValue = puzzle[row][col]
      val solvedValue = solution[row][col]

      if (puzzleValue != "-" && puzzleValue != solvedValue) {
        return false
      }
    }
  }

  return true
}
