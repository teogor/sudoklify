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

package dev.teogor.sudoklify.seeds

import dev.teogor.sudoklify.common.types.Board
import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.SudokuType
import dev.teogor.sudoklify.core.generation.difficulty
import dev.teogor.sudoklify.core.generation.generateSudoku
import dev.teogor.sudoklify.core.generation.seed
import dev.teogor.sudoklify.core.generation.seeds
import dev.teogor.sudoklify.core.generation.sudokuParamsBuilder
import dev.teogor.sudoklify.core.generation.sudokuType
import dev.teogor.sudoklify.core.util.toBoard
import dev.teogor.sudoklify.core.util.toSequenceString
import dev.teogor.sudoklify.ktx.createSeed
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SudokuSeedsTest {

  @Test
  fun `test Verify Solution Generation for 4x4 Sudoku`() {
    val expectedSolution = "3214412314322341"

    val sudokuParams = sudokuParamsBuilder {
      seeds { combinedSeeds }
      seed { createSeed(0L) }
      sudokuType { SudokuType.Sudoku4x4 }
      difficulty { Difficulty.EASY }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(expectedSolution, sudoku.solution.toSequenceString())
  }

  @Test
  fun `test Verify Solution Generation for 9x9 Sudoku`() {
    val expectedSolution =
      "395126784178549623642783915531678249964215378827934561486352197759861432213497856"

    val sudokuParams = sudokuParamsBuilder {
      seeds { combinedSeeds }
      seed { createSeed(0L) }
      sudokuType { SudokuType.Sudoku9x9 }
      difficulty { Difficulty.EASY }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(expectedSolution, sudoku.solution.toSequenceString())
  }

  @Test
  fun `test Verify Solution Generation for 16x16 Sudoku`() {
    val expectedSolution =
      "15101714111269161352834151017141112691613528341441181016131512231956731669182547141512111310121410168315137914625115246127114310161115981381113195104151226714163697145134111081231161521061631315714411128152947513263816115911121014912151141416102581336717391562111681351441011221584161261131497103115161312515108311142147961113107914251561613412813521234791161081611415"

    val sudokuParams = sudokuParamsBuilder {
      seeds { combinedSeeds }
      seed { createSeed(0L) }
      sudokuType { SudokuType.Sudoku16x16 }
      difficulty { Difficulty.EASY }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(expectedSolution, sudoku.solution.toSequenceString())
  }

  @Test
  fun `test Verify Solution Generation for 25x25 Sudoku`() {
    val expectedSolution =
      "62281315211101618205914112571724122342319207102125931711622113232441952151612814181232123722813191718154211410616205249112511195916142420234825212103221182113176715212332581713414225201292151810196247111162320178526741219141122918115132521162410391214313252281410762115111623172418192025324720111813623412181525102211416195229172411142213120231521181631782519410259712672116151918935102424251281420622111231713510211973262291281420181325161523174124111412231112152524821101719796452201318162321618122023191110113942451781437156252122817423920211618131115256124122210197253141062518216111917241132352079122132281415422913221191618203724511236178124101415251174231612101452125922081922153716111318241514191062249122317211135242511182320168741824141725512215631971623131189122102021258115181572411714610162219209231332124121651211102423192015197184213132281425176218220174512213716112210612324251481519139115924146182191125218131716472051223322101159241461821911252181317164720512233221019136722810152516242313141211291741821520"

    val sudokuParams = sudokuParamsBuilder {
      seed { createSeed(0L) }
      sudokuType { SudokuType.Sudoku25x25 }
      difficulty { Difficulty.EASY }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(expectedSolution, sudoku.solution.toSequenceString())
  }

  @Test
  fun `test Verify Puzzle Solutions Against Generated Puzzles`() {
    combinedSeeds.forEach { sudoku ->
      val isValid = comparePuzzles(
        puzzle = sudoku.puzzle.toBoard(sudoku.sudokuType),
        solution = sudoku.solution.toBoard(sudoku.sudokuType),
      )

      assertEquals(true, isValid, "Invalid puzzle for seed ${sudoku.solution}")
    }
  }

  private fun comparePuzzles(puzzle: Board, solution: Board): Boolean {
    if (puzzle.size != solution.size || puzzle[0].size != solution[0].size) {
      return false
    }

    for (row in puzzle.indices) {
      for (col in 0 until puzzle[row].size) {
        val puzzleValue = puzzle[row][col]
        val solvedValue = solution[row][col]

        if (puzzleValue != "-" && puzzleValue != solvedValue) {
          return false
        }
      }
    }

    return true
  }
}
