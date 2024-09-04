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

package dev.teogor.sudoklify.solver

import dev.teogor.sudoklify.components.BoxCoordinates
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.getBoxCoordinates
import dev.teogor.sudoklify.components.isDigitValid

/**
 * Converts the grid to `SudokuCellState`, checks for mistakes, and converts it back to the original grid type.
 *
 * @param T The type of the user grid cells.
 * @param mistakesMethod The method used to check for mistakes.
 * @param dimension The dimension used for Sudoku rules.
 * @param getValue Lambda function to get the value of a cell from the original type.
 * @param isLocked Lambda function to determine if a cell is locked.
 * @param getSolution Lambda function to get the solution value of a cell from the original type.
 * @param isError Lambda function to determine if a cell has an error state.
 * @param updateCell Lambda function to update a cell in the original grid type.
 *
 * @return The updated grid with potential mistake annotations.
 */
public inline fun <T> List<List<T>>.processGridMistakes(
  mistakesMethod: MistakeCheckingMode,
  dimension: Dimension,
  crossinline getValue: (T) -> Int,
  crossinline isLocked: (T) -> Boolean,
  crossinline getSolution: (T) -> Int,
  crossinline isError: (T) -> Boolean,
  crossinline updateCell: (row: Int, col: Int, SudokuCellState, T) -> T,
): List<List<T>> {
  // Convert the original grid to SudokuCellState
  val sudokuCellStates = this.toSudokuCellStates(getValue, isLocked, getSolution, isError)

  // Check for mistakes in the SudokuCellState grid
  val checkedBoard = sudokuCellStates.checkMistakesAll(mistakesMethod, dimension)

  // Convert the checked SudokuCellState grid back to the original grid type
  return checkedBoard.toUserGrid(updateCell, this)
}

public fun List<List<SudokuCellState>>.checkMistakesAll(
  mistakesMethod: MistakeCheckingMode,
  dimension: Dimension,
): List<List<SudokuCellState>> {
  val newBoard = this.deepCopy()

  for (i in newBoard.indices) {
    for (j in newBoard[i].indices) {
      val cell = newBoard[i][j]
      if (cell.value != 0 && !cell.isLocked) {
        when (mistakesMethod) {
          MistakeCheckingMode.NoChecking -> {
            // Mistake checking is off
            cell.isError = false
          }

          MistakeCheckingMode.CheckViolations -> {
            // Check for rule violations
            cell.isError =
              dimension.doesCellValueViolateRules(
                row = i,
                col = j,
                value = cell.value,
                gameBoard = newBoard,
              )
          }

          MistakeCheckingMode.CheckAgainstSolution -> {
            // Check against the final solution
            val isError = cell.solution != cell.value
            cell.isError = isError
          }
        }
      }
    }
  }

  return newBoard
}

public fun Dimension.doesCellValueViolateRules(
  row: Int,
  col: Int,
  value: Int,
  gameBoard: List<List<SudokuCellState>>,
): Boolean {
  require(isDigitValid(value)) { "Invalid digit: $value" }

  // Check row and column for conflicts
  val hasConflictsInRow = hasConflictsInRow(row, col, value, gameBoard)
  val hasConflictsInColumn = hasConflictsInColumn(row, col, value, gameBoard)
  if (hasConflictsInRow || hasConflictsInColumn) {
    return true
  }

  // Check box for conflicts
  val boxCoordinates = getBoxCoordinates(row, col)
  val hasConflictsInBox = hasConflictsInBox(row, col, boxCoordinates, value, gameBoard)
  return hasConflictsInBox
}

private fun Dimension.hasConflictsInRow(
  row: Int,
  checkCol: Int,
  value: Int,
  gameBoard: List<List<SudokuCellState>>,
): Boolean {
  for (col in 0 until uniqueDigitsCount) {
    if (gameBoard[row][col].value == value && checkCol != col) {
      return true
    }
  }
  return false
}

private fun Dimension.hasConflictsInColumn(
  checkRow: Int,
  col: Int,
  value: Int,
  gameBoard: List<List<SudokuCellState>>,
): Boolean {
  for (row in 0 until uniqueDigitsCount) {
    if (gameBoard[row][col].value == value && row != checkRow) {
      return true
    }
  }
  return false
}

private fun Dimension.hasConflictsInBox(
  checkRow: Int,
  checkCol: Int,
  boxCoordinates: BoxCoordinates,
  value: Int,
  gameBoard: List<List<SudokuCellState>>,
): Boolean {
  for (row in boxCoordinates.topLeftRow..boxCoordinates.bottomRightRow) {
    for (col in boxCoordinates.topLeftCol..boxCoordinates.bottomRightCol) {
      if (gameBoard[row][col].value == value && (row != checkRow || col != checkCol)) {
        return true
      }
    }
  }
  return false
}

/**
 * Creates a deep copy of the grid to avoid modifying the original references.
 * Uses a lambda function to copy each cell.
 */
internal fun List<List<SudokuCellState>>.deepCopy(): List<List<SudokuCellState>> {
  return this.map { row ->
    row.map { cell ->
      cell.copy()
    }
  }
}
