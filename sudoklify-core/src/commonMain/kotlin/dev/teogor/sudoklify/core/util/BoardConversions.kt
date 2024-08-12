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

package dev.teogor.sudoklify.core.util

import dev.teogor.sudoklify.common.types.SudokuType

/**
 * Converts a `Board` object to a Sudoku string representation.
 *
 * @return A `SudokuString` representing the board.
 */
fun Array<Array<String>>.toSequenceString(): String =
  joinToString("") {
    it.joinToString("")
  }

/**
 * Converts a `SudokuString` to a `Board` object with the specified
 * number of digits per box.
 *
 * @param boxDigits The number of digits used to represent each box
 * in the Sudoku puzzle.
 *
 * @return A `Board` representing the Sudoku puzzle.
 */
@Deprecated("Use the `toBoard(sudokuType: SudokuType)` function instead.")
fun String.toBoard(boxDigits: Int): Array<Array<String>> {
  return chunked(boxDigits)
    .map { chunk -> chunk.map { it.toString() }.toTypedArray() }
    .toTypedArray()
}

/**
 * Converts a `SudokuString` to a list of lists of strings, representing
 * the Sudoku puzzle in a more granular format.
 *
 * @param sudokuType The sudoku type, which determines the size of the Sudoku
 * puzzle.
 *
 * @return A list of lists of strings representing the Sudoku puzzle.
 */
fun String.toBoard(sudokuType: SudokuType): Array<Array<String>> {
  val regex = Regex("([A-I][a-z]+)|-|[A-I]")
  val matches = regex.findAll(this)
  val matchedTokens = ArrayList<String>()
  matches.forEach { matchedTokens.add(it.value) }
  return matchedTokens
    .chunked(sudokuType.uniqueDigitsCount)
    .map { row -> row.map { it }.toTypedArray() }
    .toTypedArray()
}
