package dev.teogor.sudoklify.seeds

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.model.SudokuBlueprint

/**
 * An array of `SudokuBlueprint` objects representing easy 9x9 Sudoku puzzles.
 */
val nineDigitsSeeds: Array<SudokuBlueprint> = arrayOf(
  SudokuBlueprint(
    puzzle = "G--D--CAF---G----II-F--HG-BB-IAEDHGC--AFCG--D-G-B-----F-D--ABC---B------C--H-BFIA",
    solution = "GBHDIECAFACEGBFDHIIDFCAHGEBBFIAEDHGCEHAFCGIBDDGCBHIAFEFIDEGABCHHABIFCEDGCEGHDBFIA",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  SudokuBlueprint(
    puzzle = "G-HEDCF---I-F--A--E--A-----C--I-DEH-I-------G--G--E---A----F--C-CF-E-GI-B-------E",
    solution = "GAHEDCFBIDICFBGAEHEFBAIHCGDCBAIGDEHFIHEBFADCGFDGHCEIABAEIGHFBDCHCFDEBGIABGDCAIHFE",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  SudokuBlueprint(
    puzzle = "-------HG-----H-D-A-G---EI--CE--DG--DBF---------BFID--HG---F----D--H---C--A-EG---",
    solution = "BEDFIACHGFICEGHBDAAHGDBCEIFICEHADGFBDBFGCEIAHGAHBFIDCEHGBCDFAEIEDIAHBFGCCFAIEGHBD",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  SudokuBlueprint(
    puzzle = "-BI-------C----E---------AF---EBA-----A-I-G------C--I----H-E--D-E------GC-B--F---",
    solution = "FBIAEGDHCACHDFBEGIEDGCHIBAFGICEBAFDHBHAFIDGCEDFEGCHAIBIAFHGECBDHEDBACIFGCGBIDFHEA",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),
)
