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

package dev.teogor.sudoklify.schema

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.tokenizer.SudokuString
import dev.teogor.sudoklify.tokenizer.toSudokuString
import dev.teogor.sudoklify.util.buildLazyHashCode

/**
 * Represents a schema for generating Sudoku puzzles.
 *
 * This class encapsulates the essential elements of a Sudoku puzzle, including the initial puzzle layout,
 * the solution, the difficulty level, and the type of Sudoku puzzle.
 *
 * @property puzzle The puzzle layout as a [SudokuString]. This represents the initial state of the Sudoku grid, where some cells are pre-filled.
 * @property solution The solution layout as a [SudokuString]. This represents the completed Sudoku grid that solves the puzzle.
 * @property difficulty The difficulty level of the Sudoku puzzle. This is used to categorize the puzzle based on its complexity.
 * @property dimension The type of the Sudoku puzzle, defining the grid size and other characteristics (e.g., 9x9 classic Sudoku).
 */
class SudokuSchema(
  val puzzle: SudokuString,
  val solution: SudokuString,
  val difficulty: Difficulty,
  val dimension: Dimension,
) {
  fun copy(
    puzzle: SudokuString = this.puzzle,
    solution: SudokuString = this.solution,
    difficulty: Difficulty = this.difficulty,
    dimension: Dimension = this.dimension,
  ): SudokuSchema {
    return SudokuSchema(
      puzzle = puzzle,
      solution = solution,
      difficulty = difficulty,
      dimension = dimension,
    )
  }

  /**
   * Returns a string representation of this SudokuSchema.
   *
   * This includes the puzzle layout, solution layout, difficulty level, and the Sudoku type.
   *
   * @return A string representing the SudokuSchema in the format:
   * "SudokuSchema(puzzle=<puzzle>, solution=<solution>, difficulty=<difficulty>, sudokuType=<sudokuType>)"
   */
  override fun toString(): String {
    return buildString {
      append("SudokuSchema(puzzle=")
      append(puzzle)
      append(", solution=")
      append(solution)
      append(", difficulty=")
      append(difficulty)
      append(", sudokuType=")
      append(dimension)
      append(")")
    }
  }

  override fun hashCode(): Int = hash

  private val hash: Int by buildLazyHashCode {
    append(puzzle)
    append(solution)
    append(difficulty)
    append(dimension)
  }

  /**
   * Checks if this SudokuSchema is equal to another object.
   *
   * Two SudokuSchema instances are considered equal if they have the same puzzle layout, solution layout,
   * difficulty level, and Sudoku type.
   *
   * @param other The object to compare with this instance.
   * @return `true` if the objects are equal, `false` otherwise.
   */
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is SudokuSchema) return false
    return puzzle == other.puzzle &&
      solution == other.solution &&
      difficulty == other.difficulty &&
      dimension == other.dimension
  }
}

/**
 * Creates a new [SudokuSchema] instance with the specified puzzle, solution, difficulty, and Sudoku type.
 *
 * This function provides a convenient way to instantiate a [SudokuSchema], which encapsulates the core components
 * of a Sudoku puzzle. It converts the provided puzzle and solution strings into their respective [SudokuString]
 * representations before constructing the [SudokuSchema].
 *
 * **Example:**
 *
 * ```kotlin
 * val schema = SudokuSchema(
 *     puzzle = "123456789",
 *     solution = "987654321",
 *     difficulty = Difficulty.MEDIUM,
 *     sudokuType = SudokuType.Sudoku9x9
 * )
 * ```
 *
 * @param puzzle The initial puzzle layout as a string, representing the state of the Sudoku grid with some pre-filled cells.
 * @param solution The solution layout as a string, representing the completed Sudoku grid that solves the puzzle.
 * @param difficulty The difficulty level of the Sudoku puzzle, used to categorize the puzzle's complexity.
 * @param dimension The type of the Sudoku puzzle, which defines the grid size and other characteristics (e.g., 9x9 classic Sudoku).
 * @return A new [SudokuSchema] instance with the specified properties.
 */
fun SudokuSchema(
  puzzle: String,
  solution: String,
  difficulty: Difficulty,
  dimension: Dimension,
): SudokuSchema {
  return SudokuSchema(
    puzzle = puzzle.toSudokuString(),
    solution = solution.toSudokuString(),
    difficulty = difficulty,
    dimension = dimension,
  )
}
