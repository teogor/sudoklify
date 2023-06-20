package beta

import Sudoku
import asPuzzleString
import boxSize
import size
import java.util.*
import java.util.*
import kotlin.math.sqrt

class SudokuGenerator(
    private val sudokuType: SudokuType,
    private val seed: Long,
    private val difficulty: Difficulty,
) {
    private val random: Random = Random(seed)
    private val boxSize = sudokuType.boxSize
    private val sudokuSize: Int = sudokuType.size

    enum class Difficulty(val cluesToRemove: Int) {
        EASY(30),
        NORMAL(40),
        DIFFICULT(50)
    }

    enum class SudokuType(val a: Int, val b: Int) {
        TWO_BY_TWO(2, 2),
        TWO_BY_THREE(2, 3),
        TWO_BY_FOUR(2, 4),
        THREE_BY_THREE(3, 3),
        TWO_BY_FIVE(2, 5),
        THREE_BY_FOUR(3, 4),
        THREE_BY_FIVE(3, 5),
        FOUR_BY_FOUR(4, 4),
        FIVE_BY_FIVE(5, 5),
        SIX_BY_SIX(6, 6),
        SEVEN_BY_SEVEN(7, 7),
        EIGHT_BY_EIGHT(8, 8),
        NINE_BY_NINE(9, 9)
    }

    private fun generatePuzzle(): Array<IntArray> {
        val puzzle = Array(boxSize) { IntArray(boxSize) }
        fillDiagonalBlocks(puzzle)
        fillRemaining(puzzle, 0, boxSize / sudokuSize)
        return puzzle
    }

    private fun solvePuzzle(puzzle: Array<IntArray>): Array<IntArray> {
        val solution = puzzle.map { it.copyOf() }.toTypedArray()
        val solutionCount = solve(solution, 0)
        return if (solutionCount == 1) solution else throw IllegalStateException("Invalid puzzle: $solutionCount solutions found")
    }

    private fun solve(puzzle: Array<IntArray>, solutionCount: Int): Int {
        val emptyCell = findEmptyCell(puzzle) ?: return solutionCount + 1

        val row = emptyCell.row
        val col = emptyCell.col

        val shuffledValues = (1..boxSize).toList().shuffled(random)
        for (value in shuffledValues) {
            if (isSafe(puzzle, row, col, value)) {
                puzzle[row][col] = value
                val newSolutionCount = solve(puzzle, solutionCount)
                if (newSolutionCount - solutionCount >= 2) {
                    return newSolutionCount
                }
                puzzle[row][col] = 0 // Undo the current assignment and try the next value
            }
        }

        return solutionCount
    }

    private fun findEmptyCell(puzzle: Array<IntArray>): Cell? {
        for (row in puzzle.indices) {
            for (col in puzzle[row].indices) {
                if (puzzle[row][col] == 0) {
                    return Cell(row, col)
                }
            }
        }
        return null // No empty cell found, puzzle is already solved
    }

    private fun fillDiagonalBlocks(puzzle: Array<IntArray>) {
        val step = boxSize / sudokuSize
        for (blockStart in 0 until boxSize step step) {
            fillBlock(puzzle, blockStart, blockStart)
        }
    }

    private fun fillBlock(puzzle: Array<IntArray>, row: Int, col: Int) {
        val values = (1..boxSize).toMutableList()
        values.shuffle(random)
        var valueIndex = 0
        for (i in row until row + boxSize / sudokuSize) {
            for (j in col until col + boxSize / sudokuSize) {
                puzzle[i][j] = values[valueIndex]
                valueIndex++
            }
        }
    }

    private fun fillRemaining(puzzle: Array<IntArray>, row: Int, col: Int): Boolean {
        var currentRow = row
        var currentCol = col

        if (currentCol >= boxSize && currentRow < boxSize - 1) {
            currentRow++
            currentCol = 0
        }

        if (currentRow >= boxSize && currentCol >= boxSize) {
            return true
        }

        if (currentRow < boxSize / sudokuSize) {
            if (currentCol < boxSize / sudokuSize) {
                currentCol = boxSize / sudokuSize
            }
        } else if (currentRow < boxSize - boxSize / sudokuSize) {
            if (currentCol == (currentRow / (boxSize / sudokuSize)) * (boxSize / sudokuSize)) {
                currentCol += boxSize / sudokuSize
            }
        } else {
            if (currentCol == boxSize - boxSize / sudokuSize) {
                currentRow++
                currentCol = 0
                if (currentRow >= boxSize) {
                    return true
                }
            }
        }

        val shuffledValues = (1..boxSize).toList().shuffled(random)
        for (value in shuffledValues) {
            if (isSafe(puzzle, currentRow, currentCol, value)) {
                puzzle[currentRow][currentCol] = value
                if (fillRemaining(puzzle, currentRow, currentCol + 1)) {
                    return true
                }
                puzzle[currentRow][currentCol] = 0
            }
        }

        return false
    }

    private fun isSafe(puzzle: Array<IntArray>, row: Int, col: Int, value: Int): Boolean {
        return !isUsedInRow(puzzle, row, value) &&
                !isUsedInColumn(puzzle, col, value) &&
                !isUsedInBox(puzzle, row - row % (boxSize / sudokuSize), col - col % (boxSize / sudokuSize), value)
    }

    private fun isUsedInRow(puzzle: Array<IntArray>, row: Int, value: Int): Boolean {
        return value in puzzle[row]
    }

    private fun isUsedInColumn(puzzle: Array<IntArray>, col: Int, value: Int): Boolean {
        for (row in 0 until boxSize) {
            if (puzzle[row][col] == value) {
                return true
            }
        }
        return false
    }

    private fun isUsedInBox(puzzle: Array<IntArray>, boxStartRow: Int, boxStartCol: Int, value: Int): Boolean {
        for (row in 0 until boxSize / sudokuSize) {
            for (col in 0 until boxSize / sudokuSize) {
                if (puzzle[row + boxStartRow][col + boxStartCol] == value) {
                    return true
                }
            }
        }
        return false
    }

    // private fun removeClues(puzzle: Array<IntArray>, cluesToRemove: Int) {
    //     val cells = mutableListOf<Pair<Int, Int>>()
    //     for (row in 0 until boxSize) {
    //         for (col in 0 until boxSize) {
    //             cells.add(row to col)
    //         }
    //     }
    //     cells.shuffle(random)
    //
    //
    //     var removedClues = 0
    //     var attempts = 0
    //     val maxAttempts = 100 // Set a maximum number of attempts
    //
    //     while (removedClues < cluesToRemove && attempts < maxAttempts) {
    //         val (row, col) = cells[attempts]
    //         val temp = puzzle[row][col]
    //         puzzle[row][col] = 0
    //
    //         // Check if the puzzle has a unique solution after removing the clue
    //         val tempSolution = solvePuzzle(puzzle)
    //         if (tempSolution.puzzle == tempSolution.solvedPuzzle) {
    //             removedClues++
    //         } else {
    //             puzzle[row][col] = temp // Undo the removal if it leads to multiple solutions
    //         }
    //
    //         attempts++
    //     }
    //
    //     if (attempts >= maxAttempts) {
    //         throw IllegalStateException("Unable to remove clues. Consider adjusting the difficulty level.")
    //     }
    //     // for (i in 0 until cluesToRemove) {
    //     //     val (row, col) = cells[i]
    //     //     puzzle[row][col] = 0
    //     // }
    // }

    private fun removeClues(puzzle: Array<IntArray>, cluesToRemove: Int) {
        val cells = mutableListOf<Pair<Int, Int>>()
        for (row in 0 until boxSize) {
            for (col in 0 until boxSize) {
                cells.add(row to col)
            }
        }
        cells.shuffle(random)

        var removedClues = 0
        var attempts = 0
        val maxAttempts = 100 // Set a maximum number of attempts

        while (removedClues < cluesToRemove && attempts < maxAttempts) {
            val (row, col) = cells[attempts]
            val temp = puzzle[row][col]
            puzzle[row][col] = 0

            // Check if the puzzle has a unique solution after removing the clue
            val solvedPuzzle = solvePuzzle(puzzle)
            val isUniqueSolution = isPuzzleUnique(puzzle, solvedPuzzle)

            if (isUniqueSolution) {
                removedClues++
            } else {
                puzzle[row][col] = temp // Undo the removal if it leads to multiple solutions
            }

            attempts++
        }

        if (attempts >= maxAttempts) {
            throw IllegalStateException("Unable to remove clues. Consider adjusting the difficulty level.")
        }
    }

    private fun isPuzzleUnique(puzzle: Array<IntArray>, solvedPuzzle: Array<IntArray>): Boolean {
        val solutionCount = solve(puzzle, 0)
        println(solutionCount)
        return solutionCount == 1 && deepEquals(puzzle, solvedPuzzle)
    }

    private fun deepEquals(puzzle1: Array<IntArray>, puzzle2: Array<IntArray>): Boolean {
        for (i in puzzle1.indices) {
            if (!puzzle1[i].contentEquals(puzzle2[i])) {
                return false
            }
        }
        return true
    }

    fun generateWithDifficulty(): Sudoku {
        val puzzle = generatePuzzle()
        val solvedPuzzle = solvePuzzle(puzzle)
        removeClues(puzzle, difficulty.cluesToRemove)
        return Sudoku(
            puzzle = puzzle.asPuzzleString(),
            solvedPuzzle = solvedPuzzle.asPuzzleString(),
            seed = seed,
            difficulty = difficulty,
            sudokuType = sudokuType
        )
    }
}
