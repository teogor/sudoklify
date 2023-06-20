import beta.Difficulty
import beta.Puzzle
import beta.SudokuGenerator
import beta.SudokuSolver
import beta.SudokuType

fun main() {
    val type = SudokuType.THREE_BY_THREE
    val seed = 0L
    val difficulty = Difficulty.MEDIUM

    val generator = SudokuGenerator(type, seed)
    val sudoku = generator.generate(difficulty)

    println("Generated Puzzle:")
    printSudoku(sudoku.puzzle)

    println("Solution:")
    printSudoku(sudoku.solution)

    val solver = SudokuSolver(sudoku)
    val isSolved = solver.solve()

    if (isSolved) {
        println("Sudoku solved.")
    } else {
        println("No solution found for the Sudoku.")
    }
}

fun printSudoku(puzzle: Puzzle) {
    println(puzzle.toString())
}
