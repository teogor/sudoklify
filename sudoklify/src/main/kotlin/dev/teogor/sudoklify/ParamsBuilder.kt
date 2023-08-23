package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.SudokuParams
import dev.teogor.sudoklify.model.Type
import dev.teogor.sudoklify.types.Seed

/**
 * A builder class for configuring and creating instances of [SudokuGenerator].
 */
class ParamsBuilder {
  private var difficulty: Difficulty = Difficulty.EASY
  private var seed: Seed = 0
  private var type: Type = Type.THREE_BY_THREE

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
   * @param type The type to set.
   * @return This builder instance for chaining.
   */
  internal fun setType(type: Type) = apply { this.type = type }

  /**
   * Build and return a [SudokuParams] instance using the configured settings.
   *
   * @return A [SudokuParams] instance containing the configured difficulty, seed,
   *  and type for generating Sudoku puzzles.
   */
  fun build() = SudokuParams(
    difficulty,
    seed,
    type,
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
fun ParamsBuilder.type(block: () -> Type) {
  setType(block())
}
