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

@file:OptIn(ExperimentalSudoklifyApi::class)

package dev.teogor.sudoklify.solver

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.SudoklifyDsl
import dev.teogor.sudoklify.puzzle.SudokuPuzzle

/**
 * Engine for solving Sudoku puzzles using the provided puzzle configuration and grid processor.
 *
 * @param T The type of the user grid cells.
 * @property gridProcessor A [SudokuGridProcessor] instance responsible for grid processing and mistake checking.
 * @property puzzle The [SudokuPuzzle] configuration, including difficulty, type, seed, givens, solution, and hints.
 */
@OptIn(ExperimentalSudoklifyApi::class)
class SudoklifySolverEngine<T>(
  private val gridProcessor: SudokuGridProcessor<T>,
  private val puzzle: SudokuPuzzle,
) {
  /**
   * The mode used for checking mistakes during puzzle solving.
   * Default is [MistakeCheckingMode.CheckViolations].
   */
  var mistakeCheckingMode: MistakeCheckingMode = MistakeCheckingMode.CheckViolations

  // Extract dimension and grid from the SudokuPuzzle
  private val dimension = puzzle.type
  private val moveAdvisor = SudoklifyMoveAdvisor(dimension)

  /**
   * Suggests the next possible move based on the current Sudoku puzzle configuration.
   *
   * @param grid The current grid to analyze for suggesting the next move.
   * @return A [SudokuMove] representing the row, column, and the single possible value for the next move, or `null` if no move is suggested.
   */
  fun suggestNextMove(grid: List<List<T>>): SudokuMove? {
    val intGrid =
      grid.map { row ->
        row.map { cell -> gridProcessor.getValue(cell) }.toIntArray()
      }.toTypedArray()

    return moveAdvisor.suggestNextMove(intGrid)
  }

  /**
   * Processes the grid to check for mistakes and updates it with potential mistake annotations.
   *
   * @param grid The grid to process.
   * @return The updated grid with potential mistake annotations.
   */
  fun processGridMistakes(grid: List<List<T>>): List<List<T>> {
    return gridProcessor.processGridMistakes(
      grid,
      mistakeCheckingMode,
      dimension,
    )
  }
}

/**
 * DSL function for creating a [SudoklifySolverEngine] instance with the specified parameters.
 *
 * @param T The type of the user grid cells.
 * @param gridProcessor A [SudokuGridProcessor] instance to handle grid processing and mistake checking.
 * @param puzzle The [SudokuPuzzle] configuration to be used.
 * @param mistakeCheckingMode The mode used for mistake checking (optional, default is [MistakeCheckingMode.CheckViolations]).
 * @return A new [SudoklifySolverEngine] instance.
 */
@SudoklifyDsl
inline fun <reified T> SudoklifySolverEngine(
  gridProcessor: SudokuGridProcessor<T>,
  puzzle: SudokuPuzzle,
  mistakeCheckingMode: MistakeCheckingMode = MistakeCheckingMode.CheckViolations,
): SudoklifySolverEngine<T> {
  return SudoklifySolverEngine(gridProcessor, puzzle).apply {
    this.mistakeCheckingMode = mistakeCheckingMode
  }
}
