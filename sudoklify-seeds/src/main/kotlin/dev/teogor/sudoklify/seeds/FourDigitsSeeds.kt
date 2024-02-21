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

import dev.teogor.sudoklify.common.model.SudokuBlueprint
import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.types.SudokuType

// TODO use JSON and versioning for different seeds like Version 1 version 2 etc
/**
 * An array of `SudokuBlueprint` objects representing easy 4x4 Sudoku puzzles.
 */
val fourDigitsSeeds: Array<SudokuBlueprint> = arrayOf(
  // Easy
  SudokuBlueprint(
    puzzle = "-BCDCDA-BAD--CBA",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "BADCD--AA--DCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "DC-ABA-CCD-BAB-D",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "DCB-BA-CC-AB-BCD",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "C-ABA-CDD-BAB-DC",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-DABA-CDDC-ABAD-",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "BADC----ABCDCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "----CDABBADCDCBA",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku4x4,
  ),

  // Medium
  SudokuBlueprint(
    puzzle = "D--B-CA--DB-C--A",
    solution = "DACBBCADADBCCBDA",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-BC-A--DB--C-AD-",
    solution = "DBCAACBDBDACCADB",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "--CDCD--AB----AB",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "DB----BDCA----AC",
    solution = "DBCAACBDCADBBDAC",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "--CD--BAAB--DC--",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-BC-A--BC--D-DA-",
    solution = "DBCAACDBCABDBDAC",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "C--AD--B-CA--DB-",
    solution = "CBDADACBBCADADBC",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "B--A-DC--BA-C--D",
    solution = "BCDAADCBDBACCABD",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku4x4,
  ),

  // Hard
  SudokuBlueprint(
    puzzle = "-CD-D--CC--A-AC-",
    solution = "ACDBDBACCDBABACD",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "D-A-A-B--D-A-A-B",
    solution = "DBACACBDBDCACADB",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-DB--BA-D--BB--A",
    solution = "ADBCCBADDACBBCDA",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-D-A-A-D-C-BA-D-",
    solution = "BDCACABDDCABABDC",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-DC--CB-D--CC--B",
    solution = "BDCAACBDDBACCADB",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "--CDDC----BCCB--",
    solution = "BACDDCABADBCCBDA",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "AB----BABD----DB",
    solution = "ABCDDCBABDACCADB",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "A--B-BC--AB-B--C",
    solution = "ACDBDBCACABDBDAC",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),

  // VERY_HARD
  SudokuBlueprint(
    puzzle = "-AC---------CDBA",
    solution = "BACDDCABABDCCDBA",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-C-A--B---C--D-B",
    solution = "BCDADABCABCDCDAB",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "BDCA-----AB-----",
    solution = "BDCAACDBCABDDBAC",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "----ABCD-DB-----",
    solution = "DCABABCDCDBABADC",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "C----A-B-CD-A---",
    solution = "CBADDACBBCDAADBC",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "-CB--A---DA--B--",
    solution = "DCBABACDCDABABDC",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "A--D---B---CD--A",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
  SudokuBlueprint(
    puzzle = "----DACB-BA-----",
    solution = "BCDADACBCBADADBC",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku4x4,
  ),
)
