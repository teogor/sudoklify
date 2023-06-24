package dev.teogor

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
        val seed = 0L
        repeat(10) {
            val sudoku = SudokuGenerator.getSudoku(
                difficulty = "easy",
                seed = seed,
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
                            difficulty = "easy",
                            seed = System.currentTimeMillis(),
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

