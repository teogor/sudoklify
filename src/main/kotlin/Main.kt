import beta.Difficulty
import beta.Puzzle
import beta.PuzzleSerializer
import beta.Sudoku
import beta.SudokuGenerator
import beta.SudokuType
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // readSudokus()

    val startTime = System.currentTimeMillis()
    val sudokus: MutableSet<Sudoku> = mutableSetOf()
    val sudokusData: MutableSet<String> = mutableSetOf()

    // val difficulties = arrayOf(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD)
    val difficulties = arrayOf(Difficulty.HARD)
    var index = 1
    val setOf = 1000000L
    for (difficulty in difficulties) {
        val type = SudokuType.THREE_BY_THREE
        val startSeed = index * setOf
        val endSeed = startSeed + setOf
        val iterationStep = 100L
        val totalIterations = endSeed / iterationStep

        var stepStartTime = System.currentTimeMillis()
        val parallelism = 10
        val job = launch(Dispatchers.Default) {
            val chunkSize = (endSeed - startSeed) / parallelism
            val ranges = (startSeed until endSeed).chunked(chunkSize.toInt())
            for (range in ranges) {
                launch {
                    for (seed in range) {
                        val generator = SudokuGenerator(type, seed)
                        val sudoku = generator.generate(difficulty)

                        synchronized(sudokus) {
                            sudokus.add(sudoku)
                        }
                        synchronized(sudokusData) {
                            sudokusData.add(sudoku.puzzle.formatAsString())
                        }

                        if (seed % iterationStep == 0L) {
                            val endTime = System.currentTimeMillis()
                            val totalTime = endTime - startTime
                            val stepTime = endTime - stepStartTime
                            val step = seed / iterationStep + 1

                            println("Step $step/$totalIterations | Time taken: ${stepTime}ms/${totalTime.toFormattedTime()}")
                            stepStartTime = System.currentTimeMillis()
                        }
                    }
                }
            }
        }
        job.join()
        index++
    }

    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(Puzzle::class.java, PuzzleSerializer())
    val gson = gsonBuilder.create()

    val json = gson.toJson(sudokus)
    val file = File("sudokus.json")
    file.writeText(json)

    val endTime = System.currentTimeMillis()
    val generationTime = endTime - startTime

    println()
    println("Sudoku List Generation Time: ${generationTime.toFormattedTime()}")
    println("Sudokus size ${sudokus.size} (${sudokusData.size})")
}

fun readSudokus() {

    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(Puzzle::class.java, PuzzleSerializer())
    val gson = gsonBuilder.create()

    val file = File("sudokus.json")
    val json = file.readText()

    println(json) // Print the JSON string for debugging

    val sudokus: Set<Sudoku> = gson.fromJson(json, object : TypeToken<Set<Sudoku>>() {}.type)
    println("sudokus size ${sudokus.size}")
}

fun Long.toFormattedTime(): String {
    val seconds = (this / 1000) % 60
    val minutes = (this / (1000 * 60)) % 60
    val hours = (this / (1000 * 60 * 60))

    val timeStringBuilder = StringBuilder()

    if (hours > 0) {
        timeStringBuilder.append("${hours}h ")
    }

    if (minutes > 0) {
        timeStringBuilder.append("${minutes}m ")
    }

    if (seconds > 0 && (hours == 0L && minutes == 0L)) {
        timeStringBuilder.append("${seconds}s")
    } else {
        timeStringBuilder.append("${this % 1000}ms")
    }

    return timeStringBuilder.toString().trimEnd(',', ' ')
}

