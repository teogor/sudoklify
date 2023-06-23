package dev.teogor.sudoku.gen

import kotlin.random.Random

data class Sudoku(val puzzle: String, val solution: String, val difficulty: Difficulty)

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

typealias Difficulty = String
typealias Token = String
typealias Board = Array<Array<Char>>
typealias Layout = Array<IntArray>
typealias TokenMap = Map<Token, String>

class SudokuGenerator(
    private val random: Random
) {
    companion object {
        fun getSudoku(difficulty: Difficulty?, seed: Long): Sudoku {
            val sudokuGenerator = SudokuGenerator(
                random = Random(seed)
            ).apply {

            }
            return sudokuGenerator.getSudoku(difficulty)
        }
    }

    private fun getSudoku(difficulty: Difficulty?): Sudoku {
        if (!difficulty.isNullOrBlank() && !validateDifficulty(difficulty)) {
            throw IllegalArgumentException("Invalid difficulty, expected one of: ${DIFFICULTY_LEVELS.joinToString(", ")}")
        }

        val seed = getSeed(SEEDS, difficulty)
        val layout = getLayout(BASE_LAYOUT)
        val tokenMap = getTokenMap()

        val puzzle = getSequence(layout, seed.puzzle, tokenMap)
        val solution = getSequence(layout, seed.solution, tokenMap)

        return Sudoku(puzzle, solution, seed.difficulty)
    }

    private fun boardToSequence(board: Board): String = board.joinToString("") { it.joinToString("") }

    private fun <T> getRandomItem(items: List<T>): T = items.random()

    private fun getSequence(layout: Layout, seedSequence: String, tokenMap: TokenMap): String {
        val populatedLayout = populateLayout(layout, replaceTokens(seedSequence, tokenMap))
        return boardToSequence(populatedLayout)
    }

    private fun replaceTokens(sequence: String, tokenMap: TokenMap): String {
        return sequence
            .split("").joinToString("") { token -> tokenMap[token] ?: token }
    }

    private fun sequenceToBoard(sequence: String): Board {
        val board = mutableListOf<Array<Char>>()
        sequence.chunked(GRID_SIZE).forEach { chunk ->
            board.add(chunk.toList().toTypedArray())
        }
        return board.toTypedArray()
    }

    private fun sortRandom(): Int = if (Math.random() < 0.5) 1 else -1

    private fun getLayout(baseLayout: Layout): Layout = shuffleLayout(rotateLayout(baseLayout))

    private fun getLayoutBands(layout: Layout): Array<Array<IntArray>> = arrayOf(
        arrayOf(layout[0], layout[1], layout[2]),
        arrayOf(layout[3], layout[4], layout[5]),
        arrayOf(layout[6], layout[7], layout[8])
    )

    private fun populateLayout(layout: Layout, sequence: String): Board =
        layout.map { row -> row.map { cell -> sequence[cell] }.toTypedArray() }.toTypedArray()

    private fun rotateLayout(layout: Layout): Layout =
        getRandomItem(listOf(::rotateLayout0, ::rotateLayout90, ::rotateLayout180, ::rotateLayout270))(layout)

    private fun rotateLayout0(layout: Layout): Layout = layout

    private fun rotateLayout90(layout: Layout): Layout =
        layout[0].mapIndexed { index, _ -> layout.map { row -> row[index] }.reversed().toIntArray() }.toTypedArray()

    private fun rotateLayout180(layout: Layout): Layout =
        layout.map { row -> row.reversed().toIntArray() }.reversed().toTypedArray()

    private fun rotateLayout270(layout: Layout): Layout =
        layout[0].mapIndexed { index, _ -> layout.map { row -> row.reversed()[index] }.toIntArray() }.toTypedArray()

    private fun shuffleLayout(layout: Layout): Layout =
        shuffleLayoutColumns(shuffleLayoutRows(shuffleLayoutStacks(shuffleLayoutBands(layout))))

    private fun shuffleLayoutBands(layout: Layout): Layout =
        getLayoutBands(layout).sortedWith(compareBy { sortRandom() }).flatMap { it.toList() }.toTypedArray()

    private fun shuffleLayoutColumns(layout: Layout): Layout =
        rotateLayout270(shuffleLayoutRows(rotateLayout90(layout)))

    private fun shuffleLayoutRows(layout: Layout): Layout =
        getLayoutBands(layout).map { rows -> rows.sortedWith(compareBy { sortRandom() }) }.flatMap { it.toList() }.toTypedArray()

    private fun shuffleLayoutStacks(layout: Layout): Layout =
        rotateLayout270(shuffleLayoutBands(rotateLayout90(layout)))

    private fun getRandomItem(items: List<(Layout) -> Layout>): (Layout) -> Layout =
        items.shuffled().first()

    private fun getSeed(seeds: Array<Sudoku>, difficulty: Difficulty?): Sudoku =
        getRandomItem(getSeedsByDifficulty(seeds, difficulty))

    private fun getSeedsByDifficulty(seeds: Array<Sudoku>, difficulty: Difficulty?): Array<Sudoku> =
        seeds.filter { seed -> difficulty == null || seed.difficulty == difficulty }.toTypedArray()

    private fun getRandomItem(items: Array<Sudoku>): Sudoku =
        items[(Math.random() * items.size).toInt()]
    
    private fun getTokenMap(): TokenMap {
        val tokenList = "abcdefghi".toList()
        val random = Random(sortRandom())
        val shuffledList = tokenList.shuffled(random)
        return shuffledList.withIndex().associate { (index, token) -> token.toString() to (index + 1).toString() }
    }
    
    private fun validateDifficulty(difficulty: Any): Boolean {
        return DIFFICULTY_LEVELS.contains(difficulty)
    }
}