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

/**
 * Data class representing a suggested move in the Sudoku puzzle.
 *
 * @property row The row index where the move can be made.
 * @property col The column index where the move can be made.
 * @property value The single possible value that can be placed in the cell.
 */
data class SudokuMove(
  val row: Int,
  val col: Int,
  val value: Int,
)
