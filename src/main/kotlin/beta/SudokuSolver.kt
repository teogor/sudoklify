package beta

import kotlin.math.sqrt

class SudokuSolver(private val sudoku: Sudoku) {

    fun solve(): Boolean {
        val board = sudoku.puzzle
        val size = sudoku.size
        return solveSudoku(board.puzzle, size)
    }

    private fun solveSudoku(board: Array<IntArray>, size: Int): Boolean {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (board[row][col] == 0) {
                    for (num in 1..size) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num
                            if (solveSudoku(board, size)) {
                                return true
                            }
                            board[row][col] = 0 // Backtrack if the solution is not valid
                        }
                    }
                    return false // No valid number found, backtrack to the previous cell
                }
            }
        }
        return true // All cells filled, Sudoku solved
    }

    private fun isSafe(board: Array<IntArray>, row: Int, col: Int, num: Int): Boolean {
        // Check if num already exists in the current row
        for (i in board.indices) {
            if (board[row][i] == num) {
                return false
            }
        }

        // Check if num already exists in the current column
        for (i in board.indices) {
            if (board[i][col] == num) {
                return false
            }
        }

        // Check if num already exists in the current box
        val boxSize = sqrt(board.size.toDouble()).toInt()
        val boxRowStart = row - row % boxSize
        val boxColStart = col - col % boxSize
        for (i in boxRowStart until boxRowStart + boxSize) {
            for (j in boxColStart until boxColStart + boxSize) {
                if (board[i][j] == num) {
                    return false
                }
            }
        }

        return true // num is safe to place in the current cell
    }
}
