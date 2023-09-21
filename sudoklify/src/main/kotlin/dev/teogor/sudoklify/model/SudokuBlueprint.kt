package dev.teogor.sudoklify.model

import dev.teogor.sudoklify.types.PuzzleString
import dev.teogor.sudoklify.types.SolutionString

/**
 * Represents a blueprint for generating Sudoku puzzles.
 *
 * @property puzzle The puzzle layout as a [PuzzleString].
 * @property solution The solution layout as a [SolutionString].
 * @property difficulty The difficulty level of the Sudoku puzzle.
 * @property gameType The type of the Sudoku grid.
 */
data class SudokuBlueprint(
  val puzzle: PuzzleString,
  val solution: SolutionString,
  val difficulty: Difficulty,
  val gameType: GameType,
)
