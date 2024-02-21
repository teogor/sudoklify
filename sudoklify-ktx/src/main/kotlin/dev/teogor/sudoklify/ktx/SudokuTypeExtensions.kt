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

package dev.teogor.sudoklify.ktx

import dev.teogor.sudoklify.common.types.BoxCoordinates
import dev.teogor.sudoklify.common.types.SudokuType

/**
 * Returns a list containing all valid digits (1 to [SudokuType.digits]) for the
 * Sudoku.
 */
fun SudokuType.getAllDigits(): List<Int> = (1..digits).toList()

/**
 * Checks if a given digit is valid within the range of allowed digits for this Sudoku
 * (1 to [SudokuType.digits]).
 */
fun SudokuType.isDigitValid(digit: Int): Boolean = digit in 1..digits

/**
 * Returns the box index (within the range 0 to [SudokuType.boxes - 1]) for a given cell
 * at the specified row and column.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided row or
 * column index is invalid.
 */
fun SudokuType.getBoxIndex(
  row: Int,
  col: Int,
): Int {
  requireValidRowColIndices(
    row = row,
    col = col,
    message = "Invalid coordinates for box",
  )

  val boxWidth = gridSize.width
  val boxHeight = gridSize.height

  val boxRowIndex = row / boxHeight
  val boxColumnIndex = col / boxWidth

  return boxRowIndex * boxWidth + boxColumnIndex
}

/**
 * Returns the top-left and bottom-right coordinates of the box containing the cell at the
 * specified row and column.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided row or
 * column index is invalid.
 */
fun SudokuType.getBoxCoordinates(
  row: Int,
  col: Int,
): BoxCoordinates {
  requireValidRowColIndices(
    row = row,
    col = col,
    message = "Invalid coordinates for box",
  )

  val boxWidth = gridSize.width
  val boxHeight = gridSize.height

  val boxRowIndex = row / boxHeight
  val boxColumnIndex = col / boxWidth

  return BoxCoordinates(
    topLeftCol = boxColumnIndex * boxWidth,
    topLeftRow = boxRowIndex * boxHeight,
    bottomRightCol = (boxColumnIndex + 1) * boxWidth - 1,
    bottomRightRow = (boxRowIndex + 1) * boxHeight - 1,
  )
}

/**
 * Returns the row index (within the range 0 to [SudokuType.height - 1]) for a given cell
 * index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell index
 * is invalid.
 */
fun SudokuType.getCellRowIndex(cellIndex: Int): Int {
  requireValidCellIndex(cellIndex = cellIndex, message = "Invalid cell index for row calculation")
  return cellIndex / width
}

/**
 * Returns the column index (within the range 0 to [SudokuType.width - 1]) for a given cell
 * index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell index
 * is invalid.
 */
fun SudokuType.getCellColumnIndex(cellIndex: Int): Int {
  requireValidCellIndex(
    cellIndex = cellIndex,
    message = "Invalid cell index for column calculation",
  )
  return cellIndex % width
}

/**
 * Returns the box row index (within the range 0 to ([SudokuType.height] - 1 /
 * [SudokuType.boxHeight] - 1)) for a given cell index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell index
 * is invalid.
 */
fun SudokuType.getCellBoxRowIndex(cellIndex: Int): Int {
  requireValidCellIndex(
    cellIndex = cellIndex,
    message = "Invalid cell index for box row",
  )
  return cellIndex / width
}

/**
 * Returns the box column index (within the range 0 to ([SudokuType.width - 1] /
 * [SudokuType.boxWidth] - 1)) for a given cell index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell
 * index is invalid.
 */
fun SudokuType.getCellBoxColumnIndex(cellIndex: Int): Int {
  requireValidCellIndex(
    cellIndex = cellIndex,
    message = "Invalid cell index for box column calculation",
  )
  return (cellIndex % width) / boxWidth
}

/**
 * Checks if two cells are in the same row based on their indices.
 *
 * Throws an [IllegalArgumentException] with a specific message if either cell index
 * is invalid.
 */
fun SudokuType.areCellsInSameRow(
  cellIndex1: Int,
  cellIndex2: Int,
): Boolean {
  requireValidCellIndex(
    cellIndex = cellIndex1,
    message = "Invalid cell index for row comparison",
  )
  requireValidCellIndex(
    cellIndex = cellIndex2,
    message = "Invalid cell index for row comparison",
  )
  return getCellRowIndex(cellIndex1) == getCellRowIndex(cellIndex2)
}

/**
 * Checks if two cells are in the same column based on their indices.
 *
 * Throws an [IllegalArgumentException] with a specific message if either cell
 * index is invalid.
 */
fun SudokuType.areCellsInSameColumn(
  cellIndex1: Int,
  cellIndex2: Int,
): Boolean {
  requireValidCellIndex(
    cellIndex = cellIndex1,
    message = "Invalid cell index for column comparison",
  )
  requireValidCellIndex(
    cellIndex = cellIndex2,
    message = "Invalid cell index for column comparison",
  )
  return getCellColumnIndex(cellIndex1) == getCellColumnIndex(cellIndex2)
}

/**
 * Checks if two cells are in the same box based on their indices.
 *
 * Throws an [IllegalArgumentException] with a specific message if either cell index
 * is invalid.
 */
fun SudokuType.areCellsInSameBox(
  cellIndex1: Int,
  cellIndex2: Int,
): Boolean {
  requireValidCellIndex(
    cellIndex = cellIndex1,
    message = "Invalid cell index for box comparison",
  )
  requireValidCellIndex(
    cellIndex = cellIndex2,
    message = "Invalid cell index for box comparison",
  )
  return getCellBoxRowIndex(cellIndex1) == getCellBoxRowIndex(cellIndex2) &&
    getCellBoxColumnIndex(cellIndex1) == getCellBoxColumnIndex(cellIndex2)
}

/**
 * This alternative way to check if two cells are in the same box uses their row and
 * column indices directly.
 *
 * Throws an [IllegalArgumentException] with a specific message if either row or column
 * index is invalid.
 */
fun SudokuType.areCellsInSameBox(
  row1: Int,
  col1: Int,
  row2: Int,
  col2: Int,
): Boolean = getBoxIndex(row1, col1) == getBoxIndex(row2, col2)

/**
 * Checks if two cells are related (in the same row, column, or box) based on their row
 * and column indices.
 *
 * Throws an [IllegalArgumentException] with a specific message if any of the row or
 * column indices are invalid.
 */
fun SudokuType.areCellsRelated(
  row1: Int,
  col1: Int,
  row2: Int,
  col2: Int,
): Boolean {
  return row1 == row2 || col1 == col2 || areCellsInSameBox(row1, col1, row2, col2)
}

/**
 * Internal helper function to validate a cell index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell
 * index is invalid.
 */
private fun SudokuType.requireValidRowColIndices(
  row: Int,
  col: Int,
  message: String = "Invalid row/column indices",
) {
  require(row in 0..<height && col in 0..<width) {
    "Invalid indices ($row, $col): $message"
  }
}

/**
 * Internal helper function to validate a cell index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell
 * index is invalid.
 */
private fun SudokuType.requireValidCellIndex(
  cellIndex: Int,
  message: String = "Invalid cell index",
) {
  require(cellIndex in 0..<cells) {
    "Invalid index ($cellIndex): $message"
  }
}
