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

package dev.teogor.sudoklify.seeds

import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.SudokuType

val combinedSeeds = arrayOf(
  *fourDigitsSeeds,
  *nineDigitsSeeds,
  *sixteenDigitsSeeds,
  *twentyFiveDigitsSeeds,
)

/**
 * Checks if this game type supports the specified difficulty level.
 *
 * @param difficulty The difficulty level to check for.
 * @return True if the sudoku type supports the given difficulty,
 * false otherwise.
 */
fun SudokuType.supportsDifficulty(
  difficulty: Difficulty,
) = combinedSeeds.any {
  it.sudokuType == this && it.difficulty == difficulty
}
