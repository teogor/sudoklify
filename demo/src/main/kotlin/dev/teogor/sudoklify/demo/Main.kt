/*
 * Copyright 2023 Teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.teogor.sudoklify.demo

import dev.teogor.sudoklify.SEEDS
import dev.teogor.sudoklify.difficulty
import dev.teogor.sudoklify.extensions.generateSudoku
import dev.teogor.sudoklify.extensions.toSequenceString
import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.Sudoku
import dev.teogor.sudoklify.model.Type
import dev.teogor.sudoklify.seed
import dev.teogor.sudoklify.sudokuParamsBuilder
import dev.teogor.sudoklify.type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
  val _2x2Seeds = SEEDS.filter { it.type == Type.TWO_BY_TWO }
  println("2x2 seeds ${_2x2Seeds.size}")
  Difficulty.values().forEach { difficulty ->
    val size = _2x2Seeds.filter { it.difficulty == difficulty }.size
    println("${difficulty.name} size $size")
  }

  val startTime = System.currentTimeMillis()
  val sudokus: MutableSet<Sudoku> = mutableSetOf()
  val singleSpace = true
  val sudokusSize = listOf(
    Type.TWO_BY_TWO,
    Type.THREE_BY_THREE,
    Type.FOUR_BY_FOUR,
  )
  val sudokusResult = listOf(
    // 4x4
    "4123321423411432",
    // 9x9
    "924761835658923417371485296743598621892146573516237948437652189285319764169874352",
    // 16x16
    "31127813914516611041215311278139145166110412158610161245111915214713351512927101131314461681610134151212711814953161114512987616431511021316311541113109526712148213145710119812163411561381611012147415115236915591411163862171213104101271161542133981614159782143165121101315641112161161584151413932710741583212161651013119141931013146152741158161214141316615310871211952",
  )
  if (singleSpace) {
    val seed = 0L
    sudokusSize.forEachIndexed { index, type ->
      val sudokuParams = sudokuParamsBuilder {
        seed {
          seed
        }

        type {
          type
        }

        difficulty {
          Difficulty.EASY
        }
      }
      val sudoku = sudokuParams.generateSudoku()
      sudokus.add(sudoku)
      val solution = sudoku.solution
      if (solution.toSequenceString() != sudokusResult[index]) {
        println("test constraint failed for $type")
      } else {
        println("test constraint passed for $type")
      }
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
            val sudokuParams = sudokuParamsBuilder {
              seed {
                System.currentTimeMillis()
              }

              type {
                Type.THREE_BY_THREE
              }

              difficulty {
                Difficulty.EASY
              }
            }
            val sudoku = sudokuParams.generateSudoku()
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
