package dev.teogor.sudoku.gen

import kotlin.math.sqrt
import kotlin.random.Random

typealias SudokuString = String
typealias PuzzleString = SudokuString
typealias SolutionString = SudokuString
typealias Token = String
typealias Board = Array<Array<Char>>
typealias Layout = Array<IntArray>
typealias TokenMap = Map<Token, String>

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD,
    EXPERT
}

data class Sudoku(
    val puzzle: PuzzleString,
    val solution: SolutionString,
    val difficulty: Difficulty
)

val SEEDS: Array<Sudoku> = arrayOf(
    Sudoku(
        puzzle = "G--D--CAF---G----II-F--HG-BB-IAEDHGC--AFCG--D-G-B-----F-D--ABC---B------C--H-BFIA",
        solution = "GBHDIECAFACEGBFDHIIDFCAHGEBBFIAEDHGCEHAFCGIBDDGCBHIAFEFIDEGABCHHABIFCEDGCEGHDBFIA",
        difficulty = Difficulty.EASY,
    ),
    Sudoku(
        puzzle = "G-HEDCF---I-F--A--E--A-----C--I-DEH-I-------G--G--E---A----F--C-CF-E-GI-B-------E",
        solution = "GAHEDCFBIDICFBGAEHEFBAIHCGDCBAIGDEHFIHEBFADCGFDGHCEIABAEIGHFBDCHCFDEBGIABGDCAIHFE",
        difficulty = Difficulty.MEDIUM,
    ),
    Sudoku(
        puzzle = "-------HG-----H-D-A-G---EI--CE--DG--DBF---------BFID--HG---F----D--H---C--A-EG---",
        solution = "BEDFIACHGFICEGHBDAAHGDBCEIFICEHADGFBDBFGCEIAHGAHBFIDCEHGBCDFAEIEDIAHBFGCCFAIEGHBD",
        difficulty = Difficulty.HARD,
    ),
    Sudoku(
        puzzle = "-BI-------C----E---------AF---EBA-----A-I-G------C--I----H-E--D-E------GC-B--F---",
        solution = "FBIAEGDHCACHDFBEGIEDGCHIBAFGICEBAFDHBHAFIDGCEDFEGCHAIBIAFHGECBDHEDBACIFGCGBIDFHEA",
        difficulty = Difficulty.EXPERT,
    ),
)

class SudokuGenerator(
    private val random: Random,
    private val gridSize: Int = 9,
) {
    companion object {
        fun getSudoku(difficulty: Difficulty, seed: Long, gridSize: Int): Sudoku {
            val sudokuGenerator = SudokuGenerator(
                random = Random(seed),
                gridSize = gridSize,
            )
            return sudokuGenerator.getSudoku(difficulty)
        }
    }

    private val gridArea = gridSize * gridSize
    private val baseLayout: Layout = generateBaseLayout()

    private fun generateBaseLayout(): Layout {
        val baseLayout = Array(gridSize) { IntArray(gridSize) }
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                baseLayout[i][j] = i * gridSize + j
            }
        }
        return baseLayout
    }

    private fun getSudoku(difficulty: Difficulty): Sudoku {
        val seed = getSeed(SEEDS, difficulty)
        val layout = getLayout(baseLayout)
        val tokenMap = getTokenMap()

        val puzzle = getSequence(layout, seed.puzzle, tokenMap)
        val solution = getSequence(layout, seed.solution, tokenMap)

        return Sudoku(puzzle, solution, seed.difficulty)
    }

    private fun boardToSequence(board: Board): String = board.joinToString("") { it.joinToString("") }

    private fun <T> getRandomItem(items: List<T>): T = items.random(random)

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
        sequence.chunked(gridSize).forEach { chunk ->
            board.add(chunk.toList().toTypedArray())
        }
        return board.toTypedArray()
    }

    private fun sortRandom(): Int = if (random.nextDouble() < 0.5) 1 else -1

    private fun getLayout(baseLayout: Layout): Layout = shuffleLayout(rotateLayout(baseLayout))

    private fun getLayoutBands(layout: Layout): Array<Array<IntArray>> {
        val bandSize = sqrt(gridSize.toDouble()).toInt()
        val bands = mutableListOf<Array<IntArray>>()
        for (i in 0 until gridSize step bandSize) {
            val band = layout.slice(i until i + bandSize).toTypedArray()
            bands.add(band)
        }
        return bands.toTypedArray()
    }

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
        getLayoutBands(layout).map { rows -> rows.sortedWith(compareBy { sortRandom() }) }.flatMap { it.toList() }
            .toTypedArray()

    private fun shuffleLayoutStacks(layout: Layout): Layout =
        rotateLayout270(shuffleLayoutBands(rotateLayout90(layout)))

    private fun getRandomItem(items: List<(Layout) -> Layout>): (Layout) -> Layout =
        items.shuffled(random).first()

    private fun getSeed(seeds: Array<Sudoku>, difficulty: Difficulty?): Sudoku =
        getRandomItem(getSeedsByDifficulty(seeds, difficulty))

    private fun getSeedsByDifficulty(seeds: Array<Sudoku>, difficulty: Difficulty?): Array<Sudoku> =
        seeds.filter { seed -> difficulty == null || seed.difficulty == difficulty }.toTypedArray()

    private fun getRandomItem(items: Array<Sudoku>): Sudoku =
        items[random.nextInt(items.size)]

    private fun getTokenMap(): TokenMap {
        val tokenList = "ABCDEFGHI".toList()
        val shuffledList = tokenList.shuffled(random)
        return shuffledList.withIndex().associate { (index, token) -> token.toString() to (index + 1).toString() }
    }
}
