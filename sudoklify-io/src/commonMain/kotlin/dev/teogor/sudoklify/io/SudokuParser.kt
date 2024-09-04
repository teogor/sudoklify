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

package dev.teogor.sudoklify.io

import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.tokenizer.JEncodedCell
import dev.teogor.sudoklify.util.toBoard

/**
 * A class responsible for parsing and manipulating Sudoku puzzle strings.
 *
 * @param puzzle The Sudoku puzzle string to parse.
 * @param dimension The sudoku type of the Sudoku puzzle (e.g., 4x4, 9x9).
 */
public class SudokuParser(
  puzzle: String,
  dimension: Dimension,
) {
  private val puzzleBoard = puzzle.toBoard(dimension)
  private val boxDigits = dimension.uniqueDigitsCount

  /**
   * Converts the Sudoku puzzle string into a numerical representation.
   *
   * @return A 2D array of integers representing the Sudoku puzzle.
   */
  public fun toIntArray(): Array<IntArray> {
    val tokenMap = generateTokenMap(boxDigits)
    val convertedPuzzle = Array(puzzleBoard.size) { IntArray(puzzleBoard[0].size) }

    for (row in puzzleBoard.indices) {
      for (col in puzzleBoard[row].indices) {
        convertedPuzzle[row][col] =
          if (puzzleBoard[row][col] != "-") {
            val value = puzzleBoard[row][col]
            tokenMap[JEncodedCell(value)]?.toInt() ?: throw IllegalArgumentException(
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
 * @param dimension The sudoku type of the Sudoku puzzle (e.g., 4x4, 9x9).
 *
 * @return A 2D array of integers representing the parsed Sudoku puzzle.
 *
 * @throws [IllegalArgumentException] if the provided Sudoku puzzle string is invalid.
 */
public fun String.toSudokuIntArray(dimension: Dimension): Array<IntArray> {
  val parser = SudokuParser(this, dimension)
  return parser.toIntArray()
}
