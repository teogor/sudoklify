import beta.Difficulty
import beta.Sudoku
import beta.SudokuGenerator
import beta.SudokuType
import com.google.gson.Gson
import java.io.File

fun main() {
    val startTime = System.currentTimeMillis()
    val sudokus: MutableSet<Sudoku> = mutableSetOf()
    val sudokusData: MutableSet<String> = mutableSetOf()

    val type = SudokuType.THREE_BY_THREE
    val difficulty = Difficulty.EASY
    for (seed in 0L..1000L) {
        val generator = SudokuGenerator(type, seed)
        val sudoku = generator.generate(difficulty)
        sudokus.add(sudoku)
        sudokusData.add(sudoku.puzzle.formatAsString())

        println("sudoku:\n${sudoku.puzzle}")
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