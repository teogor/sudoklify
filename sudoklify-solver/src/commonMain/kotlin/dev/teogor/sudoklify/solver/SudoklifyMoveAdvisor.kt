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

package dev.teogor.sudoklify.solver

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Dimension
import kotlin.random.Random

/**
 * Class for advising on Sudoku moves by analyzing the grid and suggesting possible next moves.
 *
 * @property dimension The dimension of the Sudoku grid, which provides size and box details.
 */
class SudoklifyMoveAdvisor(
  private val dimension: Dimension,
) {
  private val size = dimension.uniqueDigitsCount

  /**
   * Suggests the next possible move by identifying an empty cell with exactly one valid number
   * that can be placed in it. The suggestion is based on a randomized order of empty cells.
   *
   * @param grid The current grid represented as a 2D array of integers.
   * @return A [SudokuMove] indicating the row, column, and value of the suggested move, or `null` if no move is suggested.
   */
  fun suggestNextMove(grid: Array<IntArray>): SudokuMove? {
    // List of empty cells with their coordinates
    val emptyCells = mutableListOf<Pair<Int, Int>>()
    for (row in 0 until size) {
      for (col in 0 until size) {
        if (grid[row][col] == 0) {
          emptyCells.add(row to col)
        }
      }
    }

    // Randomize the order of empty cells
    emptyCells.shuffle(Random)

    // Find a cell with exactly one possible value
    for ((row, col) in emptyCells) {
      val possibleValues = findPossibleValues(grid, row, col)
      if (possibleValues.size == 1) {
        return SudokuMove(row, col, possibleValues.first())
      }
    }

    return null // No single possible value found for any cell
  }

  /**
   * Determines the possible values that can be placed in the cell at the specified row and column.
   *
   * @param grid The current grid represented as a 2D array of integers.
   * @param row The row index of the cell.
   * @param col The column index of the cell.
   * @return A list of possible values that can be placed in the cell.
   */
  private fun findPossibleValues(
    grid: Array<IntArray>,
    row: Int,
    col: Int,
  ): List<Int> {
    val possibleValues = mutableListOf<Int>()
    val usedValues = (1..size).toSet().toMutableSet()

    // Exclude values used in the same row
    usedValues -= grid[row].toSet()

    // Exclude values used in the same column
    usedValues -= grid.map { it[col] }.toSet()

    // Exclude values used in the same box
    val boxStartRow = row / dimension.boxHeight * dimension.boxHeight
    val boxStartCol = col / dimension.boxWidth * dimension.boxWidth
    usedValues -=
      (boxStartRow until boxStartRow + dimension.boxHeight).flatMap { r ->
        (boxStartCol until boxStartCol + dimension.boxWidth).map { c -> grid[r][c] }
      }.toSet()

    possibleValues.addAll(usedValues)
    return possibleValues
  }
}
