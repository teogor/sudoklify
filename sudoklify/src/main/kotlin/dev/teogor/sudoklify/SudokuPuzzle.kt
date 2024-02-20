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

package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType

data class SudokuPuzzle(
  val difficulty: Difficulty,
  val gameType: GameType,
  val seed: Long,
  val givens: List<Givens>,
  val solution: List<List<Int>>,
  val hints: List<Hint> = emptyList(),
) {
  /**
   * Data class for representing givens (fixed cells) in the puzzle.
   */
  data class Givens(
    val row: Int,
    val col: Int,
    val value: Int,
  )

  /**
   * Data class for representing hints (optional suggestions for solving).
   */
  data class Hint(
    val message: String,
    val row: Int,
    val col: Int = -1,
    val type: HintType,
  )

  enum class HintType {
    ROW_ELIMINATION,
    COL_ELIMINATION,
    BLOCK_ELIMINATION,
    GUESS,
  }
}
