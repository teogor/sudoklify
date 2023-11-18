package dev.teogor.sudoklify.seeds

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.model.SudokuBlueprint

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
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "BADCD--AA--DCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "DC-ABA-CCD-BAB-D",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "DCB-BA-CC-AB-BCD",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "C-ABA-CDD-BAB-DC",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-DABA-CDDC-ABAD-",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "BADC----ABCDCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "----CDABBADCDCBA",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),

  // Medium
  SudokuBlueprint(
    puzzle = "D--B-CA--DB-C--A",
    solution = "DACBBCADADBCCBDA",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-BC-A--DB--C-AD-",
    solution = "DBCAACBDBDACCADB",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "--CDCD--AB----AB",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "DB----BDCA----AC",
    solution = "DBCAACBDCADBBDAC",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "--CD--BAAB--DC--",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-BC-A--BC--D-DA-",
    solution = "DBCAACDBCABDBDAC",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "C--AD--B-CA--DB-",
    solution = "CBDADACBBCADADBC",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "B--A-DC--BA-C--D",
    solution = "BCDAADCBDBACCABD",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),

  // Hard
  SudokuBlueprint(
    puzzle = "-CD-D--CC--A-AC-",
    solution = "ACDBDBACCDBABACD",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "D-A-A-B--D-A-A-B",
    solution = "DBACACBDBDCACADB",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-DB--BA-D--BB--A",
    solution = "ADBCCBADDACBBCDA",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-D-A-A-D-C-BA-D-",
    solution = "BDCACABDDCABABDC",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-DC--CB-D--CC--B",
    solution = "BDCAACBDDBACCADB",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "--CDDC----BCCB--",
    solution = "BACDDCABADBCCBDA",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "AB----BABD----DB",
    solution = "ABCDDCBABDACCADB",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "A--B-BC--AB-B--C",
    solution = "ACDBDBCACABDBDAC",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),

  // VERY_HARD
  SudokuBlueprint(
    puzzle = "-AC---------CDBA",
    solution = "BACDDCABABDCCDBA",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-C-A--B---C--D-B",
    solution = "BCDADABCABCDCDAB",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "BDCA-----AB-----",
    solution = "BDCAACDBCABDDBAC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "----ABCD-DB-----",
    solution = "DCABABCDCDBABADC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "C----A-B-CD-A---",
    solution = "CBADDACBBCDAADBC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-CB--A---DA--B--",
    solution = "DCBABACDCDABABDC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "A--D---B---CD--A",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "----DACB-BA-----",
    solution = "BCDADACBCBADADBC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
)
