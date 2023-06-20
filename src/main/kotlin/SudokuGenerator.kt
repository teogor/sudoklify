import beta.SudokuGenerator
import java.util.*
import kotlin.math.sqrt

/**
 * Represents a Sudoku puzzle, including the initial puzzle, solved puzzle, seed, and difficulty level.
 *
 * @property puzzle The initial puzzle as a string representation.
 * @property solvedPuzzle The solved puzzle as a string representation.
 * @property seed The seed used for puzzle generation.
 * @property difficulty The difficulty level of the puzzle.
 * @property sudokuType The type of the puzzle.
 */
data class Sudoku(
    val puzzle: String,
    val solvedPuzzle: String,
    val seed: Long,
    val difficulty: SudokuGenerator.Difficulty,
    val sudokuType: SudokuGenerator.SudokuType,
)

/**
 * Converts the Sudoku puzzle represented as a 2D array of integers to a string representation.
 * Empty cells are represented as underscores (_).
 *
 * @return The Sudoku puzzle as a string.
 */
fun Array<IntArray>.asPuzzleString(): String {
    val stringBuilder = StringBuilder()
    for (row in this) {
        for (value in row) {
            if (value == 0) {
                stringBuilder.append('_')
            } else {
                val cellValue = if (value < 10) value.toString() else ('A' + value - 10).toString()
                stringBuilder.append(cellValue)
            }
        }
    }
    return stringBuilder.toString()
}

/**
 * Converts a string representation of a Sudoku puzzle back to an Array<IntArray>.
 * Empty cells are represented as underscores (_).
 * Cell values with letters (a, b, c, ...) will be converted back to their respective decimal values.
 *
 * @return The Sudoku puzzle as an Array<IntArray>.
 * @throws IllegalArgumentException if the string representation is invalid or cannot be converted.
 */
fun String.fromPuzzleString(): Array<IntArray> {
    val sanitizedString = this.replace("_", "0")
    val size = sqrt(length.toDouble()).toInt()
    if (size * size != length) {
        throw IllegalArgumentException("Invalid puzzle string: Length does not match a valid Sudoku puzzle size.")
    }
    val puzzle = Array(size) { IntArray(size) }
    for (row in 0 until size) {
        for (col in 0 until size) {
            val char = sanitizedString[row * size + col]
            val value = if (char.isDigit()) char.toString().toInt() else char.uppercaseChar() - 'A' + 10
            puzzle[row][col] = value
        }
    }
    return puzzle
}

val SudokuGenerator.SudokuType.boxSize: Int
    get() = this.a * this.b

val SudokuGenerator.SudokuType.size: Int
    get() = sqrt(boxSize.toDouble()).toInt()

/**
 * The SudokuGenerator class is responsible for generating Sudoku puzzles of various sizes and difficulty levels.
 *
 * @property sudokuType The type of Sudoku puzzle to generate.
 * @property seed The seed used for puzzle generation.
 * @property difficulty The difficulty level of the puzzle.
 */
// class SudokuGenerator(
//     private val sudokuType: SudokuType,
//     private val seed: Long,
//     private val difficulty: Difficulty,
// ) {
//     private val random: Random = Random(seed)
//     private val boxSize = sudokuType.boxSize
//     private val sudokuSize: Int = sudokuType.size
//     private lateinit var sudoku: Sudoku
//
//     /**
//      * Represents the difficulty level of a Sudoku puzzle.
//      *
//      * @property cluesToRemove The number of clues to remove based on the difficulty level.
//      */
//     enum class Difficulty(val cluesToRemove: Int) {
//         EASY(30),
//         NORMAL(40),
//         DIFFICULT(50)
//     }
//
//     /**
//      * Represents the size and structure of a Sudoku puzzle.
//      *
//      * @property a The number of rows in each block.
//      * @property b The number of columns in each block.
//      */
//     enum class SudokuType(val a: Int, val b: Int) {
//         TWO_BY_TWO(2, 2),
//         TWO_BY_THREE(2, 3),
//         TWO_BY_FOUR(2, 4),
//         THREE_BY_THREE(3, 3),
//         TWO_BY_FIVE(2, 5),
//         THREE_BY_FOUR(3, 4),
//         THREE_BY_FIVE(3, 5),
//         FOUR_BY_FOUR(4, 4),
//         FIVE_BY_FIVE(5, 5),
//         SIX_BY_SIX(6, 6),
//         SEVEN_BY_SEVEN(7, 7),
//         EIGHT_BY_EIGHT(8, 8),
//         NINE_BY_NINE(9, 9)
//     }
//
//     fun get(): Sudoku {
//         val puzzle = generate()
//         val solvedPuzzle = retrieveSolution(puzzle)
//         sudoku = Sudoku(
//             puzzle = puzzle.asPuzzleString(),
//             solvedPuzzle = solvedPuzzle!!.asPuzzleString(),
//             seed = seed,
//             difficulty = difficulty,
//             sudokuType = sudokuType,
//         )
//         return sudoku
//     }
//
//     fun generate(): Array<IntArray> {
//         val puzzle = Array(boxSize) { IntArray(boxSize) }
//         fillDiagonalBlocks(puzzle)
//         fillRemaining(puzzle, 0, boxSize / sudokuSize)
//         removeClues(puzzle, difficulty.cluesToRemove)
//         return puzzle
//     }
//
//     fun retrieveSolution(puzzle: Array<IntArray>): Array<IntArray>? {
//         val solution = puzzle.map { it.copyOf() }.toTypedArray()
//         if (solve(solution)) {
//             return solution
//         }
//         return null
//     }
//
//     private fun solve(puzzle: Array<IntArray>): Boolean {
//         val emptyCell = findEmptyCell(puzzle)
//         if (emptyCell == null) {
//             return true // Puzzle is solved
//         }
//
//         val row = emptyCell.first
//         val col = emptyCell.second
//
//         for (value in 1..boxSize) {
//             if (isSafe(puzzle, row, col, value)) {
//                 puzzle[row][col] = value
//                 if (solve(puzzle)) {
//                     return true
//                 }
//                 puzzle[row][col] = 0 // Undo the current assignment and try the next value
//             }
//         }
//
//         return false // No valid value found, backtrack
//     }
//
//     private fun findEmptyCell(puzzle: Array<IntArray>): Pair<Int, Int>? {
//         for (row in 0 until boxSize) {
//             for (col in 0 until boxSize) {
//                 if (puzzle[row][col] == 0) {
//                     return row to col
//                 }
//             }
//         }
//         return null // No empty cell found, puzzle is already solved
//     }
//
//     private fun fillDiagonalBlocks(puzzle: Array<IntArray>) {
//         val step = boxSize / sudokuSize
//         for (blockStart in 0 until boxSize step step) {
//             fillBlock(puzzle, blockStart, blockStart)
//         }
//     }
//
//     private fun fillBlock(puzzle: Array<IntArray>, row: Int, col: Int) {
//         val values = (1..boxSize).toMutableList()
//         values.shuffle(random)
//         var valueIndex = 0
//         for (i in row until row + boxSize / sudokuSize) {
//             for (j in col until col + boxSize / sudokuSize) {
//                 puzzle[i][j] = values[valueIndex]
//                 valueIndex++
//             }
//         }
//     }
//
//     private fun fillRemaining(puzzle: Array<IntArray>, row: Int, col: Int): Boolean {
//         var currentRow = row
//         var currentCol = col
//
//         if (currentCol >= boxSize && currentRow < boxSize - 1) {
//             currentRow++
//             currentCol = 0
//         }
//
//         if (currentRow >= boxSize && currentCol >= boxSize) {
//             return true
//         }
//
//         if (currentRow < boxSize / sudokuSize) {
//             if (currentCol < boxSize / sudokuSize) {
//                 currentCol = boxSize / sudokuSize
//             }
//         } else if (currentRow < boxSize - boxSize / sudokuSize) {
//             if (currentCol == (currentRow / (boxSize / sudokuSize)) * (boxSize / sudokuSize)) {
//                 currentCol += boxSize / sudokuSize
//             }
//         } else {
//             if (currentCol == boxSize - boxSize / sudokuSize) {
//                 currentRow++
//                 currentCol = 0
//                 if (currentRow >= boxSize) {
//                     return true
//                 }
//             }
//         }
//
//         for (value in 1..boxSize) {
//             if (isSafe(puzzle, currentRow, currentCol, value)) {
//                 puzzle[currentRow][currentCol] = value
//                 if (fillRemaining(puzzle, currentRow, currentCol + 1)) {
//                     return true
//                 }
//                 puzzle[currentRow][currentCol] = 0
//             }
//         }
//
//         return false
//     }
//
//     private fun isSafe(puzzle: Array<IntArray>, row: Int, col: Int, value: Int): Boolean {
//         return !isUsedInRow(puzzle, row, value) &&
//                 !isUsedInColumn(puzzle, col, value) &&
//                 !isUsedInBox(puzzle, row - row % (boxSize / sudokuSize), col - col % (boxSize / sudokuSize), value)
//     }
//
//     private fun isUsedInRow(puzzle: Array<IntArray>, row: Int, value: Int): Boolean {
//         for (col in 0 until boxSize) {
//             if (puzzle[row][col] == value) {
//                 return true
//             }
//         }
//         return false
//     }
//
//     private fun isUsedInColumn(puzzle: Array<IntArray>, col: Int, value: Int): Boolean {
//         for (row in 0 until boxSize) {
//             if (puzzle[row][col] == value) {
//                 return true
//             }
//         }
//         return false
//     }
//
//     private fun isUsedInBox(puzzle: Array<IntArray>, boxStartRow: Int, boxStartCol: Int, value: Int): Boolean {
//         for (row in 0 until boxSize / sudokuSize) {
//             for (col in 0 until boxSize / sudokuSize) {
//                 if (puzzle[row + boxStartRow][col + boxStartCol] == value) {
//                     return true
//                 }
//             }
//         }
//         return false
//     }
//
//     private fun removeClues(puzzle: Array<IntArray>, cluesToRemove: Int) {
//         var numCluesToRemove = cluesToRemove
//         while (numCluesToRemove > 0) {
//             val row = random.nextInt(boxSize)
//             val col = random.nextInt(boxSize)
//             if (puzzle[row][col] != 0) {
//                 puzzle[row][col] = 0
//                 numCluesToRemove--
//             }
//         }
//     }
// }
