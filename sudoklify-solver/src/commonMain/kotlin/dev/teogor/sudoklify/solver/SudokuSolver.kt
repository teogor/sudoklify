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

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Dimension
import kotlin.time.measureTimedValue

@ExperimentalSudoklifyApi
class SudokuSolver(
  private val grid: Array<IntArray>,
  private val dimension: Dimension,
) {
  private val steps: ArrayList<Pair<Int, Int>> = ArrayList()
  private var stepCount = 0

  fun solve(): SudokuSolution {
    val (value, timeTaken) =
      measureTimedValue {
        // Create a copy of the grid to avoid modifying the original input
        val workingGrid = copyGrid(grid)

        // Recursively solve the Sudoku puzzle using backtracking
        val solved = solveSudoku(workingGrid)

        // Update the original grid with the solved solution if found
        if (solved) {
          copyGrid(workingGrid, grid)
        }

        SudokuSolution(
          elapsedTime = 0,
          solutionFound = solved,
          stepCount = stepCount,
          solvedGrid = workingGrid,
        )
      }

    return value.copy(
      elapsedTime = timeTaken.inWholeMilliseconds,
    )
  }

  private fun copyGrid(
    sourceGrid: Array<IntArray>,
    destinationGrid: Array<IntArray>,
  ) {
    for (row in sourceGrid.indices) {
      for (col in sourceGrid[row].indices) {
        destinationGrid[row][col] = sourceGrid[row][col]
      }
    }
  }

  private fun copyGrid(sourceGrid: Array<IntArray>): Array<IntArray> {
    val newGrid = Array(sourceGrid.size) { IntArray(sourceGrid[0].size) }
    copyGrid(sourceGrid, newGrid)
    return newGrid
  }

  private fun solveSudoku(grid: Array<IntArray>): Boolean {
    stepCount++

    for (row in grid.indices) {
      for (col in grid[row].indices) {
        if (grid[row][col] == 0) {
          for (num in 1..dimension.uniqueDigitsCount) {
            if (isValid(grid, row, col, num)) {
              grid[row][col] = num
              steps.add(Pair(row, col))

              if (solveSudoku(grid)) return true

              grid[row][col] = 0
              steps.removeLast()
            }
          }

          return false // No solution found
        }
      }
    }

    return true // Puzzle solved
  }

  private fun isValid(
    grid: Array<IntArray>,
    row: Int,
    col: Int,
    num: Int,
  ): Boolean {
    for (i in 0 until dimension.totalCells) {
      if (grid[row][i] == num || grid[i][col] == num) return false
    }

    val rowStart = (row / dimension.height) * dimension.height
    val colStart = (col / dimension.width) * dimension.width
    for (i in rowStart until rowStart + dimension.height) {
      for (j in colStart until colStart + dimension.width) {
        if (grid[i][j] == num) return false
      }
    }

    return true
  }
}
