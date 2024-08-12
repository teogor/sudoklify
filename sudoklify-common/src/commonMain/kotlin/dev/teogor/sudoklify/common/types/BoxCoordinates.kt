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
 * Data class representing the coordinates of a box in a Sudoku grid.
 *
 * @property topLeftRow The row index of the top-left cell in the box.
 * @property topLeftCol The column index of the top-left cell in the box.
 * @property bottomRightRow The row index of the bottom-right cell in the box.
 * @property bottomRightCol The column index of the bottom-right cell in the box.
 *
 * @property width The width of the box (number of columns).
 * @property height The height of the box (number of rows).
 *
 * @property topLeft A tuple representing the top-left cell coordinates (row, col).
 * @property bottomRight A tuple representing the bottom-right cell coordinates (row, col).
 */
data class BoxCoordinates(
  val topLeftRow: Int,
  val topLeftCol: Int,
  val bottomRightRow: Int,
  val bottomRightCol: Int,
) {
  val width: Int get() = bottomRightCol - topLeftCol + 1
  val height: Int get() = bottomRightRow - topLeftRow + 1

  val topLeft: Pair<Int, Int>
    get() = topLeftRow to topLeftCol
  val bottomRight: Pair<Int, Int>
    get() = (bottomRightRow + height - 1) to (bottomRightCol + width - 1)
}
