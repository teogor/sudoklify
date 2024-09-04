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

package dev.teogor.sudoklify.presets

import dev.teogor.sudoklify.schema.SudokuSchemas
import dev.teogor.sudoklify.schema.addAll

/**
 * Loads a set of predefined Sudoku schemas that are included in the Sudoklify library.
 *
 * This function returns a [SudokuSchemas] object populated with a collection of seeds
 * for various Sudoku grid sizes, including:
 * - 4x4 grids (four digits)
 * - 9x9 grids (nine digits)
 * - 16x16 grids (sixteen digits)
 * - 25x25 grids (twenty-five digits)
 *
 * Each seed is a predefined configuration that can be used to generate Sudoku puzzles of
 * varying sizes and complexities.
 *
 * Example usage:
 * ```kotlin
 * val schemas = loadPresetSchemas()
 * // Use the schemas to generate or solve Sudoku puzzles.
 * ```
 *
 * @return A [SudokuSchemas] object containing all predefined seeds.
 */
public fun loadPresetSchemas(): SudokuSchemas {
  return SudokuSchemas {
    addAll(fourDigitsSchemas)
    addAll(nineDigitsSchemas)
    addAll(sixteenDigitsSchemas)
    addAll(twentyFiveDigitsSchemas)
  }
}
