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
import dev.teogor.sudoklify.components.Dimension

/**
 * Interface for processing Sudoku grids, converting between cell types, and checking for mistakes.
 *
 * @param T The type of the user grid cells.
 */
interface SudokuGridProcessor<T> {
  /**
   * Provides the lambda function to get the value of a cell.
   */
  val getValue: (T) -> Int

  /**
   * Provides the lambda function to determine if a cell is locked.
   */
  val isLocked: (T) -> Boolean

  /**
   * Provides the lambda function to get the solution value of a cell.
   */
  val getSolution: (T) -> Int

  /**
   * Provides the lambda function to determine if a cell has an error state.
   */
  val isError: (T) -> Boolean

  /**
   * Provides the lambda function to update a cell in the original grid type.
   */
  val updateCell: (row: Int, col: Int, SudokuCellState, cell: T) -> T

  /**
   * Processes the grid to check for mistakes and convert it back to the original grid type.
   *
   * @param grid The grid to process.
   * @param mistakesMethod The method used to check for mistakes.
   * @param dimension The dimension used for Sudoku rules.
   * @return The updated grid with potential mistake annotations.
   */
  fun processGridMistakes(
    grid: List<List<T>>,
    mistakesMethod: MistakeCheckingMode,
    dimension: Dimension,
  ): List<List<T>> {
    // Convert the original grid to SudokuCellState
    val sudokuCellStates =
      grid.toSudokuCellStates(
        getValue = getValue,
        isLocked = isLocked,
        getSolution = getSolution,
        isError = isError,
      )

    // Check for mistakes in the SudokuCellState grid
    val checkedBoard = sudokuCellStates.checkMistakesAll(mistakesMethod, dimension)

    // Convert the checked SudokuCellState grid back to the original grid type
    return checkedBoard.toUserGrid(updateCell, grid)
  }
}

/**
 * Creates a default implementation of [SudokuGridProcessor] for a specific grid cell type.
 *
 * @param T The type of the user grid cells.
 * @param getValue Lambda function to get the value of a cell.
 * @param isLocked Lambda function to determine if a cell is locked.
 * @param getSolution Lambda function to get the solution value of a cell.
 * @param isError Lambda function to determine if a cell has an error state.
 * @param updateCell Lambda function to update a cell in the original grid type.
 * @return A [SudokuGridProcessor] implementation with the provided functions.
 */
fun <T> createSudokuGridProcessor(
  getValue: (T) -> Int,
  isLocked: (T) -> Boolean,
  getSolution: (T) -> Int,
  isError: (T) -> Boolean,
  updateCell: (row: Int, col: Int, state: SudokuCellState, cell: T) -> T,
): SudokuGridProcessor<T> {
  return object : SudokuGridProcessor<T> {
    override val getValue = getValue
    override val isLocked = isLocked
    override val getSolution = getSolution
    override val isError = isError
    override val updateCell = updateCell
  }
}
