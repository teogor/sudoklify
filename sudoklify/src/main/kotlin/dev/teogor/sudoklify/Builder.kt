package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.Type
import kotlin.random.Random

/**
 * A builder class for configuring and creating instances of [SudokuGenerator].
 */
class Builder {
  private var difficulty: Difficulty = Difficulty.EASY
  private var seed: Long = 0
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
  internal fun setSeed(seed: Long) = apply { this.seed = seed }

  /**
   * Set the type of the Sudoku grid.
   * @param type The type to set.
   * @return This builder instance for chaining.
   */
  internal fun setType(type: Type) = apply { this.type = type }

  /**
   * Build and return a [SudokuGenerator] instance using the configured settings.
   * @return A configured [SudokuGenerator] instance.
   */
  fun build() = SudokuGenerator(Random(seed), type, difficulty)
}

/**
 * Create a [SudokuGenerator] using the provided configuration.
 * @param block Lambda for configuring the builder.
 * @return [SudokuGenerator] instance.
 */
inline fun sudokuBuilder(crossinline block: Builder.() -> Unit) = Builder().apply(block).build()

/**
 * Set the difficulty level of the Sudoku puzzle using a lambda.
 * @param block Lambda providing the difficulty level.
 */
fun Builder.difficulty(block: () -> Difficulty) {
  setDifficulty(block())
}

/**
 * Set the seed for generating random numbers using a lambda.
 * @param block Lambda providing the seed.
 */
fun Builder.seed(block: () -> Long) {
  setSeed(block())
}

/**
 * Set the type of the Sudoku grid using a lambda.
 * @param block Lambda providing the type.
 */
fun Builder.type(block: () -> Type) {
  setType(block())
}
