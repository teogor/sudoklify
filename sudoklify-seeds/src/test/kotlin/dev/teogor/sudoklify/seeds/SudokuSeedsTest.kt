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

import dev.teogor.sudoklify.common.model.SudokuBlueprint
import dev.teogor.sudoklify.common.types.Board
import dev.teogor.sudoklify.core.generation.difficulty
import dev.teogor.sudoklify.core.generation.generateSudoku
import dev.teogor.sudoklify.core.generation.seed
import dev.teogor.sudoklify.core.generation.seeds
import dev.teogor.sudoklify.core.generation.sudokuParamsBuilder
import dev.teogor.sudoklify.core.generation.sudokuType
import dev.teogor.sudoklify.core.util.toBoard
import dev.teogor.sudoklify.core.util.toSequenceString
import dev.teogor.sudoklify.ktx.createSeed
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource


class SudokuSeedsTest {
  @Test
  fun combinedSeeds_generatesSolutionMatchingPuzzle() {
    // Use a dedicated blueprint with known `combinedSeeds` and solutions
    combinedSeeds.forEach { sudoku ->
      val puzzle = sudoku.puzzle
      val solution = sudoku.solution

      val isValid = comparePuzzles(
        puzzle = puzzle.toBoard(sudoku.sudokuType),
        solution = solution.toBoard(sudoku.sudokuType),
      )

      assertEquals(true, isValid, "Invalid puzzle for solution ${sudoku.solution}")
    }
  }


  @ParameterizedTest
  @ArgumentsSource(SudokuBlueprintProvider::class)
  fun generateSudoku_createsExpectedSolutionWithDifferentSizes(
    blueprint: SudokuBlueprint,
  ) {
    val sudokuParams = sudokuParamsBuilder {
      seeds { combinedSeeds }
      seed { createSeed(0L) }
      sudokuType { blueprint.sudokuType }
      difficulty { blueprint.difficulty }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(
      blueprint.sudokuType,
      sudoku.sudokuType,
      "Sudoku type mismatch",
    )
    assertEquals(
      blueprint.difficulty,
      sudoku.difficulty,
      "Difficulty level mismatch",
    )
    assertEquals(
      blueprint.puzzle,
      sudoku.puzzle.toSequenceString(),
      "Puzzle generation error",
    )
    assertEquals(
      blueprint.solution,
      sudoku.solution.toSequenceString(),
      "Solution generation error",
    )
  }

  private fun comparePuzzles(puzzle: Board, solution: Board): Boolean {
    if (puzzle.size != solution.size || puzzle[0].size != solution[0].size) {
      return false
    }

    for (row in puzzle.indices) {
      for (col in 0..<puzzle[row].size) {
        val puzzleValue = puzzle[row][col]
        val solvedValue = solution[row][col]

        if (puzzleValue != "-" && puzzleValue != solvedValue) {
          return false
        }
      }
    }

    return true
  }
}
