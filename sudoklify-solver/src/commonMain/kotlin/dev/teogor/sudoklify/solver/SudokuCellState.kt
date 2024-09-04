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

package dev.teogor.sudoklify.solver

import dev.teogor.sudoklify.components.Dimension

/**
 * Represents the state of a single Sudoku cell.
 *
 * @property value The current value of the cell in the Sudoku grid.
 * @property solution The correct solution value for the cell, if known.
 * @property isLocked Indicates whether the cell is locked (i.e., cannot be changed by the user).
 * @property isError Indicates whether the cell contains an error based on the current Sudoku rules or solution.
 *
 * @see [toSudokuCellStates] for functions that process Sudoku grids.
 * @see [toUserGrid] for functions that process Sudoku grids.
 * @see [MistakeCheckingMode] for different methods of checking mistakes in Sudoku.
 * @see [Dimension] for dimensions and rules applied to Sudoku grids.
 * @see [SudokuSolver] for methods that solve Sudoku puzzles.
 */
public data class SudokuCellState(
  /**
   * The current value of the cell in the Sudoku grid.
   */
  val value: Int,
  /**
   * The correct solution value for the cell, if known. This is used for checking if the user's
   * input matches the expected solution.
   */
  val solution: Int,
  /**
   * Indicates whether the cell is locked. Locked cells are typically given by the puzzle setter
   * and cannot be modified during the solving process.
   */
  val isLocked: Boolean,
  /**
   * Indicates whether the cell contains an error. This property is set based on the mistake checking
   * mode and can be used to highlight cells that violate Sudoku rules or do not match the solution.
   */
  var isError: Boolean = false,
)

/**
 * Converts the grid to `SudokuCellState` using the provided lambdas.
 *
 * @param getValue Lambda function to get the value of a cell.
 * @param isLocked Lambda function to determine if a cell is locked.
 * @param getSolution Lambda function to get the solution value of a cell.
 * @param isError Lambda function to determine if a cell has an error state.
 *
 * @return A grid of `SudokuCellState`.
 */
public inline fun <T> List<List<T>>.toSudokuCellStates(
  crossinline getValue: (T) -> Int,
  crossinline isLocked: (T) -> Boolean,
  crossinline getSolution: (T) -> Int,
  crossinline isError: (T) -> Boolean,
): List<List<SudokuCellState>> {
  return this.map { row ->
    row.map { cell ->
      SudokuCellState(
        value = getValue(cell),
        solution = getSolution(cell),
        isLocked = isLocked(cell),
        isError = isError(cell),
      )
    }
  }
}

/**
 * Converts the grid of `SudokuCellState` back to the original grid type.
 *
 * @param updateCell Lambda function to update a cell in the original grid type.
 * @param originalGrid The original grid to map the updated cells back to.
 *
 * @return The updated grid of the original type.
 */
public inline fun <T> List<List<SudokuCellState>>.toUserGrid(
  crossinline updateCell: (row: Int, col: Int, SudokuCellState, T) -> T,
  originalGrid: List<List<T>>,
): List<List<T>> {
  return this.mapIndexed { rowIndex, row ->
    row.mapIndexed { colIndex, sudokuCellState ->
      updateCell(rowIndex, colIndex, sudokuCellState, originalGrid[rowIndex][colIndex])
    }
  }
}
