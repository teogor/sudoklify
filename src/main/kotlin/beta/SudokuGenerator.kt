package beta

import kotlin.random.Random

class SudokuGenerator(private val numRows: Int, private val numCols: Int, private val seed: Long = System.currentTimeMillis()) {
    enum class Difficulty { EASY, MEDIUM, HARD }

    private val random: Random = Random(seed)

    fun generate(difficulty: Difficulty): Sudoku {
        val size = numRows * numCols
        val puzzle = Array(size) { IntArray(size) }
        val solution = Array(size) { IntArray(size) }

        fillDiagonalBoxes(puzzle)
        solve(puzzle, solution)

        val cluesToRemove = calculateCluesToRemove(difficulty, size)
        removeClues(puzzle, solution, cluesToRemove)

        return Sudoku(puzzle, solution)
    }

    private fun fillDiagonalBoxes(puzzle: Array<IntArray>) {
        val boxSize = numCols * numRows

        for (boxStart in 0 until boxSize step boxSize) {
            fillBox(puzzle, boxStart, boxStart)
        }
    }

    private fun fillBox(puzzle: Array<IntArray>, startRow: Int, startCol: Int) {
        val boxSize = numCols * numRows
        val values = (1..boxSize).toMutableList().shuffled(random)

        var valueIndex = 0
        for (row in startRow until startRow + numRows) {
            for (col in startCol until startCol + numCols) {
                puzzle[row][col] = values[valueIndex]
                valueIndex++
            }
        }
    }

    private fun solve(puzzle: Array<IntArray>, solution: Array<IntArray>): Boolean {
        val emptyCell = findEmptyCell(puzzle) ?: return true

        val row = emptyCell.row
        val col = emptyCell.col

        val values = (1..numCols * numRows).toMutableList().shuffled(random)
        for (value in values) {
            if (isSafe(puzzle, row, col, value)) {
                puzzle[row][col] = value
                solution[row][col] = value

                if (solve(puzzle, solution)) {
                    return true
                }

                puzzle[row][col] = 0
                solution[row][col] = 0
            }
        }

        return false
    }

    private fun findEmptyCell(puzzle: Array<IntArray>): Cell? {
        for (row in puzzle.indices) {
            for (col in puzzle[row].indices) {
                if (puzzle[row][col] == 0) {
                    return Cell(row, col)
                }
            }
        }
        return null
    }

    private fun isSafe(puzzle: Array<IntArray>, row: Int, col: Int, value: Int): Boolean {
        return isValueUniqueInRow(puzzle, row, value) &&
                isValueUniqueInColumn(puzzle, col, value) &&
                isValueUniqueInBox(puzzle, row - row % numRows, col - col % numCols, value)
    }

    private fun isValueUniqueInRow(puzzle: Array<IntArray>, row: Int, value: Int): Boolean {
        for (col in puzzle[row].indices) {
            if (puzzle[row][col] == value) {
                return false
            }
        }
        return true
    }

    private fun isValueUniqueInColumn(puzzle: Array<IntArray>, col: Int, value: Int): Boolean {
        for (row in puzzle.indices) {
            if (puzzle[row][col] == value) {
                return false
            }
        }
        return true
    }

    private fun isValueUniqueInBox(puzzle: Array<IntArray>, startRow: Int, startCol: Int, value: Int): Boolean {
        for (row in startRow until startRow + numRows) {
            for (col in startCol until startCol + numCols) {
                if (puzzle[row][col] == value) {
                    return false
                }
            }
        }
        return true
    }

    private fun calculateCluesToRemove(difficulty: Difficulty, size: Int): Int {
        return when (difficulty) {
            Difficulty.EASY -> (0.35 * size * size).toInt()
            Difficulty.MEDIUM -> (0.45 * size * size).toInt()
            Difficulty.HARD -> (0.55 * size * size).toInt()
        }
    }

    private fun removeClues(puzzle: Array<IntArray>, solution: Array<IntArray>, cluesToRemove: Int) {
        for (i in 0 until cluesToRemove) {
            var row: Int
            var col: Int
            do {
                row = random.nextInt(numRows * numCols)
                col = random.nextInt(numRows * numCols)
            } while (puzzle[row][col] == 0)

            val removedValue = puzzle[row][col]
            puzzle[row][col] = 0

            // Check if the puzzle still has a unique solution after removing the clue
            val clone = puzzle.copyOf()
            if (!isPuzzleUnique(clone, solution)) {
                puzzle[row][col] = removedValue
            }
        }
    }

    private fun isPuzzleUnique(puzzle: Array<IntArray>, solvedPuzzle: Array<IntArray>): Boolean {
        val solutionCount = solve(puzzle, solvedPuzzle)
        return solutionCount && deepEquals(puzzle, solvedPuzzle)
    }

    private fun deepEquals(arr1: Array<IntArray>, arr2: Array<IntArray>): Boolean {
        for (row in arr1.indices) {
            for (col in arr1[row].indices) {
                if (arr1[row][col] != arr2[row][col]) {
                    return false
                }
            }
        }
        return true
    }

    private data class Cell(val row: Int, val col: Int)
}

data class Sudoku(val puzzle: Array<IntArray>, val solution: Array<IntArray>)

fun main() {
    val numRows = 4
    val numCols = 5
    val seed = 1L
    val difficulty = SudokuGenerator.Difficulty.EASY

    val generator = SudokuGenerator(numRows, numCols, seed)
    val sudoku = generator.generate(difficulty)

    val puzzle = sudoku.puzzle
    val solution = sudoku.solution

    // Print the generated puzzle
    println("Generated Puzzle:")
    printGrid(puzzle)

    // Print the solution
    println("Solution:")
    printGrid(solution)
}

private fun printGrid(grid: Array<IntArray>) {
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            print("${grid[row][col]} ")
        }
        println()
    }
}
