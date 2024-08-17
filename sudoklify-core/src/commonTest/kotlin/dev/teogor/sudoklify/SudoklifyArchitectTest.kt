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

package dev.teogor.sudoklify

import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.puzzle.SudokuPuzzle
import dev.teogor.sudoklify.puzzle.SudokuSpec
import dev.teogor.sudoklify.schema.NoSchemasForDifficultyException
import dev.teogor.sudoklify.schema.NoSchemasForTypeException
import dev.teogor.sudoklify.schema.SudokuSchema
import dev.teogor.sudoklify.schema.SudokuSchemas
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalSudoklifyApi::class)
class SudoklifyArchitectTest {
  private val schema1 =
    SudokuSchema(
      puzzle = "-BCDCDA-BAD--CBA",
      solution = "ABCDCDABBADCDCBA",
      difficulty = Difficulty.EASY,
      dimension = Dimension.FourByFour,
    )

  private val schema2 =
    SudokuSchema(
      puzzle = "BADCD--AA--DCDAB",
      solution = "BADCDCBAABCDCDAB",
      difficulty = Difficulty.HARD,
      dimension = Dimension.FourByFour,
    )

  @Test
  fun constructor_withEmptySchemas_shouldThrowEmptySudokuSchemasException() {
    val emptySchemas = SudokuSchemas(emptyList())
    assertFailsWith<EmptySudokuSchemasException> {
      SudoklifyArchitect(emptySchemas)
    }
  }

  @Test
  fun constructor_withValidSchemas_shouldInitializeArchitectCorrectly() {
    val schemas =
      SudokuSchemas {
        add(schema1, schema2)
      }
    val architect = SudoklifyArchitect(schemas)
    assertNotNull(architect, "Architect should be initialized with valid schemas.")
  }

  @Test
  fun constructSudoku_withValidSpec_shouldGeneratePuzzle() {
    val schemas =
      SudokuSchemas {
        add(schema1, schema2)
      }
    val architect = SudoklifyArchitect(schemas)

    val sudokuSpec =
      SudokuSpec {
        type = Dimension.FourByFour
        difficulty = Difficulty.EASY
      }
    val puzzle = architect.constructSudoku(sudokuSpec)

    assertNotNull(puzzle, "Puzzle should be generated based on valid SudokuSpec.")
    assertTrue(puzzle.isValid(), "Generated puzzle should be valid.")
  }

  @Test
  fun constructSudoku_withDslConfig_shouldGeneratePuzzle() {
    val architect =
      SudoklifyArchitect {
        SudokuSchemas {
          add(schema1, schema2)
        }
      }

    val puzzle =
      architect.constructSudoku {
        type = Dimension.FourByFour
        difficulty = Difficulty.HARD
      }

    assertNotNull(puzzle, "Puzzle should be generated using DSL configuration.")
    assertTrue(puzzle.isValid(), "Generated puzzle should be valid.")
  }

  @Test
  fun createSudokuFromSpec_withInvalidType_shouldThrowException() {
    val invalidSpec =
      SudokuSpec {
        type = Dimension.NineByNine
        difficulty = Difficulty.EASY
      }
    val schemas =
      SudokuSchemas {
        add(schema1, schema2)
      }
    val architect = SudoklifyArchitect(schemas)

    assertFailsWith<NoSchemasForTypeException> {
      architect.constructSudoku(invalidSpec)
    }
  }

  @Test
  fun createSudokuFromSpec_withInvalidDifficulty_shouldThrowException() {
    val invalidSpec =
      SudokuSpec {
        type = Dimension.FourByFour
        difficulty = Difficulty.VERY_HARD
      }
    val schemas =
      SudokuSchemas {
        add(schema1, schema2)
      }
    val architect = SudoklifyArchitect(schemas)

    assertFailsWith<NoSchemasForDifficultyException> {
      architect.constructSudoku(invalidSpec)
    }
  }

  private fun SudokuPuzzle.isValid(): Boolean {
    return this.solution.isNotEmpty()
  }
}
