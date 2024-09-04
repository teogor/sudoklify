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

package dev.teogor.sudoklify.puzzle

import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.createSeed
import dev.teogor.sudoklify.puzzle.SudokuPuzzle.Givens
import dev.teogor.sudoklify.puzzle.SudokuPuzzle.Hint
import dev.teogor.sudoklify.puzzle.SudokuPuzzle.HintType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SudokuPuzzleTest {
  @Test
  fun constructor_shouldSetPropertiesCorrectly() {
    val difficulty = Difficulty.EASY
    val type = Dimension.NineByNine
    val seed = createSeed(123L)
    val givens = listOf(Givens(0, 0, 1))
    val solution = listOf(listOf(1, 2, 3))
    val hints = listOf(Hint("Consider row 1", 0, 0, HintType.ROW_ELIMINATION))
    val puzzle = SudokuPuzzle(difficulty, type, seed, givens, solution, hints)

    assertEquals(difficulty, puzzle.difficulty, "Difficulty should be correctly set.")
    assertEquals(type, puzzle.type, "Sudoku type should be correctly set.")
    assertEquals(seed, puzzle.seed, "Seed should be correctly set.")
    assertEquals(givens, puzzle.givens, "Givens should be correctly set.")
    assertEquals(solution, puzzle.solution, "Solution should be correctly set.")
    assertEquals(hints, puzzle.hints, "Hints should be correctly set.")
  }

  @Test
  fun generateGridWithGivens_shouldFillGivensInGrid() {
    val puzzle =
      SudokuPuzzle(
        Difficulty.EASY,
        Dimension.FourByFour,
        createSeed(456L),
        listOf(Givens(1, 2, 3)),
        emptyList(),
        emptyList(),
      )
    val grid = puzzle.generateGridWithGivens()

    assertEquals(4, grid.size, "Grid size should match the expected size for Sudoku4x4.")
    assertEquals(
      4,
      grid[0].size,
      "Each row should have the correct number of columns for Sudoku4x4.",
    )
    assertTrue(grid[1][2] == 3, "Given value should be correctly filled in the grid.")
  }

  @Test
  fun generateGridWithGivens_shouldReturnEmptyGridForNoGivens() {
    val puzzle =
      SudokuPuzzle(
        Difficulty.EASY,
        Dimension.NineByNine,
        createSeed(789L),
        emptyList(),
        emptyList(),
        emptyList(),
      )
    val grid = puzzle.generateGridWithGivens()

    assertEquals(9, grid.size, "Grid size should match the expected size for Sudoku9x9.")
    assertEquals(
      9,
      grid[0].size,
      "Each row should have the correct number of columns for Sudoku9x9.",
    )

    for (row in grid) {
      assertTrue(
        row.all { it == 0 },
        "Grid should be completely empty when no givens are provided.",
      )
    }
  }
}
