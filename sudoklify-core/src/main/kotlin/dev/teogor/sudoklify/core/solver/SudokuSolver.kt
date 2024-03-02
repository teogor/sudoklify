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

package dev.teogor.sudoklify.core.solver

import dev.teogor.sudoklify.common.model.SudokuSolution
import dev.teogor.sudoklify.common.types.SudokuType

class SudokuSolver(
  private val grid: Array<IntArray>,
  private val sudokuType: SudokuType,
) {
  private val steps: ArrayList<Pair<Int, Int>> = ArrayList()
  private var stepCount = 0

  fun solve(): SudokuSolution {
    val startTime = System.nanoTime()

    // Create a copy of the grid to avoid modifying the original input
    val workingGrid = copyGrid(grid)

    // Recursively solve the Sudoku puzzle using backtracking
    val solved = solveSudoku(workingGrid)

    val endTime = System.nanoTime()
    val elapsedTime = endTime - startTime

    // Update the original grid with the solved solution if found
    if (solved) {
      copyGrid(workingGrid, grid)
    }

    return SudokuSolution(elapsedTime, solved, stepCount, workingGrid)
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
          for (num in 1..sudokuType.digits) {
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
    for (i in 0 until sudokuType.cells) {
      if (grid[row][i] == num || grid[i][col] == num) return false
    }

    val rowStart = (row / sudokuType.height) * sudokuType.height
    val colStart = (col / sudokuType.width) * sudokuType.width
    for (i in rowStart until rowStart + sudokuType.height) {
      for (j in colStart until colStart + sudokuType.width) {
        if (grid[i][j] == num) return false
      }
    }

    return true
  }
}
