package beta

import kotlin.random.Random

class SudokuGenerator(
    private val numRows: Int,
    private val numCols: Int,
    private val seed: Long = System.currentTimeMillis()
) {
    private val size: Int = numRows * numCols
    private val boxSize: Int = numCols * numRows
    private val random: Random = Random(seed)

    enum class Difficulty {
        EASY, MEDIUM, HARD
    }

    fun generate(difficulty: Difficulty): Sudoku {
        val puzzle = Array(size) { IntArray(size) }
        val solution = Array(size) { IntArray(size) }

        fillDiagonalBoxes(puzzle, solution)
        solve(puzzle, solution)

        val cluesToRemove = calculateCluesToRemove(difficulty, size)
        removeClues(puzzle, cluesToRemove)

        return Sudoku(puzzle, solution)
    }

    private fun fillDiagonalBoxes(puzzle: Array<IntArray>, solution: Array<IntArray>) {
        for (boxRow in 0 until numRows) {
            fillBox(puzzle, solution, boxRow * numCols, boxRow * numCols)
        }
    }

    private fun fillBox(puzzle: Array<IntArray>, solution: Array<IntArray>, startRow: Int, startCol: Int) {
        val values = (1..size).toList().shuffled(random)
        var valueIndex = 0

        for (row in startRow until startRow + numRows) {
            for (col in startCol until startCol + numCols) {
                puzzle[row][col] = values[valueIndex]
                solution[row][col] = values[valueIndex]
                valueIndex = (valueIndex + 1) % size
            }
        }
    }

    private fun solve(puzzle: Array<IntArray>, solution: Array<IntArray>): Boolean {
        val emptyCell = findEmptyCell(puzzle) ?: return true

        val row = emptyCell.row
        val col = emptyCell.col

        val shuffledValues = (1..size).toList().shuffled(random)
        for (value in shuffledValues) {
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
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                if (puzzle[row + startRow][col + startCol] == value) {
                    return false
                }
            }
        }
        return true
    }

    private fun calculateCluesToRemove(difficulty: Difficulty, size: Int): Int {
        return when (difficulty) {
            Difficulty.EASY -> size * size / 3
            Difficulty.MEDIUM -> size * size / 2
            Difficulty.HARD -> size * size / 1.5.toInt()
        }
    }

    private fun removeClues(puzzle: Array<IntArray>, cluesToRemove: Int) {
        var cluesRemoved = 0

        while (cluesRemoved < cluesToRemove) {
            val row = random.nextInt(size)
            val col = random.nextInt(size)

            if (puzzle[row][col] != 0) {
                val temp = puzzle[row][col]
                puzzle[row][col] = 0

                if (!isPuzzleUnique(puzzle)) {
                    puzzle[row][col] = temp
                } else {
                    cluesRemoved++
                }
            }
        }
    }

    private fun isPuzzleUnique(puzzle: Array<IntArray>): Boolean {
        val solution = Array(size) { IntArray(size) }
        val puzzleCopy = Array(size) { IntArray(size) }

        for (i in puzzle.indices) {
            puzzleCopy[i] = puzzle[i].clone()
        }

        return solve(puzzleCopy, solution)
    }

    private data class Cell(val row: Int, val col: Int)
}

data class Sudoku(
    val puzzle: Array<IntArray>,
    val solution: Array<IntArray>
) {
    fun getFormattedPuzzle(): String {
        val sb = StringBuilder()

        for (row in puzzle) {
            for (cell in row) {
                val formattedCell = if (cell == 0) {
                    "_"
                } else {
                    val charList = mutableListOf<Char>()
                    var value = cell
                    while (value > 0) {
                        val digit = (value % 10)
                        val char = if (digit == 0) {
                            ('a' + 9).toChar()
                        } else {
                            ('a'.toInt() + digit - 1).toChar()
                        }
                        charList.add(0, char)
                        value /= 10
                    }

                    charList[0] = charList[0].uppercaseChar()
                    charList.joinToString("")
                }
                sb.append(formattedCell).append("")
            }
            sb.append("\\")
        }

        return sb.toString()
    }
}

fun main() {
    val numRows = 3
    val numCols = 4
    val seed = 12345L
    val difficulty = SudokuGenerator.Difficulty.MEDIUM

    val generator = SudokuGenerator(numRows, numCols, seed)
    val sudoku = generator.generate(difficulty)

    println("Generated Puzzle:")
    printSudoku(sudoku.puzzle)

    println("Solution:")
    printSudoku(sudoku.solution)

    println("Sudoku String")
    println(sudoku.getFormattedPuzzle())
}

fun printSudoku(puzzle: Array<IntArray>) {
    for (row in puzzle.indices) {
        for (col in puzzle[row].indices) {
            print("${puzzle[row][col]} ")
        }
        println()
    }
}
