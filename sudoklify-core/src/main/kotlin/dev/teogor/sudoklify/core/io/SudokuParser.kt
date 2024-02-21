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

package dev.teogor.sudoklify.core.io

import dev.teogor.sudoklify.common.types.SudokuString
import dev.teogor.sudoklify.common.types.SudokuType
import dev.teogor.sudoklify.core.util.toBoard

/**
 * A class responsible for parsing and manipulating Sudoku puzzle strings.
 *
 * @param puzzle The Sudoku puzzle string to parse.
 * @param sudokuType The sudoku type of the Sudoku puzzle (e.g., 4x4, 9x9).
 */
class SudokuParser(
  puzzle: String,
  sudokuType: SudokuType,
) {
  private val puzzleBoard = puzzle.toBoard(sudokuType)
  private val boxDigits = sudokuType.cells

  /**
   * Converts the Sudoku puzzle string into a numerical representation.
   *
   * @return A 2D array of integers representing the Sudoku puzzle.
   */
  fun toIntArray(): Array<IntArray> {
    val tokenMap = generateTokenMap(boxDigits)
    val convertedPuzzle = Array(puzzleBoard.size) { IntArray(puzzleBoard[0].size) }

    for (row in puzzleBoard.indices) {
      for (col in puzzleBoard[row].indices) {
        convertedPuzzle[row][col] =
          if (puzzleBoard[row][col] != "-") {
            val value = puzzleBoard[row][col]
            tokenMap[value]?.toInt() ?: throw IllegalArgumentException(
              "Invalid token '$value' found in Sudoku puzzle.",
            )
          } else {
            0
          }
      }
    }

    return convertedPuzzle
  }
}

/**
 * Converts the provided Sudoku puzzle string into a 2D integer array representation.
 * This method utilizes a [SudokuParser] object internally to handle the parsing and
 * conversion process.
 *
 * @param sudokuType The sudoku type of the Sudoku puzzle (e.g., 4x4, 9x9).
 *
 * @return A 2D array of integers representing the parsed Sudoku puzzle.
 *
 * @throws [IllegalArgumentException] if the provided Sudoku puzzle string is invalid.
 */
fun SudokuString.toSudokuIntArray(sudokuType: SudokuType): Array<IntArray> {
  val parser = SudokuParser(this, sudokuType)
  return parser.toIntArray()
}
