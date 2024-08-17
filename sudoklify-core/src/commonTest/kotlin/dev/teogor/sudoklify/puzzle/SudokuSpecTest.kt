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

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.createSeed
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

@OptIn(ExperimentalSudoklifyApi::class)
class SudokuSpecTest {
  @Test
  fun constructor_shouldSetPropertiesCorrectly() {
    val spec = SudokuSpec(Dimension.FourByFour, createSeed(123L), Difficulty.MEDIUM)
    assertEquals(Dimension.FourByFour, spec.type, "Type should be Sudoku4x4")
    assertEquals(createSeed(123L), spec.seed, "Seed should be 123L")
    assertEquals(Difficulty.MEDIUM, spec.difficulty, "Difficulty should be MEDIUM")
  }

  @Test
  fun toBuilder_shouldCreateBuilderWithCurrentValues() {
    val spec = SudokuSpec(Dimension.NineByNine, createSeed(456L), Difficulty.HARD)
    val builder = spec.toBuilder()
    assertEquals(Dimension.NineByNine, builder.type, "Builder should retain the type Sudoku9x9")
    assertEquals(createSeed(456L), builder.seed, "Builder should retain the seed 456L")
    assertEquals(Difficulty.HARD, builder.difficulty, "Builder should retain the difficulty HARD")
  }

  @Test
  fun builder_shouldSetPropertiesCorrectly() {
    val spec =
      SudokuSpec {
        difficulty(Difficulty.VERY_EASY)
        seed(createSeed(789L))
        type(Dimension.EightyOneByEightyOne)
      }
    assertEquals(Difficulty.VERY_EASY, spec.difficulty, "Difficulty should be VERY_EASY")
    assertEquals(createSeed(789L), spec.seed, "Seed should be 789L")
    assertEquals(Dimension.EightyOneByEightyOne, spec.type, "Type should be Sudoku81x81")
  }

  @Test
  fun dsl_shouldModifyExistingMetadata() {
    val originalMetadata = SudokuSpec(Dimension.EightByEight, createSeed(10L), Difficulty.EASY)
    val modifiedMetadata =
      SudokuSpec(originalMetadata) {
        difficulty(Difficulty.HARD)
        type(Dimension.NineByNine)
      }
    assertEquals(
      Difficulty.HARD,
      modifiedMetadata.difficulty,
      "Difficulty should be changed to HARD",
    )
    assertEquals(createSeed(10L), modifiedMetadata.seed, "Seed should remain unchanged at 10L")
    assertEquals(Dimension.NineByNine, modifiedMetadata.type, "Type should be changed to Sudoku9x9")
  }

  @Test
  fun dsl_shouldUseDefaultValuesForMissingProperties() {
    val spec =
      SudokuSpec {
        type(Dimension.FourByFour)
      }
    assertEquals(Dimension.FourByFour, spec.type, "Type should be Sudoku4x4")
    assertEquals(Difficulty.EASY, spec.difficulty, "Default difficulty should be EASY")
    assertTrue(spec.seed.value > 0L, "Seed should be generated and greater than 0")
  }

  @Test
  fun typeLambda_shouldSetTypeCorrectly() {
    val spec =
      SudokuSpec {
        type { Dimension.FourByFour }
      }
    assertEquals(Dimension.FourByFour, spec.type, "Type should be set to Sudoku4x4")
  }

  @Test
  fun seedLambda_shouldSetSeedCorrectly() {
    val seedValue = 12345L
    val spec =
      SudokuSpec {
        seed { createSeed(seedValue) }
      }
    assertEquals(createSeed(seedValue), spec.seed, "Seed should be set to $seedValue")
  }

  @Test
  fun difficultyLambda_shouldSetDifficultyCorrectly() {
    val spec =
      SudokuSpec {
        difficulty { Difficulty.HARD }
      }
    assertEquals(Difficulty.HARD, spec.difficulty, "Difficulty should be set to HARD")
  }

  @Test
  fun lambdaFunctions_shouldNotModifyBuilderInstance() {
    val builder = SudokuSpec.Builder()
    val originalBuilder = builder
    builder.type { Dimension.NineByNine }
    builder.seed { createSeed(123L) }
    builder.difficulty { Difficulty.MEDIUM }
    assertSame(originalBuilder, builder, "Builder instance should remain the same")
  }
}
