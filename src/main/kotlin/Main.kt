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
    val startSeed = 0L
    val endSeed = 10000L
    val iterationStep = 100L
    val totalIterations = endSeed / iterationStep

    var stepStartTime = System.currentTimeMillis()
    for (seed in startSeed until endSeed) {
        val generator = SudokuGenerator(type, seed)
        val sudoku = generator.generate(difficulty)
        sudokus.add(sudoku)
        sudokusData.add(sudoku.puzzle.formatAsString())

        if (seed % iterationStep == 0L) {
            val endTime = System.currentTimeMillis()
            val totalTime = endTime - startTime
            val stepTime = endTime - stepStartTime
            val step = seed / iterationStep + 1

            println("Step $step/$totalIterations | Time taken: $stepTime ms/${totalTime.toFormattedTime()}")
            stepStartTime = System.currentTimeMillis()
        }
    }

    val gson = Gson()
    val json = gson.toJson(sudokus)
    val file = File("sudokus.json")
    file.writeText(json)

    val endTime = System.currentTimeMillis()
    val generationTime = endTime - startTime

    println()
    println("Sudoku List Generation Time: $generationTime ms")
    println("Sudokus size ${sudokus.size} (${sudokusData.size})")
}

fun Long.toFormattedTime(): String {
    val seconds = (this / 1000) % 60
    val minutes = (this / (1000 * 60)) % 60
    val hours = (this / (1000 * 60 * 60))

    val timeStringBuilder = StringBuilder()

    if (hours > 0) {
        timeStringBuilder.append("$hours h ")
    }

    if (minutes > 0) {
        timeStringBuilder.append("$minutes m ")
    }

    if (seconds > 0 && (hours == 0L && minutes == 0L)) {
        timeStringBuilder.append("$seconds s")
    } else {
        timeStringBuilder.append("${this % 1000} ms")
    }

    return timeStringBuilder.toString().trimEnd(',', ' ')
}

