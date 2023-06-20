package beta

import kotlin.random.Random

class SudokuGenerator(
    private val type: SudokuType,
    private val seed: Long,
) {
    private val numRows: Int = type.rows
    private val numCols: Int = type.cols
    private val size: Int = numRows * numCols
    private val random: Random = Random(seed)

    fun generate(difficulty: Difficulty): Sudoku {
        // todo convert to puzzle after we have them
        val puzzle = Puzzle(type)
        val solution = Puzzle(type)

        fillDiagonalBoxes(puzzle, solution)
        solve(puzzle, solution)

        val cluesToRemove = calculateCluesToRemove(difficulty, size)
        removeClues(puzzle, cluesToRemove)

        return Sudoku(
            puzzle = puzzle,
            solution = solution,
            seed = seed,
            difficulty = difficulty,
            sudokuType = type,
        )
    }

    private fun fillDiagonalBoxes(puzzle: Puzzle, solution: Puzzle) {
        for (boxRow in 0 until numRows) {
            fillBox(puzzle, solution, boxRow * numCols, boxRow * numCols)
        }
    }

    private fun fillBox(puzzle: Puzzle, solution: Puzzle, startRow: Int, startCol: Int) {
        val values = (1..size).toList().shuffled(random)
        var valueIndex = 0

        for (row in startRow until startRow + numRows) {
            for (col in startCol until startCol + numCols) {
                puzzle.setValue(row, col, values[valueIndex])
                solution.setValue(row, col, values[valueIndex])
                valueIndex = (valueIndex + 1) % size
            }
        }
    }

    private fun solve(puzzle: Puzzle, solution: Puzzle): Boolean {
        val emptyCell = findEmptyCell(puzzle) ?: return true

        val row = emptyCell.row
        val col = emptyCell.col

        val shuffledValues = (1..size).toList().shuffled(random)
        for (value in shuffledValues) {
            if (isSafe(puzzle, row, col, value)) {
                puzzle.setValue(row, col, value)
                solution.setValue(row, col, value)
                if (solve(puzzle, solution)) {
                    return true
                }
                puzzle.setValue(row, col, 0)
                solution.setValue(row, col, 0)
            }
        }

        return false
    }

    private fun findEmptyCell(puzzle: Puzzle): Cell? {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (puzzle.getValue(row, col) == 0) {
                    return Cell(row, col)
                }
            }
        }
        return null
    }

    private fun isSafe(puzzle: Puzzle, row: Int, col: Int, value: Int): Boolean {
        return isValueUniqueInRow(puzzle, row, value) &&
                isValueUniqueInColumn(puzzle, col, value) &&
                isValueUniqueInBox(puzzle, row - row % numRows, col - col % numCols, value)
    }

    private fun isValueUniqueInRow(puzzle: Puzzle, row: Int, value: Int): Boolean {
        for (col in 0 until size) {
            if (puzzle.getValue(row, col) == value) {
                return false
            }
        }
        return true
    }

    private fun isValueUniqueInColumn(puzzle: Puzzle, col: Int, value: Int): Boolean {
        for (row in 0 until size) {
            if (puzzle.getValue(row, col) == value) {
                return false
            }
        }
        return true
    }

    private fun isValueUniqueInBox(puzzle: Puzzle, startRow: Int, startCol: Int, value: Int): Boolean {
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                if (puzzle.getValue(row + startRow, col + startCol) == value) {
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
            Difficulty.EXTREME -> size * size
        }
    }

    private fun removeClues(puzzle: Puzzle, cluesToRemove: Int) {
        var cluesRemoved = 0

        while (cluesRemoved < cluesToRemove) {
            val row = random.nextInt(size)
            val col = random.nextInt(size)

            if (puzzle.getValue(row, col) != 0) {
                val temp = puzzle.getValue(row, col)
                puzzle.setValue(row, col, 0)

                if (!isPuzzleUnique(puzzle)) {
                    puzzle.setValue(row, col, temp)
                } else {
                    cluesRemoved++
                }
            }
        }
    }

    private fun isPuzzleUnique(puzzle: Puzzle): Boolean {
        val solution = Puzzle(type)
        val puzzleCopy = Puzzle(type)

        for (row in 0 until size) {
            for (col in 0 until size) {
                puzzleCopy.setValue(row, col, puzzle.getValue(row, col))
            }
        }

        return solve(puzzleCopy, solution)
    }

    private data class Cell(val row: Int, val col: Int)
}
