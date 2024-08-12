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

package dev.teogor.sudoklify.core.generation

import dev.teogor.sudoklify.common.model.SudokuBlueprint
import dev.teogor.sudoklify.common.model.SudokuParams
import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.Seed
import dev.teogor.sudoklify.common.types.SudokuType
import dev.teogor.sudoklify.ktx.createSeed

/**
 * A builder class for configuring and creating instances of [SudokuGenerator].
 */
class ParamsBuilder {
  private var seeds: Array<SudokuBlueprint> = emptyArray()
  private var difficulty: Difficulty = Difficulty.EASY
  private var seed: Seed = createSeed(0)
  private var sudokuType: SudokuType = SudokuType.Unspecified

  /**
   * Set the difficulty level of the Sudoku puzzle.
   * @param seeds The difficulty level to set.
   * @return This builder instance for chaining.
   */
  internal fun setSeeds(seeds: Array<SudokuBlueprint>) = apply { this.seeds = seeds }

  /**
   * Set the difficulty level of the Sudoku puzzle.
   * @param difficulty The difficulty level to set.
   * @return This builder instance for chaining.
   */
  internal fun setDifficulty(difficulty: Difficulty) = apply { this.difficulty = difficulty }

  /**
   * Set the seed for generating random numbers.
   * @param seed The seed to set.
   * @return This builder instance for chaining.
   */
  internal fun setSeed(seed: Seed) = apply { this.seed = seed }

  /**
   * Set the type of the Sudoku grid.
   * @param sudokuType The type to set.
   * @return This builder instance for chaining.
   */
  internal fun setSudokuType(sudokuType: SudokuType) = apply { this.sudokuType = sudokuType }

  /**
   * Build and return a [SudokuParams] instance using the configured settings.
   *
   * @return A [SudokuParams] instance containing the configured difficulty, seed,
   *  and type for generating Sudoku puzzles.
   */
  fun build() =
    SudokuParams(
      seeds,
      difficulty,
      seed,
      sudokuType,
    )
}

/**
 * Create a [SudokuGenerator] using the provided configuration.
 * @param block Lambda for configuring the builder.
 * @return [SudokuGenerator] instance.
 */
inline fun sudokuParamsBuilder(crossinline block: ParamsBuilder.() -> Unit) =
  ParamsBuilder().apply(block).build()

/**
 * Set the seed for generating random numbers using a lambda.
 * @param block Lambda providing the seed.
 */
fun ParamsBuilder.seeds(block: () -> Array<SudokuBlueprint>) {
  setSeeds(block())
}

/**
 * Set the difficulty level of the Sudoku puzzle using a lambda.
 * @param block Lambda providing the difficulty level.
 */
fun ParamsBuilder.difficulty(block: () -> Difficulty) {
  setDifficulty(block())
}

/**
 * Set the seed for generating random numbers using a lambda.
 * @param block Lambda providing the seed.
 */
fun ParamsBuilder.seed(block: () -> Seed) {
  setSeed(block())
}

/**
 * Set the type of the Sudoku grid using a lambda.
 * @param block Lambda providing the type.
 */
fun ParamsBuilder.sudokuType(block: () -> SudokuType) {
  setSudokuType(block())
}
