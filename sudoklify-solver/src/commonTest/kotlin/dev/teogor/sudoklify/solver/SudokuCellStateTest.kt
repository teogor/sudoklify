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

import dev.teogor.sudoklify.SudoklifyArchitect
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.areCellsInSameBox
import dev.teogor.sudoklify.components.toSeed
import dev.teogor.sudoklify.presets.loadPresetSchemas
import dev.teogor.sudoklify.puzzle.SudokuPuzzle
import dev.teogor.sudoklify.puzzle.SudokuSpec
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SudokuCellStateTest {
  private val architect = SudoklifyArchitect(loadPresetSchemas())

  // Mock class for the purpose of testing
  data class Cell(
    val value: Int,
    val isLocked: Boolean,
    val solution: Int,
  )

  data class SudokuCell(
    val row: Int,
    val col: Int,
    val value: Int,
    val solution: Int,
    val isGiven: Boolean,
    val isError: Boolean = false,
  )

  /**
   * Transforms the Sudoku grid into a grid of type [T] using the provided [converter].
   *
   * TODO: Consider moving `transformGrid` to the core module.
   *  Rationale:
   *  - This function is widely applicable across different modules that deal with Sudoku grids.
   *  - It encapsulates common transformation logic that can be reused and tested independently.
   *  Prerequisites:
   *  - Ensure the `SudokuCell` and `SudokuPuzzle` classes are also moved or made accessible in the core module.
   *  - Review dependencies and ensure they are minimal and do not introduce circular dependencies.
   *  - Write additional unit tests to cover edge cases in different grid configurations.
   *  Impact:
   *  - Moving this function to the core module will centralize grid transformation logic, reducing code duplication.
   *  - It will improve maintainability and testability by decoupling transformation logic from specific modules.
   *
   * @param converter A lambda function that converts a [SudokuCell] into an instance of [T].
   * @return A grid of type [T] with the same dimensions as the original Sudoku grid.
   */
  inline fun <reified T> SudokuPuzzle.transformGrid(converter: SudokuCell.() -> T): List<List<T>> {
    val gridSize = type.uniqueDigitsCount
    val grid = MutableList(gridSize) { MutableList(gridSize) { 0 } }

    // Map givens to the grid
    for (given in givens) {
      grid[given.row][given.col] = given.value
    }

    return grid.mapIndexed { rowIndex, row ->
      row.mapIndexed { colIndex, value ->
        SudokuCell(
          row = rowIndex,
          col = colIndex,
          value = value,
          solution = solution[rowIndex][colIndex],
          isGiven = value != 0,
        ).converter()
      }
    }
  }

  @Test
  fun toSudokuCellStates_shouldConvertGridCorrectly() {
    // Arrange
    val sudokuSpec =
      SudokuSpec {
        seed = 2024L.toSeed()
        type = Dimension.NineByNine
        difficulty = Difficulty.EASY
      }
    val originalGrid =
      architect.constructSudoku(sudokuSpec)
        .transformGrid {
          Cell(
            value = value,
            isLocked = isGiven,
            solution = solution,
          )
        }
    val originalGridAsSudokuCellStates =
      architect.constructSudoku(sudokuSpec)
        .transformGrid {
          SudokuCellState(
            value = value,
            isLocked = isGiven,
            solution = solution,
          )
        }

    val getValue: (Cell) -> Int = { it.value }
    val isLocked: (Cell) -> Boolean = { it.isLocked }
    val getSolution: (Cell) -> Int = { it.solution }
    val isError: (Cell) -> Boolean = { false }

    // Act
    val sudokuCellStates = originalGrid.toSudokuCellStates(getValue, isLocked, getSolution, isError)

    // Assert
    assertEquals(
      originalGridAsSudokuCellStates,
      sudokuCellStates,
      buildString {
        append("The converted grid does not match the expected grid")
        append(" ")
        append("Ensure that the 'toSudokuCellStates' function maps cell properties correctly.")
      },
    )
  }

  @Test
  fun toUserGrid_shouldConvertBackToOriginalGrid() {
    // Arrange
    val sudokuSpec =
      SudokuSpec {
        seed = 2024L.toSeed()
        type = Dimension.NineByNine
        difficulty = Difficulty.EASY
      }
    val originalGrid =
      architect.constructSudoku(sudokuSpec)
        .transformGrid {
          Cell(
            value = value,
            isLocked = isGiven,
            solution = solution,
          )
        }
    val sudokuCellStates =
      architect.constructSudoku(sudokuSpec)
        .transformGrid {
          SudokuCellState(
            value = value,
            isLocked = isGiven,
            solution = solution,
          )
        }
    val updateCell: (Int, Int, SudokuCellState, Cell) -> Cell = { _, _, state, _ ->
      Cell(state.value, state.isLocked, state.solution)
    }

    // Act
    val resultGrid = sudokuCellStates.toUserGrid(updateCell, originalGrid)

    // Assert
    assertEquals(
      originalGrid,
      resultGrid,
      buildString {
        append("The grid converted back from 'SudokuCellState' does not match the original grid.")
        append(" ")
        append("Verify that 'toUserGrid' correctly restores original cell values and properties.")
      },
    )
  }

  @Test
  fun checkMistakesAll_shouldMarkErrorsBasedOnCheckingMode() {
    // Arrange
    val dimension = Dimension.NineByNine
    val sudokuSpec =
      SudokuSpec {
        seed = 2024L.toSeed()
        type = dimension
        difficulty = Difficulty.EASY
      }
    val sudokuCellStates =
      architect.constructSudoku(sudokuSpec)
        .transformGrid {
          SudokuCellState(
            value = value,
            isLocked = isGiven,
            solution = solution,
          )
        }
    // Define mistake checking mode to simulate errors
    val mistakesMethod = MistakeCheckingMode.CheckViolations // Example method

    // Act
    val resultBoard = sudokuCellStates.checkMistakesAll(mistakesMethod, dimension)

    // Assert
    assertTrue(
      resultBoard.all { row -> row.all { !it.isError } },
      buildString {
        append(
          "The checkMistakesAll function should not mark any cells with errors when " +
            "there are no violations.",
        )
        append(" ")
        append("Check the implementation of the mistake checking method and rules.")
      },
    )
  }

  @Test
  fun dimension_doesCellValueViolateRules_shouldDetectConflicts() {
    // Arrange
    val dimension = Dimension.NineByNine
    val sudokuSpec = SudokuSpec {
      seed = 2024L.toSeed()
      type = dimension
      difficulty = Difficulty.EASY
    }
    var gameBoard = architect.constructSudoku(sudokuSpec)
      .transformGrid {
        SudokuCellState(
          value = value,
          isLocked = isGiven,
          solution = solution,
        )
      }

    gameBoard = gameBoard.toMutableList().apply {
      forEachIndexed { rowIndex, cells ->
        cells.forEachIndexed { colIndex, cell ->
          val isCellInSameBox = dimension.areCellsInSameBox(0, 1, rowIndex, colIndex)
          if (isCellInSameBox && rowIndex != 0 && colIndex != 1) {
            this[0] = this[0].toMutableList().apply {
              this[1] = cell
            }
          }
        }
      }
    }

    // Act
    val hasConflicts = dimension.doesCellValueViolateRules(0, 1, gameBoard[0][1].value, gameBoard)

    // Assert
    assertTrue(
      hasConflicts,
      buildString {
        append(
          "The doesCellValueViolateRules function should detect a conflict in the row, column," +
            " or box when duplicate values are present.",
        )
        append(" ")
        append("Verify that the conflict detection logic is correct.")
      },
    )
  }
}
