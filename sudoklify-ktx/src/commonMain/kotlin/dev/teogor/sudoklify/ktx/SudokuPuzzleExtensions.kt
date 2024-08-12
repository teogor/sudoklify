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

package dev.teogor.sudoklify.ktx

import dev.teogor.sudoklify.common.model.SudokuPuzzle

/**
 * Generates a grid based on the Sudoku puzzle with the given cells filled in.
 *
 * @return A list of lists representing the generated grid, where each inner list
 * represents a row and each element in the inner list represents a cell value.
 */
fun SudokuPuzzle.generateGridWithGivens(): List<List<Int>> {
  val gridSize = sudokuType.uniqueDigitsCount
  val grid = MutableList(gridSize) { MutableList(gridSize) { 0 } }

  // Map givens to the grid
  for (given in givens) {
    grid[given.row][given.col] = given.value
  }

  return grid.toList()
}
