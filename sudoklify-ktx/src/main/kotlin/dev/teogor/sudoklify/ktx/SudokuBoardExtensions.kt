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

package dev.teogor.sudoklify.ktx

import dev.teogor.sudoklify.common.InternalSudoklifyApi
import dev.teogor.sudoklify.common.types.BoardCell
import dev.teogor.sudoklify.common.types.SudokuType

/**
 * Converts a two-dimensional list of integers representing a Sudoku board to a
 * string representation.
 *
 * @receiver The two-dimensional list of integers representing the Sudoku board.
 * @return The string representation of the Sudoku board, where each cell is encoded as a
 * [BoardCell] using its default encoding.
 */
fun List<List<Int>>.mapToSudokuString(): String {
  return flatMap { cells ->
    cells.map { cell ->
      cell.toBoardCell()
    }
  }.joinToString("")
}

/**
 * Converts a two-dimensional list of elements to a string representation, applying a custom
 * value mapper to each element.
 *
 * @receiver The two-dimensional list of elements.
 * @param valueMapper A function that maps each element of the list to an integer to be used
 * in the string representation.
 * @return The string representation of the Sudoku board, where each cell is encoded using
 * the provided [valueMapper].
 */
inline fun <T> List<List<T>>.mapToSudokuString(crossinline valueMapper: T.() -> Int): String {
  return flatMap { cells ->
    cells.map { cell ->
      valueMapper(cell).toBoardCell()
    }
  }.joinToString("")
}

/**
 * Converts a string representation of a Sudoku board to a two-dimensional list of integers.
 *
 * @receiver The string representing the Sudoku board.
 * @param sudokuType The type of Sudoku board (e.g., 4x4, 9x9), used to determine the size
 * and structure of the board.
 * @return A two-dimensional list of integers representing the Sudoku board, where each cell
 * is converted to an integer using its default decoding.
 */
@OptIn(InternalSudoklifyApi::class)
fun String.mapToSudokuBoard(sudokuType: SudokuType): List<List<Int>> {
  return getCells()
    .chunked(sudokuType.cells)
    .map { row -> row.map { it.toInt() } }
}

/**
 * Converts a string representation of a Sudoku board to a two-dimensional list of custom
 * elements, applying a custom value mapper to each cell value.
 *
 * @receiver The string representing the Sudoku board.
 * @param sudokuType The type of Sudoku board (e.g., 4x4, 9x9), used to determine the size
 * and structure of the board.
 * @param valueMapper A function that maps each integer value in the string representation
 * to a custom element.
 * @return A two-dimensional list of custom elements representing the Sudoku board, where
 * each cell is decoded and mapped using the provided [valueMapper].
 */
@OptIn(InternalSudoklifyApi::class)
inline fun <T> String.mapToSudokuBoard(
  sudokuType: SudokuType,
  crossinline valueMapper: Int.() -> T,
): List<List<T>> {
  return getCells()
    .chunked(sudokuType.cells)
    .map { row -> row.map { valueMapper(it.toInt()) } }
}

/**
 * Converts a string representation of a Sudoku board to a two-dimensional list of custom
 * elements, allowing access to row and column indices during mapping.
 *
 * @receiver The string representing the Sudoku board.
 * @param sudokuType The type of Sudoku board (e.g., 4x4, 9x9), used to determine the size
 * and structure of the board.
 * @param valueMapper A function that maps each integer value in the string representation
 * to a custom element, additionally providing the row and column indices of the cell.
 * @return A two-dimensional list of custom elements representing the Sudoku board, where
 * each cell is decoded and mapped using the provided [valueMapper], incorporating its row and column indices.
 */
@OptIn(InternalSudoklifyApi::class)
inline fun <T> String.mapIndexedToSudokuBoard(
  sudokuType: SudokuType,
  crossinline valueMapper: (value: Int, row: Int, column: Int) -> T,
): List<List<T>> {
  return getCells()
    .chunked(sudokuType.cells)
    .mapIndexed { row, rowElements ->
      rowElements.mapIndexed { column, value ->
        valueMapper(value.toInt(), row, column)
      }
    }
}

/**
 * Extracts individual cell values from the string representation of a Sudoku board.
 *
 * @receiver The string representing the Sudoku board.
 * @return An `ArrayList<String>` containing each cell value extracted from the string,
 * preserving their original representation (e.g., "A1", "5", "-").
 */
@InternalSudoklifyApi
fun String.getCells(): ArrayList<String> {
  val regex = Regex("([A-I][a-z]+)|-|[A-I]")
  val matches = regex.findAll(this)
  val matchedTokens = ArrayList<String>()
  matches.forEach { matchedTokens.add(it.value) }
  return matchedTokens
}
