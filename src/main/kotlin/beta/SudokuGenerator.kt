package beta

import kotlin.random.Random

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD,
    EXTREME,
}

enum class SudokuType(val rows: Int, val cols: Int) {
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

class Puzzle(private val type: SudokuType) {
    private val size: Int = type.rows * type.cols
    private val puzzle: Array<IntArray> = Array(size) { IntArray(size) }

    fun setValue(row: Int, col: Int, value: Int) {
        puzzle[row][col] = value
    }

    fun getValue(row: Int, col: Int): Int {
        return puzzle[row][col]
    }

    fun getSize(): Int {
        return size
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (row in puzzle.indices) {
            for (col in puzzle[row].indices) {
                sb.append("${puzzle[row][col]} ")
            }
            sb.append("\n")
        }

        return sb.toString()
    }

    fun asString(): String {
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

        sb.deleteCharAt(sb.length - 1)
        return sb.toString()
    }

    companion object {
        fun asPuzzle(content: String): Array<IntArray> {
            val rows = content.trim().split("\\")
            val size = rows[0].length

            val puzzle = Array(rows.size) { IntArray(size) }

            for (i in rows.indices) {
                val row = rows[i].trim()

                for (j in row.indices) {
                    val cellValue = row[j]

                    val cell = if (cellValue == '_') {
                        0
                    } else {
                        val charIndex = cellValue.uppercaseChar().code - 'A'.code
                        val value = (if (charIndex > 9) charIndex - 9 else charIndex) + 1
                        value
                    }

                    puzzle[i][j] = cell
                }
            }

            return puzzle
        }
    }
}


data class Sudoku(
    val puzzle: Puzzle,
    val solution: Puzzle,
    val seed: Long,
    val difficulty: Difficulty,
    val sudokuType: SudokuType,
)

class SudokuGenerator(
    private val type: SudokuType,
    private val seed: Long,
) {
    private val numRows: Int = type.rows
    private val numCols: Int = type.cols
    private val size: Int = numRows * numCols
    private val random: Random = Random(seed)

    fun generate(difficulty: Difficulty): Sudoku {
        val puzzle = Puzzle(type)
        val solution = Puzzle(type)

        fillDiagonalBoxes(puzzle, solution)
        solve(puzzle, solution)

        val cluesToRemove = calculateCluesToRemove(difficulty, size)
        removeClues(puzzle, cluesToRemove)

        return Sudoku(
            puzzle,
            solution,
            seed,
            difficulty,
            type
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

fun main() {
    val type = SudokuType.TWO_BY_FOUR
    val seed = 0L
    val difficulty = Difficulty.MEDIUM

    val generator = SudokuGenerator(type, seed)
    val sudoku = generator.generate(difficulty)

    println("Generated Puzzle:")
    printSudoku(sudoku.puzzle)

    println("Solution:")
    printSudoku(sudoku.solution)
}

fun printSudoku(puzzle: Puzzle) {
    println(puzzle.toString())
}
