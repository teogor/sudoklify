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

package dev.teogor.sudoklify

import dev.teogor.sudoklify.puzzle.SudokuPuzzle
import dev.teogor.sudoklify.puzzle.SudokuSpec
import dev.teogor.sudoklify.random.SeededRandom
import dev.teogor.sudoklify.random.randomItem
import dev.teogor.sudoklify.schema.SudokuSchemas
import dev.teogor.sudoklify.tokenizer.Tokenizer
import kotlin.random.Random

/**
 * Assembles a Sudoku puzzle based on the provided configuration and specifications.
 *
 * The [SudoklifyAssembler] class is responsible for creating Sudoku puzzles by selecting a random schema
 * according to the puzzle's difficulty, shuffling and rotating the layout, generating the token sequence for
 * the puzzle and its solution, and returning a [SudokuPuzzle] object.
 *
 * **Example:**
 *
 * ```kotlin
 * // Create a SudokuSchemas with the schemas
 * val schemas = SudokuSchemas(*)
 *
 * // Define Sudoku specifications
 * val spec = SudokuSpec {
 *     type = SudokuType.Sudoku9x9
 *     difficulty = Difficulty.EASY
 *     seed = 1234L
 * }
 *
 * // Create a SudoklifyAssembler
 * val assembler = SudoklifyAssembler(schemas, spec)
 *
 * // Assemble a Sudoku puzzle
 * val puzzle = assembler.assembleSudoku()
 * println(puzzle)
 * ```
 *
 * @property schemas A [SudokuSchemas] instance containing the Sudoku schemas.
 * @property spec A [SudokuSpec] instance specifying the puzzle's configuration.
 * @property sudokuLayoutManipulator A [SudokuLayoutManipulator] responsible for shuffling and rotating the Sudoku layout.
 * @property sudokuTokenMapper A [SudokuTokenMapper] used to generate token sequences for the puzzle and solution.
 * @property random A [Random] instance used for generating random selections.
 *
 * @constructor Creates a new [SudoklifyAssembler] instance with the provided parameters.
 *
 * @see SudokuSchemas
 * @see SudokuSpec
 * @see SudokuLayoutManipulator
 * @see SudokuTokenMapper
 * @see SudokuPuzzle
 */
class SudoklifyAssembler(
  val schemas: SudokuSchemas,
  val spec: SudokuSpec,
  private val sudokuLayoutManipulator: SudokuLayoutManipulator,
  private val sudokuTokenMapper: SudokuTokenMapper,
  private val random: Random,
) {
  /**
   * Creates a new Sudoku puzzle based on the specified configuration.
   *
   * This method selects a random schema according to the puzzle's difficulty, shuffles and rotates the layout,
   * generates the token sequence for the puzzle and solution, and returns a [SudokuPuzzle] object.
   *
   * **Example:**
   *
   * ```kotlin
   * val puzzle = assembler.assembleSudoku()
   * println("Puzzle:")
   * println(puzzle.givens)
   * println("Solution:")
   * println(puzzle.solution)
   * ```
   *
   * @return A [SudokuPuzzle] object containing the generated puzzle, solution, and other metadata.
   */
  fun assembleSudoku(): SudokuPuzzle {
    val randomItem =
      schemas
        .getSeedsBySize(spec.type)
        .getSeedsByDifficulty(spec.difficulty)
        .randomItem(random)

    val layout =
      sudokuLayoutManipulator.shuffle(
        sudokuLayoutManipulator.rotate(sudokuLayoutManipulator.constructBase()),
      )
    val tokenMap = sudokuTokenMapper.getTokenMap()

    val puzzle = sudokuTokenMapper.getSequence(layout, randomItem.puzzle, tokenMap)
    val solution = sudokuTokenMapper.getSequence(layout, randomItem.solution, tokenMap)

    return SudokuPuzzle(
      difficulty = spec.difficulty,
      type = spec.type,
      seed = spec.seed,
      givens = puzzle.toGivens(),
      solution = solution.toSolution(),
      hints = emptyList(),
    )
  }

  /**
   * Converts a 2D array of strings to a list of givens for the puzzle.
   *
   * **Example:**
   *
   * ```kotlin
   * val array = arrayOf(
   *     arrayOf("1", "0", "0", "0"),
   *     arrayOf("0", "2", "0", "0"),
   *     arrayOf("0", "0", "3", "0"),
   *     arrayOf("0", "0", "0", "4")
   * )
   * val givens = array.toGivens()
   * println(givens)
   * ```
   *
   * @return A list of [SudokuPuzzle.Givens] objects representing the non-zero values in the puzzle.
   */
  private fun Array<Array<String>>.toGivens(): List<SudokuPuzzle.Givens> {
    return map { it.toList() }
      .mapIndexed { row, cols ->
        cols.mapIndexed { col, value ->
          SudokuPuzzle.Givens(
            value = value.toIntOrNull() ?: 0,
            row = row,
            col = col,
          )
        }
      }
      .flatten()
      .filter { it.value != 0 }
  }

  /**
   * Converts a 2D array of strings to a list of solution values.
   *
   * **Example:**
   *
   * ```kotlin
   * val array = arrayOf(
   *     arrayOf("1", "2", "3", "4"),
   *     arrayOf("5", "6", "7", "8"),
   *     arrayOf("9", "1", "2", "3"),
   *     arrayOf("4", "5", "6", "7")
   * )
   * val solution = array.toSolution()
   * println(solution)
   * ```
   *
   * @return A list of lists representing the solution values in the puzzle.
   */
  private fun Array<Array<String>>.toSolution(): List<List<Int>> {
    return map { it.toList() }
      .map { cols ->
        cols.map { value ->
          value.toIntOrNull() ?: 0
        }
      }
  }

  /**
   * Factory class for creating instances of [SudoklifyAssembler].
   *
   * The [Factory] class provides methods to create the necessary components for the [SudoklifyAssembler],
   * including the layout transformer and token sequence provider, based on the provided [SudokuSpec].
   *
   * **Example:**
   *
   * ```kotlin
   * val factory = SudoklifyAssembler.Factory(schemas, spec)
   * val assembler = factory.createAssembler()
   * val puzzle = assembler.assembleSudoku()
   * println(puzzle)
   * ```
   *
   * @property spec The specification used to configure the [SudoklifyAssembler].
   */
  class Factory(
    private val schemas: SudokuSchemas,
    private val spec: SudokuSpec,
  ) {
    private val random: Random = SeededRandom(spec.seed)

    /**
     * Creates a [SudokuLayoutManipulator] instance for transforming the Sudoku layout.
     *
     * @return A [SudokuLayoutManipulator] instance for transforming the Sudoku layout.
     */
    private fun createLayoutGenerator(): SudokuLayoutManipulator {
      return SudokuLayoutManipulator.default(
        boxDigits = spec.type.uniqueDigitsCount,
        random = random,
      )
    }

    /**
     * Creates a [SudokuTokenMapper] instance for providing token sequences.
     *
     * @return A [SudokuTokenMapper] instance for generating token sequences for the Sudoku puzzle.
     */
    private fun createBoardElementsFactory(): SudokuTokenMapper {
      val tokenizer =
        Tokenizer.create(
          digits = spec.type.uniqueDigitsCount,
        )
      return SudokuTokenMapper.default(
        tokenizer = tokenizer,
        random = random,
        boxDigits = spec.type.uniqueDigitsCount,
      )
    }

    /**
     * Creates a [SudoklifyAssembler] instance with the configured [SudokuSpec].
     *
     * **Example:**
     *
     * ```kotlin
     * val assembler = factory.createAssembler()
     * val puzzle = assembler.assembleSudoku()
     * println(puzzle)
     * ```
     *
     * @return A [SudoklifyAssembler] instance ready to generate Sudoku puzzles.
     */
    fun createAssembler(): SudoklifyAssembler {
      return SudoklifyAssembler(
        schemas = schemas,
        spec = spec,
        random = random,
        sudokuLayoutManipulator = createLayoutGenerator(),
        sudokuTokenMapper = createBoardElementsFactory(),
      )
    }
  }
}

/**
 * Creates a [SudoklifyAssembler] instance using a DSL-style function.
 *
 * This function simplifies the creation of a [SudoklifyAssembler] by using a DSL-style syntax,
 * allowing configuration and assembly in a concise and expressive manner.
 *
 * **Example:**
 *
 * ```kotlin
 * val assembler = SudoklifyAssembler(schemas, spec)
 * ```
 *
 * @param schemas The [SudokuSchemas] containing Sudoku schemas.
 * @param spec The [SudokuSpec] specifying the puzzle's configuration.
 * @return A [SudoklifyAssembler] instance configured with the provided parameters.
 */
@SudoklifyDsl
fun SudoklifyAssembler(
  schemas: SudokuSchemas,
  spec: SudokuSpec,
): SudoklifyAssembler {
  val factory = SudoklifyAssembler.Factory(schemas, spec)
  val assembler = factory.createAssembler()
  return assembler
}

/**
 * Creates a [SudoklifyAssembler] instance using a DSL-style function with a configuration block.
 *
 * This function simplifies the creation of a [SudoklifyAssembler] and immediately executes the
 * provided block of code. This allows for a concise and expressive configuration of the assembler
 * followed by immediate execution.
 *
 * **Example:**
 *
 * ```kotlin
 * val sudoku = SudoklifyAssembler(schemas, spec) {
 *     assembleSudoku()
 * }
 * println(sudoku)
 * ```
 *
 * @param schemas The [SudokuSchemas] containing Sudoku schemas.
 * @param spec The [SudokuSpec] specifying the puzzle's configuration.
 * @param block A block of code to be executed on the created [SudoklifyAssembler] instance.
 * @return The result of executing the block on the [SudoklifyAssembler] instance.
 */
@Suppress("FunctionName")
@SudoklifyDsl
fun <T> SudoklifyAssembler(
  schemas: SudokuSchemas,
  spec: SudokuSpec,
  block: SudoklifyAssembler.() -> T,
): T {
  val factory = SudoklifyAssembler.Factory(schemas, spec)
  val assembler = factory.createAssembler()
  return assembler.block()
}
