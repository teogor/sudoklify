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

package dev.teogor.sudoklify.ktx

import dev.teogor.sudoklify.common.types.PuzzleString

/**
 * Counts the number of remaining cells in the Sudoku puzzle.
 *
 * A remaining cell is a cell that is currently empty and needs to be filled in by
 * the user. The function traverses the provided PuzzleString and counts the number
 * of occurrences of the '-' character, which represents an empty cell.
 *
 * @return The number of remaining cells in the Sudoku puzzle.
 */
fun PuzzleString.countRemainingCells(): Int {
  return count { it == '-' }
}
