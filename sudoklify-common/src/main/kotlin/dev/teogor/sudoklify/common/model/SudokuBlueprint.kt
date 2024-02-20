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

import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.GameType
import dev.teogor.sudoklify.common.types.PuzzleString
import dev.teogor.sudoklify.common.types.SolutionString

/**
 * Represents a blueprint for generating Sudoku puzzles.
 *
 * @property puzzle The puzzle layout as a [PuzzleString].
 * @property solution The solution layout as a [SolutionString].
 * @property difficulty The difficulty level of the Sudoku puzzle.
 * @property gameType The type of the Sudoku grid.
 */
data class SudokuBlueprint(
  val puzzle: PuzzleString,
  val solution: SolutionString,
  val difficulty: Difficulty,
  val gameType: GameType,
)
