import beta.SudokuGenerator
import com.google.gson.Gson
import java.io.File

fun main() {
    val debug = true
    val sudokuType = if (debug) {
        SudokuGenerator.SudokuType.FOUR_BY_FOUR
    } else {
        SudokuGenerator.SudokuType.THREE_BY_THREE
    }
    val difficulty = SudokuGenerator.Difficulty.DIFFICULT

    val startTime = System.currentTimeMillis()
    val sudokus: MutableSet<Sudoku> = mutableSetOf()
    val sudokusData: MutableSet<String> = mutableSetOf()
    for (seed in 0L..1000L) {
        val generator = SudokuGenerator(sudokuType, seed, difficulty)
        val sudoku = generator.generateWithDifficulty()
        // val sudoku = generator.get()

        sudokus.add(sudoku) // Add the Sudoku object to the set of sudokus
        sudokusData.add(sudoku.puzzle) // Add the puzzle string to the set of sudokusData

        println("sudoku: ${sudoku.puzzle}")
        // println("solved-sudoku: ${sudoku.solvedPuzzle}")
    }

    val gson = Gson()
    val json = gson.toJson(sudokus)
    val file = File("sudokus.json")
    file.writeText(json)

    val endTime = System.currentTimeMillis()
    val generationTime = endTime - startTime

    println("Sudoku List Generation Time: $generationTime ms")
    println("Sudokus size ${sudokus.size} (${sudokusData.size})")
}