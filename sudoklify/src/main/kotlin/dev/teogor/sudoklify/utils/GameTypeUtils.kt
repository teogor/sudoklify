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

package dev.teogor.sudoklify.utils

import dev.teogor.sudoklify.model.GameType

/**
 * Finds a GameType based on the given number of rows and columns.
 *
 * @param rows The number of rows in the Sudoku grid.
 * @param cols The number of columns in the Sudoku grid.
 * @return The matching GameType, or GameType.Unspecified if no match is found.
 */
fun findBySize(
  rows: Int,
  cols: Int,
): GameType {
  return GameType.values()
    .find {
      it.gridHeight == rows && it.gridWidth == cols
    } ?: GameType.Unspecified
}
