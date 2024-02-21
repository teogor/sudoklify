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

import dev.teogor.sudoklify.common.model.SudokuParams
import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.SudokuType
import dev.teogor.sudoklify.core.generation.createPuzzle
import dev.teogor.sudoklify.ktx.createSeed
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class SudokuParamsTest {
  @Test
  fun createPuzzle_returnsPuzzleWithCorrectParams() {
    val params = SudokuParams(
      difficulty = Difficulty.EASY,
      sudokuType = SudokuType.Sudoku9x9,
      seed = createSeed(1234L),
      seeds = combinedSeeds,
    )

    val puzzle = params.createPuzzle()

    assertEquals(Difficulty.EASY, puzzle.difficulty)
    assertEquals(SudokuType.Sudoku9x9, puzzle.sudokuType)
    assertEquals(1234L, puzzle.seed.value)
  }

  @Test
  fun createPuzzle_generatesDifferentPuzzlesWithDifferentSeeds() {
    val params1 = SudokuParams(
      difficulty = Difficulty.EASY,
      sudokuType = SudokuType.Sudoku9x9,
      seed = createSeed(1234L),
      seeds = combinedSeeds,
    )
    val params2 = SudokuParams(
      difficulty = Difficulty.EASY,
      sudokuType = SudokuType.Sudoku9x9,
      seed = createSeed(4321L),
      seeds = combinedSeeds,
    )

    val puzzle1 = params1.createPuzzle()
    val puzzle2 = params2.createPuzzle()

    assertNotEquals(puzzle1, puzzle2)
  }
}

