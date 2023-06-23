package dev.teogor.sudoku.gen.constants

import dev.teogor.sudoku.gen.types.Difficulty
import dev.teogor.sudoku.gen.types.Layout
import dev.teogor.sudoku.gen.types.Sudoku

val BASE_LAYOUT: Layout = arrayOf(
    intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
    intArrayOf(9, 10, 11, 12, 13, 14, 15, 16, 17),
    intArrayOf(18, 19, 20, 21, 22, 23, 24, 25, 26),
    intArrayOf(27, 28, 29, 30, 31, 32, 33, 34, 35),
    intArrayOf(36, 37, 38, 39, 40, 41, 42, 43, 44),
    intArrayOf(45, 46, 47, 48, 49, 50, 51, 52, 53),
    intArrayOf(54, 55, 56, 57, 58, 59, 60, 61, 62),
    intArrayOf(63, 64, 65, 66, 67, 68, 69, 70, 71),
    intArrayOf(72, 73, 74, 75, 76, 77, 78, 79, 80)
)

val DIFFICULTY_RECORD: Map<Difficulty, Unit> = mapOf(
    "easy" to Unit,
    "medium" to Unit,
    "hard" to Unit,
    "expert" to Unit
)

val DIFFICULTY_LEVELS: List<Difficulty> = DIFFICULTY_RECORD.keys.toList()

val GRID_SIZE: Int = 9

val LINE_CONTAINER: List<List<String>> = List(GRID_SIZE) { emptyList() }

val SEEDS: Array<Sudoku> = arrayOf(
    Sudoku(
        puzzle = "g--d--caf---g----ii-f--hg-bb-iaedhgc--afcg--d-g-b-----f-d--abc---b------c--h-bfia",
        solution = "gbhdiecafacegbfdhiidfcahgebbfiaedhgcehafcgibddgcbhiafefidegabchhabifcedgceghdbfia",
        difficulty = "easy",
    ),
    Sudoku(
        puzzle = "g-hedcf---i-f--a--e--a-----c--i-deh-i-------g--g--e---a----f--c-cf-e-gi-b-------e",
        solution = "gahedcfbidicfbgaehefbaihcgdcbaigdehfihebfadcgfdghceiabaeighfbdchcfdebgiabgdcaihfe",
        difficulty = "medium",
    ),
    Sudoku(
        puzzle = "-------hg-----h-d-a-g---ei--ce--dg--dbf---------bfid--hg---f----d--h---c--a-eg---",
        solution = "bedfiachgficeghbdaahgdbceificehadgfbdbfgceiahgahbfidcehgbcdfaeiediahbfgccfaieghbd",
        difficulty = "hard",
    ),
    Sudoku(
        puzzle = "-bi-------c----e---------af---eba-----a-i-g------c--i----h-e--d-e------gc-b--f---",
        solution = "fbiaegdhcachdfbegiedgchibafgicebafdhbhafidgcedfegchaibiafhgecbdhedbacifgcgbidfhea",
        difficulty = "expert",
    ),
)