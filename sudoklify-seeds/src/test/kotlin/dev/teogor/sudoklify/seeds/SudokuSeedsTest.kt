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
import dev.teogor.sudoklify.core.generation.createPuzzle
import dev.teogor.sudoklify.core.generation.difficulty
import dev.teogor.sudoklify.core.generation.seed
import dev.teogor.sudoklify.core.generation.seeds
import dev.teogor.sudoklify.core.generation.sudokuParamsBuilder
import dev.teogor.sudoklify.core.generation.sudokuType
import dev.teogor.sudoklify.core.util.toBoard
import dev.teogor.sudoklify.ktx.createSeed
import dev.teogor.sudoklify.seeds.utils.comparePuzzles
import dev.teogor.sudoklify.seeds.utils.toSequenceString
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
    val sudoku = sudokuParams.createPuzzle()

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
      toSequenceString(sudoku),
      "Puzzle generation error",
    )
    assertEquals(
      blueprint.solution,
      sudoku.solution.toSequenceString(),
      "Solution generation error",
    )
  }
}
