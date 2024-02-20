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

package dev.teogor.sudoklify.extensions

import dev.teogor.sudoklify.SudokuGenerator
import dev.teogor.sudoklify.SudokuPuzzle
import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.model.Sudoku
import dev.teogor.sudoklify.model.SudokuParams
import dev.teogor.sudoklify.types.Seed
import kotlin.random.Random

fun SudokuParams.generateSudoku(): Sudoku {
  return SudokuGenerator(
    random = Random(seed),
    gameType = gameType,
    difficulty = difficulty,
  ).composeSudokuPuzzle()
}

/**
 * Generates a Sudoku puzzle with the specified parameters.
 *
 * This function creates a [SudokuPuzzle] object using the provided [SudokuParams].
 * The created puzzle will have the specified difficulty level, seed, and game type.
 *
 * @receiver The `SudokuParams` object containing generation parameters.
 * @return A `SudokuPuzzle` object representing the generated puzzle.
 *
 * @throws [IllegalArgumentException] if any of the parameters are invalid.
 *
 * @see Difficulty
 * @see Seed
 * @see GameType
 * @see SudokuPuzzle
 */
fun SudokuParams.createPuzzle(): SudokuPuzzle {
  return SudokuGenerator(
    seed = seed,
    gameType = gameType,
    difficulty = difficulty,
  ).createPuzzle()
}
