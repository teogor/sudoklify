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

import dev.teogor.sudoklify.puzzle.SudokuPuzzle
import dev.teogor.sudoklify.puzzle.SudokuSpec
import dev.teogor.sudoklify.schema.SudokuSchemas
import dev.teogor.sudoklify.schema.requireSchemasForDifficulty
import dev.teogor.sudoklify.schema.requireSchemasForType

/**
 * A class responsible for constructing Sudoku puzzles using provided Sudoku schemas.
 *
 * @property schemas The set of Sudoku schemas used for puzzle generation.
 *
 * @throws EmptySudokuSchemasException If the provided schemas are empty.
 *
 * Example usage:
 *
 * ```
 * val architect = SudoklifyArchitect(SudokuSchemas {
 *     add(schema1, schema2)
 * })
 *
 * val sudokuSpec = SudokuSpec {
 *     type = SudokuType.Sudoku4x4
 *     difficulty = Difficulty.EASY
 * }
 *
 * val puzzle = architect.constructSudoku(sudokuSpec)
 * ```
 */
public class SudoklifyArchitect(
  private val schemas: SudokuSchemas,
) {
  private lateinit var spec: SudokuSpec

  init {
    require(schemas.isNotEmpty()) {
      throw EmptySudokuSchemasException()
    }
  }

  /**
   * Constructs a Sudoku puzzle based on the provided configuration block.
   *
   * @param specConfig A configuration block that applies settings to the [SudokuSpec] builder.
   * @return The generated [SudokuPuzzle].
   */
  public fun constructSudoku(specConfig: SudokuSpec.Builder.() -> Unit): SudokuPuzzle {
    val localSpecBuilder = if (this::spec.isInitialized) {
      this.spec.toBuilder()
    } else {
      SudokuSpec.Builder()
    }
    val localSpec = localSpecBuilder.apply(specConfig).build()
    return constructSudoku(localSpec)
  }

  /**
   * Constructs a Sudoku puzzle based on the provided [SudokuSpec].
   *
   * @param spec The specification to use for puzzle generation.
   * @return The generated [SudokuPuzzle].
   */
  public fun constructSudoku(spec: SudokuSpec): SudokuPuzzle {
    this.spec = spec
    return createSudokuFromSpec(spec)
  }

  /**
   * Creates a Sudoku puzzle from the provided [SudokuSpec].
   *
   * @param spec The specification to use for puzzle generation.
   * @return The generated [SudokuPuzzle].
   */
  private fun createSudokuFromSpec(spec: SudokuSpec): SudokuPuzzle {
    schemas.requireSchemasForType(spec.type)
    schemas.requireSchemasForDifficulty(spec.difficulty)

    val assembler = SudoklifyAssembler(schemas, spec)
    return assembler.assembleSudoku()
  }
}

/**
 * Creates a new instance of [SudoklifyArchitect] using a DSL-style lambda to configure [SudokuSchemas].
 *
 * This function allows for a more intuitive and readable way to create a [SudoklifyArchitect] by using a DSL-style lambda to
 * directly configure the [SudokuSchemas]. It simplifies the process of setting up the Sudoku schemas and initializing
 * the architect with those configurations.
 *
 * **Example:**
 *
 * ```kotlin
 * val architect = SudoklifyArchitect {
 *     SudokuSchemas {
 *         add(schema1, schema2)
 *     }
 * }
 * ```
 *
 * @param block A lambda function that configures the [SudokuSchemas] using a DSL-style approach. The lambda should call
 *              the [SudokuSchemas.Builder] methods to define the schemas.
 * @return A new [SudoklifyArchitect] initialized with the configured [SudokuSchemas].
 *
 * @see SudoklifyArchitect
 * @see SudokuSchemas
 * @see SudokuSchemas.Builder
 * @see SudokuSchemas.Builder.add
 * @see SudokuSchemas.Builder.addAll
 */
@SudoklifyDsl
public inline fun SudoklifyArchitect(crossinline block: () -> SudokuSchemas): SudoklifyArchitect {
  return SudoklifyArchitect(block())
}

/**
 * A custom exception thrown when the Sudoku schemas list is empty.
 *
 * This exception indicates that at least one Sudoku schema must be provided to generate a Sudoku puzzle.
 *
 * Example usage:
 *
 * ```
 * throw EmptySudokuSchemasException()
 * ```
 *
 * For more detailed instructions and examples on providing Sudoku schemas, visit:
 * [Sudoklify Documentation - Sudoku Schemas](https://source.teogor.dev/sudoklify/sudoku-schemas)
 */
public class EmptySudokuSchemasException : Exception(
  """
  |The Sudoku schemas list is empty.
  |
  |To generate a Sudoku puzzle, you must provide at least one valid Sudoku schema.
  |Schemas define the structure and rules of the Sudoku puzzle and are essential for the architect to function.
  |
  |Here's how you can add schemas:
  |
  |```kt
  |SudoklifyArchitect {
  |  SudokuSchemas {
  |    add(schema1, schema2)
  |  }
  |}
  |```
  |
  |For more detailed instructions and examples, visit:
  |https://source.teogor.dev/sudoklify/sudoku-schemas
  """.trimMargin(),
)
