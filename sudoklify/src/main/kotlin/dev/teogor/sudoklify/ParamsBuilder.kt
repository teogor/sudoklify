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

package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.model.SudokuParams
import dev.teogor.sudoklify.types.Seed

/**
 * A builder class for configuring and creating instances of [SudokuGenerator].
 */
class ParamsBuilder {
  private var difficulty: Difficulty = Difficulty.EASY
  private var seed: Seed = 0
  private var gameType: GameType = GameType.Unspecified

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
   * @param gameType The type to set.
   * @return This builder instance for chaining.
   */
  internal fun setGameType(gameType: GameType) = apply { this.gameType = gameType }

  /**
   * Build and return a [SudokuParams] instance using the configured settings.
   *
   * @return A [SudokuParams] instance containing the configured difficulty, seed,
   *  and type for generating Sudoku puzzles.
   */
  fun build() = SudokuParams(
    difficulty,
    seed,
    gameType,
  )
}

/**
 * Create a [SudokuGenerator] using the provided configuration.
 * @param block Lambda for configuring the builder.
 * @return [SudokuGenerator] instance.
 */
inline fun sudokuParamsBuilder(
  crossinline block: ParamsBuilder.() -> Unit,
) = ParamsBuilder().apply(block).build()

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
fun ParamsBuilder.gameType(block: () -> GameType) {
  setGameType(block())
}
