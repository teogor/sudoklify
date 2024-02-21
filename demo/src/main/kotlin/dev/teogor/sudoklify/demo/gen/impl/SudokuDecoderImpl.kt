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

package dev.teogor.sudoklify.demo.gen.impl

import dev.teogor.sudoklify.common.types.Board
import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.SudokuType
import dev.teogor.sudoklify.core.io.toSudokuIntArray
import dev.teogor.sudoklify.core.solver.SudokuSolver
import dev.teogor.sudoklify.core.util.toBoard
import dev.teogor.sudoklify.demo.gen.SudokuDecoder
import dev.teogor.sudoklify.ktx.countRemainingCells
import dev.teogor.sudoklify.seeds.nineDigitsSeeds
import dev.teogor.sudoklify.seeds.sixteenDigitsSeeds
import java.io.BufferedWriter
import java.io.File

fun main() {
  val sudokuPuzzleFile = "demo/src/main/resources/raw/sudoku-puzzle.txt"
  val sudokuSolvedFile = "demo/src/main/resources/raw/sudoku-solved.txt"
  val outputFile = "demo/src/main/resources/raw/sudoku-converted.txt"
  val sudokuPuzzle = getParsedPuzzle(sudokuPuzzleFile)
  val sudokuSolved = getParsedPuzzle(sudokuSolvedFile)
  val difficulty = Difficulty.VERY_HARD
  val sudokuType = SudokuType.Sudoku25x25
  val validPuzzles =
    comparePuzzles(
      puzzle = sudokuPuzzle.toBoard(sudokuType),
      solution = sudokuSolved.toBoard(sudokuType),
    )
  if (validPuzzles) {
    File(outputFile) write {
      buildString {
        appendLine("// Remaining Digits ${sudokuPuzzle.countRemainingCells()}")
        appendLine("SudokuBlueprint(")
        appendLine("  puzzle = \"$sudokuPuzzle\",")
        appendLine("  solution = \"$sudokuSolved\",")
        appendLine("  difficulty = Difficulty.${difficulty.name},")
        appendLine("  gameType = GameType.${sudokuType.name},")
        appendLine("),")
      }.also { write(it) }
    }
  } else {
    println("invalid puzzles")
  }
  val rootDigitsSeeds = nineDigitsSeeds
  val originalSize = rootDigitsSeeds.size
  val uniquesSize = rootDigitsSeeds.distinctBy { it.solution }.size

  println("Original array contains $originalSize Sudoku puzzles for $sudokuType.")
  if (originalSize != uniquesSize) {
    println("Duplicates detected! Unique seeds: $uniquesSize")
  }
  val difficultyCounts =
    rootDigitsSeeds
      .groupBy { it.difficulty }
      .map { (difficulty, blueprints) ->
        "$difficulty: ${blueprints.size}"
      }

  println()
  println("Difficulty distribution:")
  difficultyCounts.forEach { println(" -> $it") }
  println()

  val puzzle = sixteenDigitsSeeds.random()
  val sudokuSolver = SudokuSolver(sudokuPuzzle.toSudokuIntArray(sudokuType), sudokuType)
  val solution = sudokuSolver.solve()
  println("solution=$solution")

  puzzle.puzzle
    .toSudokuIntArray(SudokuType.Sudoku16x16)
    .also {
      it[0].forEach { cell -> print("${if (cell == 0) "/" else cell} ") }
    }
  println()
  puzzle.solution
    .toSudokuIntArray(SudokuType.Sudoku16x16)
    .also {
      it[0].forEach { cell -> print("$cell ") }
    }
  println()
}

private fun comparePuzzles(
  puzzle: Board,
  solution: Board,
): Boolean {
  if (puzzle.size != solution.size || puzzle[0].size != solution[0].size) {
    return false
  }

  for (row in puzzle.indices) {
    for (col in 0 until puzzle[row].size) {
      val puzzleValue = puzzle[row][col]
      val solvedValue = solution[row][col]

      if (puzzleValue != "-" && puzzleValue != solvedValue) {
        return false
      }
    }
  }

  return true
}

private fun getParsedPuzzle(path: String): String {
  val fileContent = File(path).readText()
  return SudokuDecoder().parsePuzzle(fileContent)
}

private infix fun File.write(block: BufferedWriter.() -> Unit) {
  bufferedWriter().use { it.block() }
}
