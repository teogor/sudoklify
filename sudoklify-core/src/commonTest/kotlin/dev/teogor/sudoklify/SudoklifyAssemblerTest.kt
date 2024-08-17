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
import dev.teogor.sudoklify.components.Seed
import dev.teogor.sudoklify.components.createSeed
import dev.teogor.sudoklify.puzzle.SudokuSpec
import dev.teogor.sudoklify.schema.SudokuSchema
import dev.teogor.sudoklify.schema.SudokuSchemas
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalSudoklifyApi::class)
class SudoklifyAssemblerTest {
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

  private val schemas =
    SudokuSchemas {
      add(schema1, schema2)
    }

  private val spec =
    SudokuSpec {
      type = Dimension.FourByFour
      difficulty = Difficulty.EASY
      seed = createSeed(123L)
    }

  @Test
  fun assembleSudoku_withValidSpec_shouldReturnNonNullPuzzle() {
    val assembler = SudoklifyAssembler(schemas, spec)

    val puzzle = assembler.assembleSudoku()

    assertNotNull(puzzle, "Puzzle should be generated.")
  }

  @Test
  fun assembleSudoku_withValidSpec_shouldReturnPuzzleWithCorrectProperties() {
    val assembler = SudoklifyAssembler(schemas, spec)

    val puzzle = assembler.assembleSudoku()

    assertEquals(
      spec.difficulty,
      puzzle.difficulty,
      "Difficulty should match the specification.",
    )
    assertEquals(spec.type, puzzle.type, "Sudoku type should match the specification.")
    assertEquals(spec.seed, puzzle.seed, "Seed should match the specification.")
  }

  @Test
  fun assembleSudoku_withValidSpec_shouldUseRandomSeed() {
    val randomSeed = Seed.Random()
    val updatedSpec =
      SudokuSpec {
        type = Dimension.FourByFour
        difficulty = Difficulty.EASY
        seed = randomSeed
      }
    val assembler = SudoklifyAssembler(schemas, updatedSpec)

    val puzzle = assembler.assembleSudoku()

    assertEquals(
      randomSeed,
      puzzle.seed,
      "Random seed should match the specification.",
    )
  }

  @Test
  fun assembleSudoku_withDifferentDifficulty_shouldReturnDifferentPuzzle() {
    val easySpec =
      SudokuSpec {
        type = Dimension.FourByFour
        difficulty = Difficulty.EASY
        seed = createSeed(123L)
      }
    val hardSpec =
      SudokuSpec {
        type = Dimension.FourByFour
        difficulty = Difficulty.HARD
        seed = createSeed(123L)
      }
    val easyAssembler = SudoklifyAssembler(schemas, easySpec)
    val hardAssembler = SudoklifyAssembler(schemas, hardSpec)

    val easyPuzzle = easyAssembler.assembleSudoku()
    val hardPuzzle = hardAssembler.assembleSudoku()

    assertNotEquals(
      easyPuzzle,
      hardPuzzle,
      "Puzzles with different difficulties should be different.",
    )
  }

  @Test
  fun factory_withValidSpec_shouldCreateAssembler() {
    val factory = SudoklifyAssembler.Factory(schemas, spec)
    val assembler = factory.createAssembler()

    assertNotNull(assembler, "Assembler should be created by the factory.")
    assertEquals(
      schemas,
      assembler.schemas,
      "Assembler should have the correct schemas.",
    )
    assertEquals(spec, assembler.spec, "Assembler should have the correct specification.")
  }

  @Test
  fun sudoklifyAssemblerDsl_withValidSpec_shouldGeneratePuzzle() {
    val puzzle =
      SudoklifyAssembler(schemas, spec) {
        assembleSudoku()
      }

    assertNotNull(puzzle, "Puzzle should be generated using DSL.")
  }

  @Test
  fun sudoklifyAssemblerDsl_withBlock_shouldExecuteBlock() {
    var blockExecuted = false

    SudoklifyAssembler(schemas, spec) {
      blockExecuted = true
      assembleSudoku()
    }

    assertTrue(blockExecuted, "Block should be executed in DSL function.")
  }
}
