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
import dev.teogor.sudoklify.demo.gen.impl.comparePuzzles
import dev.teogor.sudoklify.extensions.toBoard
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.utils.countRemainingCells
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
  SEEDS
    .filter { it.gameType == GameType.NineDigits }
    .forEach {
      println("- [${it.difficulty}, ${it.gameType}]: ${it.puzzle.countRemainingCells()} empty cells")
    }

  SEEDS.forEach { sudoku ->
    val isValid = comparePuzzles(
      puzzle = sudoku.puzzle.toBoard(sudoku.gameType),
      solution = sudoku.solution.toBoard(sudoku.gameType),
    )
    if (!isValid)
      println("invalid puzzles")
  }
}
