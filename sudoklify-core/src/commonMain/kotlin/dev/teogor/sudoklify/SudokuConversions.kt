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

import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.tokenizer.JEncodedCell
import dev.teogor.sudoklify.tokenizer.toJEncodedCell

/**
 * Converts an [Iterable] of elements to a [String] representation using [toJEncodedCell] for
 * mapping.
 *
 * **Example:**
 *
 * ```kt
 * val sudokuBoard: Iterable<Iterable<Int>> = listOf(
 *   listOf(1, 2, 3),
 *   listOf(4, 5, 6),
 *   listOf(7, 8, 9)
 * )
 *
 * // Convert the sudokuBoard to a string using mapToSudokuString
 * val sudokuString: String = sudokuBoard.mapToSudokuString()
 * ```
 *
 * @receiver The [Iterable] of elements to be converted.
 * @return A string representation of the elements, with each element converted using
 * [toJEncodedCell].
 *
 * @see mapToSudokuString
 * @see toJEncodedCell
 */
fun Iterable<Iterable<Int>>.mapToSudokuString(): String {
  return flatMap { innerIterable ->
    innerIterable.map { element ->
      element.toJEncodedCell()
    }
  }.joinToString("")
}

/**
 * Converts an [Iterable] of elements to a [String] representation using [toJEncodedCell] for
 * mapping.
 *
 * **Example:**
 *
 * ```kotlin
 * data class Cell(val number: Int)
 *
 * val board: Iterable<Iterable<Cell>> = listOf(
 *   listOf(Cell(1), Cell(2), Cell(3)),
 *   listOf(Cell(4), Cell(5), Cell(6)),
 *   listOf(Cell(7), Cell(8), Cell(9))
 * )
 *
 * // Convert the board to a string using mapToSudokuString with a custom valueMapper
 * val sudokuString: String = board.mapToSudokuString { number }
 * ```
 *
 * @receiver The [Iterable] of elements to be converted.
 * @param valueMapper A function that maps each element of the list to an integer to be used
 * in the string representation.
 * @return A string representation of the elements, with each element converted using
 * [toJEncodedCell].
 *
 * @see mapToSudokuString
 * @see toJEncodedCell
 */
inline fun <T> Iterable<Iterable<T>>.mapToSudokuString(
  crossinline valueMapper: T.() -> Int,
): String {
  return flatMap { cells ->
    cells.map { cell ->
      valueMapper(cell).toJEncodedCell()
    }
  }.joinToString("")
}

/**
 * Converts a string representation of a Sudoku board to a two-dimensional list of integers.
 *
 * This function converts a string representation of a Sudoku board to a two-dimensional list of integers.
 * It uses the provided [dimension] to determine the size and structure of the board, and assumes that
 * the string contains encoded cell values that can be decoded to integers.
 *
 * **Example:**
 *
 * ```kotlin
 * val sudokuString: String = "ABCDDCBAABCDBADC"
 *
 * // Convert the string to a 4x4 Sudoku board
 * val sudokuBoard: List<List<Int>> = sudokuString.mapToSudokuBoard(SudokuType.Sudoku4x4)
 *
 * // The resulting sudokuBoard will be a 4x4 list of integers
 * // For Sudoku4x4, you would get:
 * // [
 * //   [1, 2, 3, 4],
 * //   [4, 3, 2, 1],
 * //   [1, 2, 3, 4],
 * //   [2, 1, 4, 3],
 * // ]
 * ```
 *
 * @receiver The string representing the Sudoku board.
 * @param dimension The type of Sudoku board (e.g., 4x4, 9x9), used to determine the size
 * and structure of the board.
 * @return A two-dimensional list of integers representing the Sudoku board, where each cell
 * is converted to an integer using its default decoding.
 *
 * @see mapToSudokuBoard
 * @see mapIndexedToSudokuBoard
 */
fun String.mapToSudokuBoard(dimension: Dimension): List<List<Int>> {
  return JEncodedCell.extractCells(this)
    .chunked(dimension.uniqueDigitsCount)
    .map { row -> row.map { it.toInt() } }
}

/**
 * Converts a string representation of a Sudoku board to a two-dimensional list of elements of type [T].
 *
 * This function converts a string representing a Sudoku board to a two-dimensional list where each integer
 * cell value is mapped to an element of type [T] using the provided [valueMapper] function. It uses the
 * [dimension] to determine the size and structure of the board and assumes that the string contains encoded
 * cell values that can be decoded to integers.
 *
 * **Example:**
 *
 * ```kotlin
 * val sudokuString: String = "ABCDDCBAABCDBADC"
 *
 * // Example with a custom valueMapper that maps integers to their string representation
 * val sudokuBoardString: List<List<String>> = sudokuString.mapToSudokuBoard(SudokuType.Sudoku4x4) { toString() }
 *
 * // The resulting sudokuBoardString will be a 4x4 list of strings
 * // For Sudoku4x4, you would get:
 * // [
 * //   ["1", "2", "3", "4"],
 * //   ["4", "3", "2", "1"],
 * //   ["1", "2", "3", "4"],
 * //   ["2", "1", "4", "3"],
 * // ]
 * ```
 *
 * @receiver The string representing the Sudoku board.
 * @param dimension The type of Sudoku board (e.g., 4x4, 9x9), used to determine the size
 * and structure of the board.
 * @param valueMapper A function that maps each integer value to an element of type [T].
 * @return A two-dimensional list of elements of type [T] representing the Sudoku board, where each cell
 * is converted using the provided [valueMapper] function.
 *
 * @see mapToSudokuBoard
 * @see mapIndexedToSudokuBoard
 */
inline fun <T> String.mapToSudokuBoard(
  dimension: Dimension,
  crossinline valueMapper: Int.() -> T,
): List<List<T>> {
  return JEncodedCell.extractCells(this)
    .chunked(dimension.uniqueDigitsCount)
    .map { row -> row.map { valueMapper(it.toInt()) } }
}

/**
 * Converts a string representation of a Sudoku board to a two-dimensional list of custom
 * elements, allowing access to row and column indices during mapping.
 *
 * @receiver The string representing the Sudoku board.
 * @param dimension The type of Sudoku board (e.g., 4x4, 9x9), used to determine the size
 * and structure of the board.
 * @param valueMapper A function that maps each integer value in the string representation
 * to a custom element, additionally providing the row and column indices of the cell.
 * @return A two-dimensional list of custom elements representing the Sudoku board, where
 * each cell is decoded and mapped using the provided [valueMapper], incorporating its row and
 * column indices.
 *
 * @see mapToSudokuBoard
 */
inline fun <T> String.mapIndexedToSudokuBoard(
  dimension: Dimension,
  crossinline valueMapper: (value: Int, row: Int, column: Int) -> T,
): List<List<T>> {
  return JEncodedCell.extractCells(this)
    .chunked(dimension.uniqueDigitsCount)
    .mapIndexed { row, rowElements ->
      rowElements.mapIndexed { column, value ->
        valueMapper(value.toInt(), row, column)
      }
    }
}
