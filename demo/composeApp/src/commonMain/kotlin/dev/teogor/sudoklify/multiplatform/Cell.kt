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

package dev.teogor.sudoklify.multiplatform

/**
 * Represents a cell in a Sudoku puzzle.
 *
 * @property value The current value of the cell. A value of 0 indicates an empty cell.
 * @property isGiven Indicates if the cell is part of the original puzzle (immutable).
 * @property isError Indicates if the cell contains an incorrect value.
 * @property solution The correct solution value for this cell.
 * @property row The row index of the cell in the Sudoku grid.
 * @property col The column index of the cell in the Sudoku grid.
 */
data class Cell(
  val value: Int,
  val isGiven: Boolean,
  val isError: Boolean,
  val solution: Int,
  val row: Int,
  val col: Int,
) {

  /**
   * Represents the position of a cell within the Sudoku grid.
   *
   * @property row The row index of the cell.
   * @property col The column index of the cell.
   */
  data class Position(val row: Int, val col: Int)
}
