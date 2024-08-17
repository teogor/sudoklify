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

@file:OptIn(ExperimentalSudoklifyApi::class)

package dev.teogor.sudoklify.demo

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.SudoklifyArchitect
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.toSeed
import dev.teogor.sudoklify.mapToSudokuBoard
import dev.teogor.sudoklify.mapToSudokuString
import dev.teogor.sudoklify.presets.loadPresetSchemas
import dev.teogor.sudoklify.puzzle.SudokuSpec
import dev.teogor.sudoklify.puzzle.generateGridWithGivens
import dev.teogor.sudoklify.schema.SudokuSchemas

fun main() {
  val architect =
    SudoklifyArchitect {
      SudokuSchemas(loadPresetSchemas()) {
        // TODO Optional: Add own schemas.
        //  addAll(*), add(*)
      }
    }

  val sudokuSpec =
    SudokuSpec {
      seed = 2024L.toSeed()
      type = Dimension.NineByNine
      difficulty = Difficulty.EASY
    }
  val sudokuPuzzle1 = architect.constructSudoku(sudokuSpec)
  val sudokuPuzzle2 =
    architect.constructSudoku {
      seed = 2025L.toSeed()
    }

  val puzzles = listOf(sudokuPuzzle1, sudokuPuzzle2)
  puzzles.forEach { puzzle ->
    println(puzzle.generateGridWithGivens().mapToSudokuString())
    println(
      puzzle.generateGridWithGivens().mapToSudokuString().mapToSudokuBoard(puzzle.type),
    )
  }
}
