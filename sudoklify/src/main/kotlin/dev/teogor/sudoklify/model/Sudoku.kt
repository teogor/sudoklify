package dev.teogor.sudoklify.model

import dev.teogor.sudoklify.types.PuzzleString
import dev.teogor.sudoklify.types.SolutionString


data class Sudoku(
  val puzzle: PuzzleString,
  val solution: SolutionString,
  val difficulty: Difficulty,
  val type: Type,
)
