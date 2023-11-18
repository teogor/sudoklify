package dev.teogor.sudoklify.demo

import dev.teogor.sudoklify.model.Difficulty
import java.io.File
import kotlin.math.sqrt

class SudokuSolver(private val size: Int) {

  fun solve(sudokuString: String): Array<Array<Int>>? {
    val sudokuGrid = parseSudoku(sudokuString)
    return solveSudoku(sudokuGrid)
  }

  private fun parseSudoku(sudokuString: String): Array<Array<Int>> {
    val sudokuGrid = Array(size) { Array(size) { 0 } }
    val lines = sudokuString.trim().split("\n")
    for (i in 0 until size) {
      val line = lines[i].trim()
      for (j in 0 until size) {
        if (j < line.length) {
          val char = line[j]
          if (char.isDigit()) {
            sudokuGrid[i][j] = char.toString().toInt()
          }
        }
      }
    }
    return sudokuGrid
  }

  private fun solveSudoku(grid: Array<Array<Int>>): Array<Array<Int>>? {
    val emptyCell = findEmptyCell(grid) ?: return grid

    val (row, col) = emptyCell

    for (num in 1..size) {
      if (isValidMove(grid, row, col, num)) {
        grid[row][col] = num

        if (solveSudoku(grid) != null) {
          return grid
        }

        grid[row][col] = 0 // If this num doesn't lead to a solution, backtrack.
      }
    }
    return null // No solution found.
  }

  private fun findEmptyCell(grid: Array<Array<Int>>): Pair<Int, Int>? {
    for (row in 0 until size) {
      for (col in 0 until size) {
        if (grid[row][col] == 0) {
          return Pair(row, col)
        }
      }
    }
    return null
  }

  private fun isValidMove(grid: Array<Array<Int>>, row: Int, col: Int, num: Int): Boolean {
    // Check the row and column for the same number.
    for (i in grid.indices) {
      if (grid[row][i] == num || grid[i][col] == num) {
        return false
      }
    }

    // Check the 2x2 box for the same number.
    val boxSize = sqrt(size.toDouble()).toInt()
    val startRow = row - row % boxSize
    val startCol = col - col % boxSize
    for (i in startRow until startRow + boxSize) {
      for (j in startCol until startCol + boxSize) {
        if (grid[i][j] == num) {
          return false
        }
      }
    }

    return true
  }
}

class SudokuValidator(private val size: Int) {

  fun isValidSudokuFormat(input: String): Boolean {
    // Split the input string into rows
    val rows = input.trim().split("\n")

    // Check if there are exactly size rows
    if (rows.size != size) return false

    // Check each row
    for (row in rows) {
      // Check if each row contains exactly size characters
      if (row.length != size) return false

      // Check if each character is a digit from '1' to '9' or a period '.' or a zero '0'
      if (!row.all { it in '1'..'9' || it == '.' || it == '0' }) return false
    }

    // Check for valid rows, columns, and subgrids
    for (i in 0 until size) {
      val rowSet = mutableSetOf<Char>()
      val colSet = mutableSetOf<Char>()
      val subgridSet = mutableSetOf<Char>()

      for (j in 0 until size) {
        // Check rows
        if (rowSet.contains(rows[i][j])) return false
        if (rows[i][j] != '.' && rows[i][j] != '0') rowSet.add(rows[i][j])

        // Check columns
        if (colSet.contains(rows[j][i])) return false
        if (rows[j][i] != '.' && rows[j][i] != '0') colSet.add(rows[j][i])

        // Check subgrids
        val subgridSize = sqrt(size.toDouble()).toInt()
        val rowStart = (i / subgridSize) * subgridSize
        val colStart = (i % subgridSize) * subgridSize
        val num = rows[rowStart + j / subgridSize][colStart + j % subgridSize]
        if (subgridSet.contains(num)) return false
        if (num != '.' && num != '0') subgridSet.add(num)
      }
    }

    return true
  }
}

fun getSudokuString(): String {
  val filePath = "demo/src/main/resources/raw/sudoku-string.txt"
  val file = File(filePath)
  return try {
    val fileContent = file.readText()
    fileContent.trimIndent()
  } catch (e: Exception) {
    throw RuntimeException("file does not exist")
  }
}

fun main() {
  val size = 9
  val difficulty = Difficulty.VERY_HARD

  val sudokuString = getSudokuString()

  val solver = SudokuSolver(size)
  val validator = SudokuValidator(size)

  if (validator.isValidSudokuFormat(sudokuString)) {
    val solvedSudoku = solver.solve(sudokuString)
    if (solvedSudoku != null) {
      println("Sudoku can be solved.")
      println(
        """

  SudokuBlueprint(
    puzzle = "${formatSudokuString(sudokuString)}",
    solution = "${formatSudoku(solvedSudoku)}",
    difficulty = Difficulty.${difficulty.name},
    type = Type.TWO_BY_TWO,
  ),
      """.trimIndent(),
      )
    } else {
      println("No solution found for the Sudoku.")
    }
  } else {
    println("The Sudoku format is not valid.")
  }
}

private fun formatSudokuString(sudokuString: String): String {
  return sudokuString.replace("\n", "")
    .replace(".", "-")
    .map {
      if (it.isDigit()) {
        ('A' + (it - '0' - 1))
      } else {
        it
      }
    }.joinToString("")
}


private fun formatSudoku(sudoku: Array<Array<Int>>): String {
  return sudoku.joinToString("") { row ->
    row.joinToString("") { cell ->
      when (cell) {
        0 -> "-"
        else -> ('A' + cell - 1).toString()
      }
    }
  }
}
