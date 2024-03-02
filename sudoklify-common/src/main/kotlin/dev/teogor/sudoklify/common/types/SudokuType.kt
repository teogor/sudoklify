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

package dev.teogor.sudoklify.common.types

/**
 * Sealed class representing different Sudoku types with varying grid sizes.
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
 * @property name A human-readable name for the Sudoku type (e.g., "4x4").
 */
sealed class SudokuType(
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
   * A human-readable name for the Sudoku type (e.g., "4x4").
   */
  val name: String = "${width}x$height"

  /**
   * Returns a string representation of the Sudoku type.
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
   * Companion object used to represent an unspecified Sudoku type.
   */
  data object Unspecified : SudokuType(
    gridSize = GridSize(0, 0),
  )

  /**
   * Object representing a 4x4 Sudoku type.
   */
  data object Sudoku4x4 : SudokuType(
    gridSize = GridSize(2, 2),
  )

  /**
   * Object representing a 6x6 Sudoku type.
   */
  data object Sudoku6x6 : SudokuType(
    gridSize = GridSize(2, 3),
  )

  /**
   * Object representing a 8x8 Sudoku type.
   */
  data object Sudoku8x8 : SudokuType(
    gridSize = GridSize(2, 4),
  )

  /**
   * Object representing a 9x9 Sudoku type.
   */
  data object Sudoku9x9 : SudokuType(
    gridSize = GridSize(3, 3),
  )

  /**
   * Object representing a 10x10 Sudoku type.
   */
  data object Sudoku10x10 : SudokuType(
    gridSize = GridSize(2, 5),
  )

  /**
   * Object representing a 12x12 Sudoku type.
   */
  data object Sudoku12x12 : SudokuType(
    gridSize = GridSize(3, 4),
  )

  /**
   * Object representing a 15x15 Sudoku type.
   */
  data object Sudoku15x15 : SudokuType(
    gridSize = GridSize(3, 5),
  )

  /**
   * Object representing a 16x16 Sudoku type.
   */
  data object Sudoku16x16 : SudokuType(
    gridSize = GridSize(4, 4),
  )

  /**
   * Object representing a 25x25 Sudoku type.
   */
  data object Sudoku25x25 : SudokuType(
    gridSize = GridSize(5, 5),
  )

  /**
   * Object representing a 36x36 Sudoku type.
   */
  data object Sudoku36x36 : SudokuType(
    gridSize = GridSize(6, 6),
  )

  /**
   * Object representing a 49x49 Sudoku type.
   */
  data object Sudoku49x49 : SudokuType(
    gridSize = GridSize(7, 7),
  )

  /**
   * Object representing a 64x64 Sudoku type.
   */
  data object Sudoku64x64 : SudokuType(
    gridSize = GridSize(8, 8),
  )

  /**
   * Object representing a 81x81 Sudoku type.
   */
  data object Sudoku81x81 : SudokuType(
    gridSize = GridSize(9, 9),
  )
}
