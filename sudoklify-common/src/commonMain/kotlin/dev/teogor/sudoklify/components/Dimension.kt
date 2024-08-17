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

package dev.teogor.sudoklify.components

import dev.teogor.sudoklify.ExperimentalSudoklifyApi

/**
 * Sealed class representing different Sudoku dimensions with varying grid sizes.
 *
 * @property gridSize The size of the Sudoku grid (width and height).
 *
 * @property uniqueDigitsCount The total number of digits used in the Sudoku (width * height).
 * @property totalCells The total number of cells in the Sudoku (digits squared).
 * @property isSquare Whether the grid size is square (width == height).
 * @property width The width of the Sudoku grid.
 * @property height The height of the Sudoku grid.
 * @property boxWidth The width of a single box in the grid.
 * @property boxHeight The height of a single box in the grid.
 * @property name A human-readable name for the Sudoku dimension (e.g., "4x4").
 */
@ExperimentalSudoklifyApi
sealed class Dimension(
  val gridSize: GridSize,
) {
  /**
   * The number of distinct digits used in the Sudoku grid. This represents the total
   * number of unique symbols that can be placed within a single cell of the Sudoku.
   * It is calculated as the product of the grid width and height.
   */
  val uniqueDigitsCount: Int = gridSize.width * gridSize.height

  @Deprecated(
    message = """Consider using 'uniqueDigitsCount' for better clarity. This variable provides
      |the same information but emphasizes the distinct nature of the digits used in the Sudoku
      |grid.""",
    replaceWith = ReplaceWith("uniqueDigitsCount"),
  )
  val digits: Int = uniqueDigitsCount

  /**
   * The total number of cells in the Sudoku grid. This is calculated by squaring the
   * [uniqueDigitsCount]. Each cell within the grid can hold one of the [uniqueDigitsCount]
   * distinct digits (or symbols).
   */
  val totalCells: Int = uniqueDigitsCount * uniqueDigitsCount

  @Deprecated(
    message = """Consider using 'totalCells' for better clarity. This variable represents the
      |same information but emphasizes the total number of individual squares or cells within
      |the Sudoku grid.""",
    replaceWith = ReplaceWith("totalCells"),
  )
  val cells = totalCells

  /**
   * Whether the grid size is square (width == height).
   */
  val isSquare: Boolean = gridSize.isSquare

  /**
   * The width of the Sudoku grid.
   */
  val width: Int = uniqueDigitsCount

  /**
   * The height of the Sudoku grid.
   */
  val height: Int = uniqueDigitsCount

  /**
   * The width of a single box in the grid.
   */
  val boxWidth: Int = gridSize.width

  /**
   * The height of a single box in the grid.
   */
  val boxHeight: Int = gridSize.height

  /**
   * A human-readable name for the Sudoku dimension (e.g., "4x4").
   */
  val name: String = "${width}x$height"

  /**
   * Returns a string representation of the Sudoku dimension.
   */
  final override fun toString(): String = "$width x $height ($uniqueDigitsCount digits)"

  /**
   * Data class representing the size of a Sudoku grid.
   *
   * @property width The width of the grid.
   * @property height The height of the grid.
   *
   * @property isSquare Whether the grid size is square (width == height).
   */
  data class GridSize(
    val width: Int,
    val height: Int,
  ) {
    val isSquare: Boolean = width == height
  }

  /**
   * Companion object used to represent an unspecified Sudoku dimension.
   */
  data object Unspecified : Dimension(
    gridSize = GridSize(0, 0),
  )

  /**
   * Object representing a 4x4 Sudoku dimension.
   */
  data object FourByFour : Dimension(
    gridSize = GridSize(2, 2),
  )

  /**
   * Object representing a 6x6 Sudoku dimension.
   */
  data object SixBySix : Dimension(
    gridSize = GridSize(2, 3),
  )

  /**
   * Object representing a 8x8 Sudoku dimension.
   */
  data object EightByEight : Dimension(
    gridSize = GridSize(2, 4),
  )

  /**
   * Object representing a 9x9 Sudoku dimension.
   */
  data object NineByNine : Dimension(
    gridSize = GridSize(3, 3),
  )

  /**
   * Object representing a 10x10 Sudoku dimension.
   */
  data object TenByTen : Dimension(
    gridSize = GridSize(2, 5),
  )

  /**
   * Object representing a 12x12 Sudoku dimension.
   */
  data object TwelveByTwelve : Dimension(
    gridSize = GridSize(3, 4),
  )

  /**
   * Object representing a 15x15 Sudoku dimension.
   */
  data object FifteenByFifteen : Dimension(
    gridSize = GridSize(3, 5),
  )

  /**
   * Object representing a 16x16 Sudoku dimension.
   */
  data object SixteenBySixteen : Dimension(
    gridSize = GridSize(4, 4),
  )

  /**
   * Object representing a 25x25 Sudoku dimension.
   */
  data object TwentyFiveByTwentyFive : Dimension(
    gridSize = GridSize(5, 5),
  )

  /**
   * Object representing a 36x36 Sudoku dimension.
   */
  data object ThirtySixByThirtySix : Dimension(
    gridSize = GridSize(6, 6),
  )

  /**
   * Object representing a 49x49 Sudoku dimension.
   */
  data object FortyNineByFortyNine : Dimension(
    gridSize = GridSize(7, 7),
  )

  /**
   * Object representing a 64x64 Sudoku dimension.
   */
  data object SixtyFourBySixtyFour : Dimension(
    gridSize = GridSize(8, 8),
  )

  /**
   * Object representing a 81x81 Sudoku dimension.
   */
  data object EightyOneByEightyOne : Dimension(
    gridSize = GridSize(9, 9),
  )

  companion object {
    /**
     * Returns the corresponding [Dimension] instance based on the provided digit count.
     *
     * This function maps common Sudoku grid sizes, identified by their digit count, to their respective
     * [Dimension] instances. If the provided digit count does not match any of the predefined Sudoku sizes,
     * it throws an [InvalidDimensionException].
     * ### Example Usage:
     *
     * ```kotlin
     * // Example 1: Retrieving the Dimension for a 4x4 Sudoku grid
     * val dimension4x4 = Dimension.fromDigitCount(4)
     * println(dimension4x4) // Outputs: Dimension.FourByFour
     *
     * // Example 2: Retrieving the Dimension for a 9x9 Sudoku grid
     * val dimension9x9 = Dimension.fromDigitCount(9)
     * println(dimension9x9) // Outputs: Dimension.NineByNine
     *
     * // Example 3: Retrieving the Dimension for a 16x16 Sudoku grid
     * val dimension16x16 = Dimension.fromDigitCount(16)
     * println(dimension16x16) // Outputs: Dimension.SixteenBySixteen
     *
     * // Example 4: Handling invalid digit count
     * try {
     *     val invalidDimension = Dimension.fromDigitCount(7)
     * } catch (e: InvalidDimensionException) {
     *     println(e.message) // Outputs: No dimension exists for digit count: 7
     * }
     * ```
     *
     * ### Parameters:
     * - `digitCount: Int`: The total number of unique digits (or cells) in a Sudoku grid. This is usually
     *   the square of the grid size (e.g., 9 for a 3x3 grid, 16 for a 4x4 grid, etc.).
     *
     * ### Returns:
     * - [Dimension]: The corresponding [Dimension] instance if the `digitCount` matches a known Sudoku grid size.
     *
     * ### Throws:
     * - [InvalidDimensionException]: If the provided `digitCount` does not correspond to any known Sudoku dimension.
     */
    @Throws(InvalidDimensionException::class)
    fun fromDigitCount(digitCount: Int): Dimension {
      return when (digitCount) {
        4 -> FourByFour
        6 -> SixBySix
        8 -> EightByEight
        9 -> NineByNine
        10 -> TenByTen
        12 -> TwelveByTwelve
        15 -> FifteenByFifteen
        16 -> SixteenBySixteen
        25 -> TwentyFiveByTwentyFive
        36 -> ThirtySixByThirtySix
        49 -> FortyNineByFortyNine
        64 -> SixtyFourBySixtyFour
        81 -> EightyOneByEightyOne
        else -> throw InvalidDimensionException(digitCount)
      }
    }
  }
}

/**
 * Creates a [Dimension] instance based on the provided digit count.
 *
 * This function is a convenience wrapper around [Dimension.fromDigitCount]. It simplifies the creation
 * of a [Dimension] instance by directly using a digit count. If the digit count does not match any of
 * the known Sudoku grid sizes, it will throw an [InvalidDimensionException].
 *
 * ### Example Usage:
 *
 * ```kotlin
 * // Example 1: Creating a Dimension for a 4x4 Sudoku grid
 * val dimension4x4 = Dimension(4)
 * println(dimension4x4) // Outputs: Dimension.FourByFour
 *
 * // Example 2: Creating a Dimension for a 9x9 Sudoku grid
 * val dimension9x9 = Dimension(9)
 * println(dimension9x9) // Outputs: Dimension.NineByNine
 *
 * // Example 3: Creating a Dimension for a 16x16 Sudoku grid
 * val dimension16x16 = Dimension(16)
 * println(dimension16x16) // Outputs: Dimension.SixteenBySixteen
 *
 * // Example 4: Handling invalid digit count
 * try {
 *     val invalidDimension = Dimension(7)
 * } catch (e: InvalidDimensionException) {
 *     println(e.message) // Outputs: No dimension exists for digit count: 7
 * }
 * ```
 *
 * ### Parameters:
 * - `digitCount: Int`: The total number of unique digits (or cells) in a Sudoku grid. This number
 *   should be the square of the grid size (e.g., 9 for a 3x3 grid, 16 for a 4x4 grid, etc.).
 *
 * ### Returns:
 * - [Dimension]: The corresponding [Dimension] instance for the given `digitCount`.
 *
 * ### Throws:
 * - [InvalidDimensionException]: If the provided `digitCount` does not correspond to any known Sudoku dimension.
 */
@ExperimentalSudoklifyApi
fun Dimension(digitCount: Int): Dimension {
  return Dimension.fromDigitCount(digitCount)
}

/**
 * Returns a list containing all valid digits (1 to [Dimension.digits]) for the
 * Sudoku.
 */
@ExperimentalSudoklifyApi
fun Dimension.getAllDigits(): List<Int> = (1..uniqueDigitsCount).toList()

/**
 * Checks if a given digit is valid within the range of allowed digits for this Sudoku
 * (1 to [Dimension.digits]).
 */
@ExperimentalSudoklifyApi
fun Dimension.isDigitValid(digit: Int): Boolean = digit in 1..uniqueDigitsCount

/**
 * Returns the box index (within the range 0 to [Dimension.boxes - 1]) for a given cell
 * at the specified row and column.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided row or
 * column index is invalid.
 */
@ExperimentalSudoklifyApi
fun Dimension.getBoxIndex(
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

  return boxRowIndex + boxColumnIndex
}

/**
 * Returns the top-left and bottom-right coordinates of the box containing the cell at the
 * specified row and column.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided row or
 * column index is invalid.
 */
@ExperimentalSudoklifyApi
fun Dimension.getBoxCoordinates(
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
 * Returns the row index (within the range 0 to [Dimension.height - 1]) for a given cell
 * index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell index
 * is invalid.
 */
@ExperimentalSudoklifyApi
fun Dimension.getCellRowIndex(cellIndex: Int): Int {
  requireValidCellIndex(cellIndex = cellIndex, message = "Invalid cell index for row calculation")
  return cellIndex / width
}

/**
 * Returns the column index (within the range 0 to [Dimension.width - 1]) for a given cell
 * index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell index
 * is invalid.
 */
@ExperimentalSudoklifyApi
fun Dimension.getCellColumnIndex(cellIndex: Int): Int {
  requireValidCellIndex(
    cellIndex = cellIndex,
    message = "Invalid cell index for column calculation",
  )
  return cellIndex % width
}

/**
 * Returns the box row index (within the range 0 to ([Dimension.height] - 1 /
 * [Dimension.boxHeight] - 1)) for a given cell index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell index
 * is invalid.
 */
@ExperimentalSudoklifyApi
fun Dimension.getCellBoxRowIndex(cellIndex: Int): Int {
  requireValidCellIndex(
    cellIndex = cellIndex,
    message = "Invalid cell index for box row",
  )
  return cellIndex / width
}

/**
 * Returns the box column index (within the range 0 to ([Dimension.width - 1] /
 * [Dimension.boxWidth] - 1)) for a given cell index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell
 * index is invalid.
 */
@ExperimentalSudoklifyApi
fun Dimension.getCellBoxColumnIndex(cellIndex: Int): Int {
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
@ExperimentalSudoklifyApi
fun Dimension.areCellsInSameRow(
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
@ExperimentalSudoklifyApi
fun Dimension.areCellsInSameColumn(
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
@ExperimentalSudoklifyApi
fun Dimension.areCellsInSameBox(
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
@ExperimentalSudoklifyApi
fun Dimension.areCellsInSameBox(
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
@ExperimentalSudoklifyApi
fun Dimension.areCellsRelated(
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
internal fun Dimension.requireValidRowColIndices(
  row: Int,
  col: Int,
  message: String = "Invalid row/column indices",
) {
  require(row in 0..<height && col in 0..<width) {
    throw InvalidRowOrColumnIndexException("Invalid indices ($row, $col): $message")
  }
}

/**
 * Internal helper function to validate a cell index.
 *
 * Throws an [IllegalArgumentException] with a specific message if the provided cell
 * index is invalid.
 */
internal fun Dimension.requireValidCellIndex(
  cellIndex: Int,
  message: String = "Invalid cell index",
) {
  require(cellIndex in 0..<totalCells) {
    throw InvalidCellIndexException("Invalid index ($cellIndex): $message")
  }
}

class InvalidRowOrColumnIndexException(message: String) : IllegalArgumentException(message)

class InvalidCellIndexException(message: String) : IllegalArgumentException(message)

class InvalidDimensionException(digitCount: Int) : IllegalArgumentException(
  "No dimension exists for digit count: $digitCount",
)
