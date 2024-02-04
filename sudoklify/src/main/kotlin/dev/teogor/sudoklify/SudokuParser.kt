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

package dev.teogor.sudoklify

import dev.teogor.sudoklify.extensions.toBoard
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.types.SudokuString
import dev.teogor.sudoklify.utils.generateTokenMap

/**
 * A class responsible for parsing and manipulating Sudoku puzzle strings.
 *
 * @param puzzle The Sudoku puzzle string to parse.
 * @param gameType The game type of the Sudoku puzzle (e.g., 4x4, 9x9).
 */
class SudokuParser(
  puzzle: String,
  gameType: GameType,
) {
  private val puzzleBoard = puzzle.toBoard(gameType)
  private val boxDigits = gameType.cells

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
 * This method utilizes a `SudokuParser` object internally to handle the parsing and
 * conversion process.
 *
 * @param gameType The game type of the Sudoku puzzle (e.g., 4x4, 9x9).
 *
 * @return A 2D array of integers representing the parsed Sudoku puzzle.
 *
 * @throws IllegalArgumentException if the provided Sudoku puzzle string is invalid.
 */
fun SudokuString.toSudokuIntArray(gameType: GameType): Array<IntArray> {
  val parser = SudokuParser(this, gameType)
  return parser.toIntArray()
}
