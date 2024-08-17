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

package dev.teogor.sudoklify.schema

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.tokenizer.SudokuString
import dev.teogor.sudoklify.tokenizer.toSudokuString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalSudoklifyApi::class)
class SudokuSchemaTest {
  @Test
  fun constructor_shouldSetPropertiesCorrectly() {
    val puzzle = SudokuString("-BCDCDA-BAD--CBA")
    val solution = SudokuString("ABCDCDABBADCDCBA")
    val difficulty = Difficulty.EASY
    val dimension = Dimension.FourByFour
    val schema = SudokuSchema(puzzle, solution, difficulty, dimension)

    assertEquals(puzzle, schema.puzzle, "Puzzle layout should be correctly set.")
    assertEquals(solution, schema.solution, "Solution layout should be correctly set.")
    assertEquals(difficulty, schema.difficulty, "Difficulty should be correctly set.")
    assertEquals(dimension, schema.dimension, "Sudoku type should be correctly set.")
  }

  @Test
  fun sudokuSchema_constructor_shouldConvertStringsToSudokuString() {
    val puzzleString = "-BCDCDA-BAD--CBA"
    val solutionString = "ABCDCDABBADCDCBA"
    val difficulty = Difficulty.MEDIUM
    val dimension = Dimension.FourByFour

    val schema =
      SudokuSchema(
        puzzle = puzzleString,
        solution = solutionString,
        difficulty = difficulty,
        dimension = dimension,
      )

    assertEquals(
      puzzleString.toSudokuString(),
      schema.puzzle,
      "Puzzle string should be converted to SudokuString.",
    )
    assertEquals(
      solutionString.toSudokuString(),
      schema.solution,
      "Solution string should be converted to SudokuString.",
    )
    assertEquals(difficulty, schema.difficulty, "Difficulty should be correctly set.")
    assertEquals(dimension, schema.dimension, "Sudoku type should be correctly set.")
  }

  @Test
  fun copy_shouldReturnModifiedCopy() {
    val originalPuzzle = SudokuString("-BCDCDA-BAD--CBA")
    val solution = SudokuString("ABCDCDABBADCDCBA")
    val newPuzzle = SudokuString("BADCD--AA--DCDAB")
    val newSolution = SudokuString("BADCDCBAABCDCDAB")
    val originalSchema =
      SudokuSchema(
        puzzle = originalPuzzle,
        solution = solution,
        difficulty = Difficulty.EASY,
        dimension = Dimension.FourByFour,
      )

    val copiedSchema =
      originalSchema.copy(
        puzzle = newPuzzle,
        solution = newSolution,
        difficulty = Difficulty.HARD,
      )

    assertEquals(
      newPuzzle,
      copiedSchema.puzzle,
      "Puzzle layout should be updated in the copied schema.",
    )
    assertNotEquals(
      originalPuzzle,
      copiedSchema.puzzle,
      "Puzzle layout should be updated in the copied schema.",
    )
    assertEquals(
      Difficulty.HARD,
      copiedSchema.difficulty,
      "Difficulty should be updated in the copied schema.",
    )
    assertEquals(
      Dimension.FourByFour,
      copiedSchema.dimension,
      "Sudoku type should remain unchanged in the copied schema.",
    )
  }

  @Test
  fun toString_shouldReturnFormattedString() {
    val puzzle = SudokuString("-BCDCDA-BAD--CBA")
    val solution = SudokuString("ABCDCDABBADCDCBA")
    val schema = SudokuSchema(puzzle, solution, Difficulty.MEDIUM, Dimension.FourByFour)

    val expectedString =
      buildString {
        append("SudokuSchema(puzzle=")
        append("-BCDCDA-BAD--CBA")
        append(", solution=")
        append("ABCDCDABBADCDCBA")
        append(", difficulty=")
        append("MEDIUM")
        append(", sudokuType=")
        append("4 x 4 (4 digits)")
        append(")")
      }
    assertEquals(
      expectedString,
      schema.toString(),
      "String representation should be formatted correctly.",
    )
  }

  @Test
  fun hashCode_shouldReturnConsistentValue() {
    val puzzle = SudokuString("-BCDCDA-BAD--CBA")
    val solution = SudokuString("ABCDCDABBADCDCBA")
    val schema1 = SudokuSchema(puzzle, solution, Difficulty.MEDIUM, Dimension.FourByFour)
    val schema2 = SudokuSchema(puzzle, solution, Difficulty.MEDIUM, Dimension.FourByFour)

    assertEquals(
      schema1.hashCode(),
      schema2.hashCode(),
      "Hash codes should be consistent for identical schemas.",
    )
  }

  @Test
  fun equals_shouldReturnTrueForIdenticalSchemas() {
    val puzzle = SudokuString("-BCDCDA-BAD--CBA")
    val solution = SudokuString("ABCDCDABBADCDCBA")
    val schema1 = SudokuSchema(puzzle, solution, Difficulty.MEDIUM, Dimension.FourByFour)
    val schema2 = SudokuSchema(puzzle, solution, Difficulty.MEDIUM, Dimension.FourByFour)

    assertEquals(schema1, schema2, "Schemas with identical properties should be equal.")
  }

  @Test
  fun equals_shouldReturnFalseForDifferentSchemas() {
    val puzzle1 = SudokuString("-BCDCDA-BAD--CBA")
    val puzzle2 = SudokuString("ABCDCDABBADCDCBA")
    val schema1 = SudokuSchema(puzzle1, puzzle1, Difficulty.MEDIUM, Dimension.FourByFour)
    val schema2 = SudokuSchema(puzzle2, puzzle2, Difficulty.HARD, Dimension.FourByFour)

    assertNotEquals(schema1, schema2, "Schemas with different properties should not be equal.")
  }
}
