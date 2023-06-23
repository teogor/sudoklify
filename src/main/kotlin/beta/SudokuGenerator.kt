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
        println("----------------------------")
        // todo convert to puzzle after we have them
        val puzzle = Puzzle(type)
        val solution = Puzzle(type)
        val triedCells = mutableSetOf<Cell>()

        fillDiagonalBoxes(puzzle, solution)
        solve(puzzle, solution, triedCells)

        val cluesToRemove = calculateCluesToRemove(difficulty, size)
        removeClues(puzzle, cluesToRemove)
        println("clues removed")

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

    private fun solve(puzzle: Puzzle, solution: Puzzle, triedCells: MutableSet<Cell>): Boolean {
        val emptyCell = findEmptyCell(puzzle) ?: return true

        val row = emptyCell.row
        val col = emptyCell.col

        val shuffledValues = (1..size).toList().shuffled(random)
        for (value in shuffledValues) {
            if (isSafe(puzzle, row, col, value)) {
                puzzle.setValue(row, col, value)
                solution.setValue(row, col, value)

                val currentCell = Cell(row, col)
                triedCells.add(currentCell)

                if (solve(puzzle, solution, triedCells)) {
                    return true
                }

                puzzle.setValue(row, col, 0)
                solution.setValue(row, col, 0)
                triedCells.remove(currentCell)
            }
        }

        return false
    }


    private fun solve(puzzle: Puzzle, solution: Puzzle): Boolean {
        val emptyCell = findEmptyCell(puzzle) ?: return true

        val row = emptyCell.row
        val col = emptyCell.col

        val shuffledValues = (1..size).toList().shuffled(random)
        for (value in shuffledValues) {
            println("Trying value $value at row $row, col $col")

            if (isSafe(puzzle, row, col, value)) {
                puzzle.setValue(row, col, value)
                solution.setValue(row, col, value)
                println("Value $value is safe. Continuing with the next empty cell.")
                if (solve(puzzle, solution)) {
                    return true
                }
                puzzle.setValue(row, col, 0)
                solution.setValue(row, col, 0)
                println("Value $value didn't lead to a solution. Backtracking.")
            }
        }

        println("No value worked at row $row, col $col. Backtracking to the previous cell.")
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
        val maxAttempts = 1000 // Maximum number of attempts to remove clues

        var attempts = 0 // Counter for the number of attempts made

        while (cluesRemoved < cluesToRemove && attempts < maxAttempts) {
            val row = random.nextInt(size)
            val col = random.nextInt(size)

            println("cluesRemoved loop")
            if (puzzle.getValue(row, col) != 0) {
                val temp = puzzle.getValue(row, col)
                puzzle.setValue(row, col, 0)

                if (!isPuzzleUnique(puzzle)) {
                    puzzle.setValue(row, col, temp)
                    println("Not unique at row: $row, col: $col. Restoring value: $temp")
                } else {
                    cluesRemoved++
                    println("Clue removed at row: $row, col: $col")
                }

                attempts++
            }
        }

        if (cluesRemoved < cluesToRemove) {
            println("Unable to remove the required number of clues. Stuck at $cluesRemoved clues.")
        }
    }

    private fun isPuzzleUnique(puzzle: Puzzle): Boolean {
        val solution = Puzzle(type)
        val puzzleCopy = Puzzle(type)
        val triedCells = mutableSetOf<Cell>()

        println("isPuzzleUnique forLoop --s")
        for (row in 0 until size) {
            for (col in 0 until size) {
                puzzleCopy.setValue(row, col, puzzle.getValue(row, col))
            }
        }
        println("isPuzzleUnique forLoop --e")

        val isSolved = solve(puzzle, solution, triedCells)

        if (isSolved) {
            println("Sudoku puzzle solved!")
            // Handle the solved puzzle here
            // You can access the solved puzzle using the 'puzzle' or 'solution' variables
        } else {
            println("Unable to solve the Sudoku puzzle.")
            // Handle the unsolved puzzle here
        }

        return isSolved
    }

    private data class Cell(val row: Int, val col: Int)
}
