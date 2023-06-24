package dev.teogor

import dev.teogor.sudoku.gen.Difficulty
import dev.teogor.sudoku.gen.Sudoku
import dev.teogor.sudoku.gen.SudokuGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import toFormattedTime

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val sudokus: MutableSet<Sudoku> = mutableSetOf()
    val singleSpace = true
    if (singleSpace) {
        // 4x4 = 4312321442314123
        // 9x9 = 924761835658923417371485296743598621892146573516237948437652189285319764169874352
        val seed = 0L
        repeat(2) {
            val sudoku = SudokuGenerator.getSudoku(
                difficulty = Difficulty.EASY,
                seed = seed,
                gridSize = 4,
            )
            println(sudoku.solution)
            sudokus.add(sudoku)
        }
    } else {
        val parallelism = 1
        val setOf = 10L
        val startSeed = 0 * setOf
        val endSeed = startSeed + setOf
        val job = launch(Dispatchers.Default) {
            val chunkSize = (endSeed - startSeed) / parallelism
            val ranges = (startSeed until endSeed).chunked(chunkSize.toInt())
            for (range in ranges) {
                launch {
                    repeat(parallelism) {
                        val sudoku = SudokuGenerator.getSudoku(
                            difficulty = Difficulty.EASY,
                            seed = System.currentTimeMillis(),
                            gridSize = 9,
                        )
                        println(sudoku.solution)
                        sudokus.add(sudoku)
                    }
                }
            }
        }
        job.join()
    }
    val endTime = System.currentTimeMillis()
    val generationTime = endTime - startTime

    println()
    println("Sudoku List Generation Time: ${generationTime.toFormattedTime()}")
    println("Sudokus size ${sudokus.size}")
}

