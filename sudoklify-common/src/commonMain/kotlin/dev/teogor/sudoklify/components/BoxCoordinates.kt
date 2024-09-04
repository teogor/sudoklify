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
 * Data class representing the coordinates of a box in a Sudoku grid.
 *
 * This class encapsulates the details of a rectangular box within a Sudoku grid, defined by its top-left and bottom-right coordinates.
 * It provides additional properties to easily access the dimensions of the box and its corner coordinates.
 *
 * @property topLeftRow The row index of the top-left cell in the box.
 * @property topLeftCol The column index of the top-left cell in the box.
 * @property bottomRightRow The row index of the bottom-right cell in the box.
 * @property bottomRightCol The column index of the bottom-right cell in the box.
 *
 * @property width The width of the box (number of columns). Computed as the difference between the bottom-right and top-left column indices, plus one.
 * @property height The height of the box (number of rows). Computed as the difference between the bottom-right and top-left row indices, plus one.
 *
 * @property topLeft A tuple representing the top-left cell coordinates (row, column).
 * @property bottomRight A tuple representing the bottom-right cell coordinates (row, column). This takes into account the height and width of the box.
 */
public data class BoxCoordinates(
  val topLeftRow: Int,
  val topLeftCol: Int,
  val bottomRightRow: Int,
  val bottomRightCol: Int,
) {
  /**
   * Computes the width of the box, which is the number of columns spanned by the box.
   *
   * @return The width of the box.
   */
  val width: Int = bottomRightCol - topLeftCol + 1

  /**
   * Computes the height of the box, which is the number of rows spanned by the box.
   *
   * @return The height of the box.
   */
  val height: Int = bottomRightRow - topLeftRow + 1

  /**
   * Gets the coordinates of the top-left cell of the box as a tuple.
   *
   * @return A tuple representing the top-left cell coordinates (row, column).
   */
  val topLeft: Pair<Int, Int> = topLeftRow to topLeftCol

  /**
   * Gets the coordinates of the bottom-right cell of the box as a tuple.
   *
   * This method computes the bottom-right cell coordinates by adding the height and width to the top-left coordinates.
   *
   * @return A tuple representing the bottom-right cell coordinates (row, column).
   */
  val bottomRight: Pair<Int, Int> = (bottomRightRow + height - 1) to (bottomRightCol + width - 1)
}

/**
 * Checks if the given cell coordinates are within this box.
 *
 * @param row The row index of the cell to check.
 * @param col The column index of the cell to check.
 * @return `true` if the cell is within the box; otherwise, `false`.
 */
public fun BoxCoordinates.contains(
  row: Int,
  col: Int,
): Boolean {
  return row in topLeftRow..bottomRightRow && col in topLeftCol..bottomRightCol
}

/**
 * Expands the box by the specified number of rows and columns.
 *
 * @param rowExpansion The number of additional rows to add to the box.
 * @param colExpansion The number of additional columns to add to the box.
 * @return A new [BoxCoordinates] instance with the expanded dimensions.
 */
public fun BoxCoordinates.expand(
  rowExpansion: Int,
  colExpansion: Int,
): BoxCoordinates {
  return BoxCoordinates(
    topLeftRow = topLeftRow,
    topLeftCol = topLeftCol,
    bottomRightRow = bottomRightRow + rowExpansion,
    bottomRightCol = bottomRightCol + colExpansion,
  )
}

/**
 * Converts the box coordinates to a human-readable string representation.
 *
 * @return A string representing the box coordinates.
 */
public fun BoxCoordinates.toFormattedString(): String {
  return buildString {
    append("Box Coordinates:\n")
    append("  Top-Left: ($topLeftRow, $topLeftCol)\n")
    append("  Bottom-Right: ($bottomRightRow, $bottomRightCol)\n")
    append("  Width: $width\n")
    append("  Height: $height")
  }
}

/**
 * Computes the intersection of this box with another [BoxCoordinates] instance.
 *
 * @param other The other box to intersect with.
 * @return A new [BoxCoordinates] instance representing the intersection of the two boxes, or `null` if they do not intersect.
 */
public fun BoxCoordinates.intersect(other: BoxCoordinates): BoxCoordinates? {
  val intersectTopLeftRow = maxOf(this.topLeftRow, other.topLeftRow)
  val intersectTopLeftCol = maxOf(this.topLeftCol, other.topLeftCol)
  val intersectBottomRightRow = minOf(this.bottomRightRow, other.bottomRightRow)
  val intersectBottomRightCol = minOf(this.bottomRightCol, other.bottomRightCol)

  return if (intersectTopLeftRow <= intersectBottomRightRow &&
    intersectTopLeftCol <= intersectBottomRightCol
  ) {
    BoxCoordinates(
      topLeftRow = intersectTopLeftRow,
      topLeftCol = intersectTopLeftCol,
      bottomRightRow = intersectBottomRightRow,
      bottomRightCol = intersectBottomRightCol,
    )
  } else {
    null
  }
}

/**
 * Checks if the box is in the top-left corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the box is in the top-left corner; otherwise, `false`.
 */
public fun BoxCoordinates.isTopStart(dimension: Dimension): Boolean {
  return topLeftRow == 0 && topLeftCol == 0
}

/**
 * Checks if the box is in the top-right corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the box is in the top-right corner; otherwise, `false`.
 */
public fun BoxCoordinates.isTopEnd(dimension: Dimension): Boolean {
  return topLeftRow == 0 && bottomRightCol == dimension.width - 1
}

/**
 * Checks if the box is in the bottom-left corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the box is in the bottom-left corner; otherwise, `false`.
 */
public fun BoxCoordinates.isBottomStart(dimension: Dimension): Boolean {
  return bottomRightRow == dimension.height - 1 && topLeftCol == 0
}

/**
 * Checks if the box is in the bottom-right corner of the Sudoku grid.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the box is in the bottom-right corner; otherwise, `false`.
 */
public fun BoxCoordinates.isBottomEnd(dimension: Dimension): Boolean {
  return bottomRightRow == dimension.height - 1 && bottomRightCol == dimension.width - 1
}

/**
 * Determines if the box should have a darker background color.
 *
 * @param dimension The dimension of the Sudoku grid.
 * @return `true` if the box should have a darker background; otherwise, `false`.
 */
public fun BoxCoordinates.isAlternateBox(dimension: Dimension): Boolean {
  // Compute the box index based on its position
  val boxIndex =
    (topLeftRow / dimension.boxHeight) * (dimension.width / dimension.boxWidth) + (topLeftCol / dimension.boxWidth)

  // Use modulo operation to alternate the style
  return boxIndex % 2 == 1
}
