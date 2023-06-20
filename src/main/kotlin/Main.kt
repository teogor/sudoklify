import beta.Difficulty
import beta.Puzzle
import beta.SudokuGenerator
import beta.SudokuType

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
