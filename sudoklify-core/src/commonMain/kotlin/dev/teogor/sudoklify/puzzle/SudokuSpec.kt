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

@file:OptIn(ExperimentalSudoklifyApi::class)

package dev.teogor.sudoklify.puzzle

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.SudoklifyDsl
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.Seed

/**
 * Data class representing specs for a Sudoku puzzle.
 *
 * @property type The type of Sudoku puzzle (e.g., 4x4, 9x9).
 * @property seed The seed value used for generating the puzzle.
 * @property difficulty The difficulty level of the puzzle.
 */
data class SudokuSpec(
  val type: Dimension,
  val seed: Seed,
  val difficulty: Difficulty,
) {
  /**
   * Creates a new [Builder] instance with the same properties as this [SudokuSpec].
   *
   * @return A new [Builder] instance.
   */
  @OptIn(ExperimentalSudoklifyApi::class)
  fun toBuilder(): Builder {
    return Builder()
      .difficulty(this.difficulty)
      .seed(this.seed)
      .type(this.type)
  }

  /**
   * Builder class for creating [SudokuSpec] instances.
   *
   * @property difficulty The difficulty level of the puzzle.
   * @property seed The seed value used for generating the puzzle.
   * @property type The type of Sudoku puzzle.
   */
  data class Builder(
    var difficulty: Difficulty = Difficulty.EASY,
    var seed: Seed = Seed.Random(),
    var type: Dimension = Dimension.Unspecified,
  ) {
    /**
     * Sets the difficulty level for the Sudoku puzzle.
     *
     * @param difficulty The desired difficulty level.
     * @return This builder instance.
     */
    fun difficulty(difficulty: Difficulty) = apply { this.difficulty = difficulty }

    /**
     * Sets the difficulty level for the Sudoku puzzle using a lambda function.
     *
     * @param difficulty A lambda that returns the desired difficulty level.
     * @return This builder instance.
     */
    inline fun difficulty(crossinline difficulty: () -> Difficulty) =
      apply {
        this.difficulty = difficulty()
      }

    /**
     * Sets the seed value for the Sudoku puzzle.
     *
     * @param seed The desired seed value.
     * @return This builder instance.
     */
    fun seed(seed: Seed) = apply { this.seed = seed }

    /**
     * Sets the seed value for the Sudoku puzzle using a lambda function.
     *
     * @param seed A lambda that returns the desired seed value.
     * @return This builder instance.
     */
    inline fun seed(crossinline seed: () -> Seed) =
      apply {
        this.seed = seed()
      }

    /**
     * Sets the type of Sudoku puzzle.
     *
     * @param type The desired type of Sudoku puzzle.
     * @return This builder instance.
     */
    fun type(type: Dimension) = apply { this.type = type }

    /**
     * Sets the type of Sudoku puzzle using a lambda function.
     *
     * @param type A lambda that returns the desired type of Sudoku puzzle.
     * @return This builder instance.
     */
    inline fun type(crossinline type: () -> Dimension) =
      apply {
        this.type = type()
      }

    /**
     * Builds a [SudokuSpec] instance with the configured properties.
     *
     * @return A new [SudokuSpec] instance.
     */
    fun build(): SudokuSpec {
      return SudokuSpec(
        type = type,
        difficulty = difficulty,
        seed = seed,
      )
    }
  }
}

/**
 * DSL function for creating a [SudokuSpec] instance using a builder.
 *
 * **Example:**
 *
 * ```kotlin
 * val spec = SudokuSpec {
 *     difficulty(Difficulty.HARD)
 *     seed(createSeed(1234L))
 *     type(SudokuType.Sudoku9x9)
 * }
 * ```
 *
 * @param block A block of code that configures the [SudokuSpec.Builder].
 * @return A new [SudokuSpec] instance configured by the builder.
 */
@SudoklifyDsl
fun SudokuSpec(block: SudokuSpec.Builder.() -> Unit): SudokuSpec {
  val builder = SudokuSpec.Builder()
  builder.apply(block)
  return builder.build()
}

/**
 * DSL function for updating an existing [SudokuSpec] instance using a builder.
 *
 * **Example:**
 *
 * ```kotlin
 * val updatedSpec = SudokuSpec(existingMetadata) {
 *     difficulty(Difficulty.MEDIUM)
 *     seed(createSeed(5678L))
 * }
 * ```
 *
 * @param spec The existing [SudokuSpec] instance to update.
 * @param block A block of code that configures the [SudokuSpec.Builder].
 * @return A new [SudokuSpec] instance with the updated properties.
 */
@SudoklifyDsl
fun SudokuSpec(
  spec: SudokuSpec,
  block: SudokuSpec.Builder.() -> Unit,
): SudokuSpec {
  val builder = spec.toBuilder()
  builder.apply(block)
  return builder.build()
}
