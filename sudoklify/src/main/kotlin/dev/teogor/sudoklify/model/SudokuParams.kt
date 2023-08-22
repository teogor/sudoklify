package dev.teogor.sudoklify.model

/**
 * Data class representing parameters for configuring a Sudoku puzzle generator.
 *
 * @property difficulty The difficulty level of the Sudoku puzzle.
 * @property seed The seed for generating random numbers.
 * @property type The type of the Sudoku grid.
 */
data class SudokuParams(
  val difficulty: Difficulty,
  val seed: Long,
  val type: Type,
)
