package dev.teogor.sudoklify.model

import dev.teogor.sudoklify.types.Seed

/**
 * Data class representing parameters for configuring a Sudoku puzzle generator.
 *
 * @property difficulty The difficulty level of the Sudoku puzzle.
 * @property seed The seed for generating random numbers.
 * @property gameType The type of the Sudoku grid.
 */
data class SudokuParams(
  val difficulty: Difficulty,
  val seed: Seed,
  val gameType: GameType,
)
