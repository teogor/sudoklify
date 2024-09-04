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

package dev.teogor.sudoklify.components

/**
 * Data class representing the coordinates of a cell in a Sudoku grid.
 *
 * This class encapsulates the details of a cell's position within the grid, defined by its row and column indices.
 *
 * @property row The row index of the cell.
 * @property col The column index of the cell.
 *
 * @property position A tuple representing the cell coordinates (row, column).
 */
public data class CellCoordinates(
  val row: Int,
  val col: Int,
) {
  /**
   * Gets the coordinates of the cell as a tuple.
   *
   * @return A tuple representing the cell coordinates (row, column).
   */
  val position: Pair<Int, Int> = row to col
}

/**
 * Checks if the cell is in the top-start corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the cell is in the top-start corner; otherwise, `false`.
 */
public fun CellCoordinates.isTopStart(dimension: Dimension): Boolean {
  return row == 0 && col == 0
}

/**
 * Checks if the cell is in the top-end corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the cell is in the top-end corner; otherwise, `false`.
 */
public fun CellCoordinates.isTopEnd(dimension: Dimension): Boolean {
  return row == 0 && col == dimension.width - 1
}

/**
 * Checks if the cell is in the bottom-start corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the cell is in the bottom-start corner; otherwise, `false`.
 */
public fun CellCoordinates.isBottomStart(dimension: Dimension): Boolean {
  return row == dimension.height - 1 && col == 0
}

/**
 * Checks if the cell is in the bottom-end corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the cell is in the bottom-end corner; otherwise, `false`.
 */
public fun CellCoordinates.isBottomEnd(dimension: Dimension): Boolean {
  return row == dimension.height - 1 && col == dimension.width - 1
}

/**
 * Determines if the cell should have an alternate style, such as a different color.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the cell should have an alternate style; otherwise, `false`.
 */
public fun CellCoordinates.isAlternateCell(dimension: Dimension): Boolean {
  // Compute the cell index based on its position
  val cellIndex = row * dimension.width + col

  // Use modulo operation to alternate the style
  return cellIndex % 2 == 1
}
