/*
 * Copyright 2024 Teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.teogor.sudoklify.puzzle

import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.Seed
import dev.teogor.sudoklify.puzzle.SudokuPuzzle.Hint

/**
 * Represents a Sudoku puzzle, including its difficulty, type, seed,
 * given cells (givens), solution, and optional hints.
 *
 * @property difficulty The difficulty level of the Sudoku puzzle.
 * @property type The type of the Sudoku puzzle, defining its grid size.
 * @property seed The seed value used to generate the puzzle.
 * @property givens A list of given cells (fixed values) in the puzzle.
 * @property solution The solution grid of the puzzle as a list of lists of integers.
 * @property hints Optional hints for solving the puzzle, represented as a list of [Hint].
 */
public data class SudokuPuzzle(
  val difficulty: Difficulty,
  val type: Dimension,
  val seed: Seed,
  val givens: List<Givens>,
  val solution: List<List<Int>>,
  val hints: List<Hint> = emptyList(),
) {
  /**
   * Represents a fixed cell in the Sudoku puzzle.
   *
   * @property row The row index of the given cell (0-based).
   * @property col The column index of the given cell (0-based).
   * @property value The fixed value assigned to the cell.
   */
  public data class Givens(
    val row: Int,
    val col: Int,
    val value: Int,
  )

  /**
   * Represents a hint for solving the Sudoku puzzle.
   *
   * @property message The message or description of the hint.
   * @property row The row index to which the hint applies (0-based).
   * @property col The column index to which the hint applies (0-based).
   *                Defaults to -1 if the hint is not specific to a column.
   * @property type The type of hint, which determines the solving strategy to be used.
   */
  public data class Hint(
    val message: String,
    val row: Int,
    val col: Int = -1,
    val type: HintType,
  )

  /**
   * Enumerates the types of hints that can be provided to help solve the Sudoku puzzle.
   */
  public enum class HintType {
    /**
     * Indicates that the hint involves eliminating possibilities in a row.
     */
    ROW_ELIMINATION,

    /**
     * Indicates that the hint involves eliminating possibilities in a column.
     */
    COL_ELIMINATION,

    /**
     * Indicates that the hint involves eliminating possibilities in a block (sub-grid).
     */
    BLOCK_ELIMINATION,

    /**
     * Indicates that the hint involves making an educated guess.
     */
    GUESS,
  }
}

/**
 * Generates a Sudoku grid with the given cells filled in based on the puzzle configuration.
 *
 * **Example:**
 *
 * ```kotlin
 * val puzzle = SudokuPuzzle(
 *   difficulty = Difficulty.MEDIUM,
 *   type = SudokuType.Sudoku9x9,
 *   seed = createSeed(123L),
 *   givens = listOf(Givens(0, 0, 5), Givens(1, 1, 3)),
 *   solution = ... // provide the solution grid here
 * )
 *
 * val grid = puzzle.generateGridWithGivens()
 * // grid[0][0] will be 5, grid[1][1] will be 3, and the rest will be 0
 * ```
 *
 * @return A list of lists representing the grid, where each inner list corresponds to a row,
 * and each element in the inner list corresponds to a cell value.
 */
public fun SudokuPuzzle.generateGridWithGivens(): List<List<Int>> {
  val gridSize = type.uniqueDigitsCount
  val grid = MutableList(gridSize) { MutableList(gridSize) { 0 } }

  // Map givens to the grid
  for (given in givens) {
    grid[given.row][given.col] = given.value
  }

  return grid.toList()
}
