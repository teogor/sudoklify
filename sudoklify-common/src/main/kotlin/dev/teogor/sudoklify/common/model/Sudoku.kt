/*
 * Copyright 2023 Teogor (Teodor Grigor)
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

package dev.teogor.sudoklify.common.model

import dev.teogor.sudoklify.common.types.Board
import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.SudokuType

@Deprecated(
  message =
    """
  The `Sudoku` data class is deprecated as of version 1.0.0-beta01.
  For enhanced functionality and efficient data storage, we highly recommend using the
  more versatile `SudokuPuzzle` class instead.
  """,
  replaceWith = ReplaceWith("SudokuPuzzle"),
)
data class Sudoku(
  val puzzle: Board,
  val solution: Board,
  val difficulty: Difficulty,
  val sudokuType: SudokuType,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Sudoku

    if (!puzzle.contentDeepEquals(other.puzzle)) return false
    if (!solution.contentDeepEquals(other.solution)) return false
    if (difficulty != other.difficulty) return false
    if (sudokuType != other.sudokuType) return false

    return true
  }

  override fun hashCode(): Int {
    var result = puzzle.contentDeepHashCode()
    result = 31 * result + solution.contentDeepHashCode()
    result = 31 * result + difficulty.hashCode()
    result = 31 * result + sudokuType.hashCode()
    return result
  }
}
