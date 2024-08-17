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

package dev.teogor.sudoklify.presets

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.schema.SudokuSchema

/**
 * An array of [SudokuSchema] objects representing easy 4x4 Sudoku puzzles.
 */
val fourDigitsSchemas: Array<SudokuSchema> = arrayOf(
  // region Difficulty - Very Easy (*)
  // endregion

  // region Difficulty - Easy (**)
  SudokuSchema(
    puzzle = "-BCDCDA-BAD--CBA",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "BADCD--AA--DCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "DC-ABA-CCD-BAB-D",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "DCB-BA-CC-AB-BCD",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "C-ABA-CDD-BAB-DC",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-DABA-CDDC-ABAD-",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "BADC----ABCDCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "----CDABBADCDCBA",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.EASY,
    dimension = Dimension.FourByFour,
  ),
  // endregion

  // region Difficulty - MEDIUM (***)
  SudokuSchema(
    puzzle = "D--B-CA--DB-C--A",
    solution = "DACBBCADADBCCBDA",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-BC-A--DB--C-AD-",
    solution = "DBCAACBDBDACCADB",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "--CDCD--AB----AB",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "DB----BDCA----AC",
    solution = "DBCAACBDCADBBDAC",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "--CD--BAAB--DC--",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-BC-A--BC--D-DA-",
    solution = "DBCAACDBCABDBDAC",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "C--AD--B-CA--DB-",
    solution = "CBDADACBBCADADBC",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "B--A-DC--BA-C--D",
    solution = "BCDAADCBDBACCABD",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.FourByFour,
  ),
  // endregion

  // region Difficulty - HARD (****)
  SudokuSchema(
    puzzle = "-CD-D--CC--A-AC-",
    solution = "ACDBDBACCDBABACD",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "D-A-A-B--D-A-A-B",
    solution = "DBACACBDBDCACADB",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-DB--BA-D--BB--A",
    solution = "ADBCCBADDACBBCDA",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-D-A-A-D-C-BA-D-",
    solution = "BDCACABDDCABABDC",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-DC--CB-D--CC--B",
    solution = "BDCAACBDDBACCADB",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "--CDDC----BCCB--",
    solution = "BACDDCABADBCCBDA",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "AB----BABD----DB",
    solution = "ABCDDCBABDACCADB",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "A--B-BC--AB-B--C",
    solution = "ACDBDBCACABDBDAC",
    difficulty = Difficulty.HARD,
    dimension = Dimension.FourByFour,
  ),
  // endregion

  // region Difficulty - VERY_HARD (*****)
  SudokuSchema(
    puzzle = "-AC---------CDBA",
    solution = "BACDDCABABDCCDBA",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-C-A--B---C--D-B",
    solution = "BCDADABCABCDCDAB",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "BDCA-----AB-----",
    solution = "BDCAACDBCABDDBAC",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "----ABCD-DB-----",
    solution = "DCABABCDCDBABADC",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "C----A-B-CD-A---",
    solution = "CBADDACBBCDAADBC",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "-CB--A---DA--B--",
    solution = "DCBABACDCDABABDC",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "A--D---B---CD--A",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
  SudokuSchema(
    puzzle = "----DACB-BA-----",
    solution = "BCDADACBCBADADBC",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.FourByFour,
  ),
)
