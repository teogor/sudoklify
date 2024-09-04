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

package dev.teogor.sudoklify.multiplatform

import androidx.lifecycle.ViewModel
import dev.teogor.sudoklify.SudoklifyArchitect
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.Seed
import dev.teogor.sudoklify.presets.loadPresetSchemas
import dev.teogor.sudoklify.puzzle.SudokuPuzzle
import dev.teogor.sudoklify.puzzle.SudokuSpec
import dev.teogor.sudoklify.puzzle.generateGridWithGivens
import dev.teogor.sudoklify.solver.MistakeCheckingMode
import dev.teogor.sudoklify.solver.SudoklifySolverEngine
import dev.teogor.sudoklify.solver.createSudokuGridProcessor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel responsible for managing the state of the Sudoku game.
 */
class SudokuGameViewModel : ViewModel() {
  private val architect = SudoklifyArchitect(loadPresetSchemas())

  private val _gameState = MutableStateFlow<GameState>(GameState.NotStarted)
  val gameState: StateFlow<GameState> = _gameState.asStateFlow()

  private val _sudokuGameState = MutableStateFlow(
    SudokuGameState(
      grid = emptyList(),
      selectedCell = null,
      mistakes = emptyList(),
      isSolved = false,
    ),
  )
  val sudokuGameState: StateFlow<SudokuGameState> = _sudokuGameState.asStateFlow()

  lateinit var sudokuSpec: SudokuSpec
  lateinit var sudokuPuzzle: SudokuPuzzle
  lateinit var sudokuSolver: SudoklifySolverEngine<Cell>

  /**
   * Generates a new Sudoku puzzle based on the provided dimension, difficulty, and seed.
   *
   * @param dimension The dimensions of the Sudoku grid.
   * @param difficulty The difficulty level of the puzzle.
   * @param seed The seed used for puzzle generation.
   */
  fun generateSudoku(dimension: Dimension, difficulty: Difficulty, seed: Seed) {
    _gameState.update { GameState.Loading }
    sudokuSpec = SudokuSpec {
      seed(seed)
      type(dimension)
      difficulty(difficulty)
    }
    sudokuPuzzle = architect.constructSudoku(sudokuSpec)

    val initialGrid = sudokuPuzzle.transformGrid {
      Cell(
        value = value,
        isGiven = isGiven,
        isError = isError,
        solution = solution,
        row = row,
        col = col,
      )
    }
    _sudokuGameState.update {
      SudokuGameState(
        grid = initialGrid,
        selectedCell = null,
        mistakes = emptyList(),
        isSolved = false,
      )
    }

    sudokuSolver = SudoklifySolverEngine(
      gridProcessor = createSudokuGridProcessor(
        getValue = { it.value },
        getSolution = { it.solution },
        isLocked = { it.isGiven },
        isError = { it.isError },
        updateCell = { row, col, state, cell ->
          Cell(
            value = state.value,
            isGiven = state.isLocked,
            isError = state.isError,
            solution = state.solution,
            row = row,
            col = col,
          )
        },
      ),
      puzzle = sudokuPuzzle,
      mistakeCheckingMode = MistakeCheckingMode.CheckViolations,
    )

    _gameState.update { GameState.Active }
  }

  /**
   * Selects a cell at the specified row and column.
   *
   * @param row The row index of the selected cell.
   * @param col The column index of the selected cell.
   */
  fun selectCell(row: Int, col: Int) {
    val currentState = _sudokuGameState.value
    _sudokuGameState.update { currentState.copy(selectedCell = Cell.Position(row, col)) }
  }

  /**
   * Makes a move by updating the value of a cell at the specified row and column.
   *
   * @param row The row index of the cell.
   * @param col The column index of the cell.
   * @param value The new value to be set in the cell.
   */
  fun makeMove(row: Int, col: Int, value: Int) {
    val currentState = _sudokuGameState.value
    val newGrid = currentState.grid.mapIndexed { r, rowList ->
      rowList.mapIndexed { c, cell ->
        if (r == row && c == col) {
          cell.copy(value = value)
        } else {
          cell
        }
      }
    }.let {
      sudokuSolver.processGridMistakes(it)
    }

    newGrid
      .flatten()
      .filter { it.isError }
      .also {
        println("Mistakes: $it")
      }

    val isSolved = checkIfSolved(newGrid)

    _sudokuGameState.update {
      currentState.copy(
        grid = newGrid,
        isSolved = isSolved,
        selectedCell = null,
      )
    }

    if (isSolved) {
      _gameState.update { GameState.Completed(isWon = true) }
    }
  }

  private fun checkIfSolved(grid: List<List<Cell>>): Boolean {
    // Implement Sudoku solving logic here
    return true
  }

  /**
   * Resets the current Sudoku game, clearing the board and starting over.
   */
  fun resetGame() {
    _sudokuGameState.update {
      SudokuGameState(
        grid = sudokuPuzzle.generateGridWithGivens().mapIndexed { rowIndex, rowList ->
          rowList.mapIndexed { colIndex, value ->
            Cell(
              value = value,
              isGiven = false,
              isError = false,
              solution = value,
              row = rowIndex,
              col = colIndex,
            )
          }
        },
        selectedCell = null,
        mistakes = emptyList(),
        isSolved = false,
      )
    }
    _gameState.update { GameState.Active }
  }

  private inline fun <reified T> SudokuPuzzle.transformGrid(
    converter: Cell.() -> T,
  ): List<List<T>> {
    val gridSize = type.uniqueDigitsCount
    val grid = MutableList(gridSize) { MutableList(gridSize) { 0 } }

    // Map givens to the grid
    for (given in givens) {
      grid[given.row][given.col] = given.value
    }

    return grid.mapIndexed { rowIndex, row ->
      row.mapIndexed { colIndex, value ->
        Cell(
          row = rowIndex,
          col = colIndex,
          value = value,
          solution = solution[rowIndex][colIndex],
          isGiven = value != 0,
          isError = false,
        ).converter()
      }
    }
  }
}

/**
 * Represents the current state of the Sudoku game.
 *
 * @property grid The current state of the Sudoku grid as a list of lists of cells.
 * @property selectedCell The position of the currently selected cell, if any.
 * @property mistakes A list of positions where mistakes have been made.
 * @property isSolved Boolean flag indicating if the puzzle has been solved.
 */
data class SudokuGameState(
  val grid: List<List<Cell>>,
  val selectedCell: Cell.Position?,
  val mistakes: List<Cell.Position>,
  val isSolved: Boolean,
)
