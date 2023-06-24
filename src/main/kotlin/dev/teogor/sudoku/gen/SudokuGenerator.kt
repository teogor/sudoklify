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
    val difficulty: Difficulty,
    val gridSize: Int,
)

val SEEDS: Array<Sudoku> = arrayOf(
    Sudoku(
        puzzle = "G--D--CAF---G----II-F--HG-BB-IAEDHGC--AFCG--D-G-B-----F-D--ABC---B------C--H-BFIA",
        solution = "GBHDIECAFACEGBFDHIIDFCAHGEBBFIAEDHGCEHAFCGIBDDGCBHIAFEFIDEGABCHHABIFCEDGCEGHDBFIA",
        difficulty = Difficulty.EASY,
        gridSize = 9,
    ),
    Sudoku(
        puzzle = "G-HEDCF---I-F--A--E--A-----C--I-DEH-I-------G--G--E---A----F--C-CF-E-GI-B-------E",
        solution = "GAHEDCFBIDICFBGAEHEFBAIHCGDCBAIGDEHFIHEBFADCGFDGHCEIABAEIGHFBDCHCFDEBGIABGDCAIHFE",
        difficulty = Difficulty.MEDIUM,
        gridSize = 9,
    ),
    Sudoku(
        puzzle = "-------HG-----H-D-A-G---EI--CE--DG--DBF---------BFID--HG---F----D--H---C--A-EG---",
        solution = "BEDFIACHGFICEGHBDAAHGDBCEIFICEHADGFBDBFGCEIAHGAHBFIDCEHGBCDFAEIEDIAHBFGCCFAIEGHBD",
        difficulty = Difficulty.HARD,
        gridSize = 9,
    ),
    Sudoku(
        puzzle = "-BI-------C----E---------AF---EBA-----A-I-G------C--I----H-E--D-E------GC-B--F---",
        solution = "FBIAEGDHCACHDFBEGIEDGCHIBAFGICEBAFDHBHAFIDGCEDFEGCHAIBIAFHGECBDHEDBACIFGCGBIDFHEA",
        difficulty = Difficulty.EXPERT,
        gridSize = 9,
    ),
    Sudoku(
        puzzle = "ABCD----CDAB----",
        solution = "ABCDDBACBCADCDAB",
        difficulty = Difficulty.EASY,
        gridSize = 4,
    ),
    Sudoku(
        puzzle = "BADC----CABD----",
        solution = "BACDADBCDBACCDAB",
        difficulty = Difficulty.MEDIUM,
        gridSize = 4,
    ),
    Sudoku(
        puzzle = "CDBA----ABCD----",
        solution = "CDBACDABABCDABCD",
        difficulty = Difficulty.HARD,
        gridSize = 4,
    ),
    Sudoku(
        puzzle = "DCBA----BADC----",
        solution = "DCBAADCBBADCABCD",
        difficulty = Difficulty.EXPERT,
        gridSize = 4,
    ),
)

class SudokuGenerator(
    private val random: Random,
    private val gridSize: Int,
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

        return Sudoku(puzzle, solution, seed.difficulty, gridSize)
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

    private fun getSeed(seeds: Array<Sudoku>, difficulty: Difficulty): Sudoku =
        getRandomItem(getSeedsByDifficulty(getSeedsBySize(seeds, gridSize), difficulty))

    private fun getSeedsByDifficulty(seeds: Array<Sudoku>, difficulty: Difficulty): Array<Sudoku> =
        seeds.filter { seed -> seed.difficulty == difficulty }.toTypedArray()

    private fun getSeedsBySize(seeds: Array<Sudoku>, size: Int): Array<Sudoku> =
        seeds.filter { seed -> seed.gridSize == size }.toTypedArray()

    private fun getRandomItem(items: Array<Sudoku>): Sudoku =
        items[random.nextInt(items.size)]

    @Deprecated("use getTokenMap()")
    private fun getTokenMapLegacy(): TokenMap {
        val tokenList = ('A'..'Z').take(gridSize)
        val shuffledList = tokenList.shuffled(random)
        return shuffledList.withIndex().associate { (index, token) -> token.toString() to (index + 1).toString() }
    }

    fun getTokenMap(): TokenMap {
        val gridList = (1..1000).take(gridSize)
        val tokenList: MutableList<String> = mutableListOf()
        gridList.withIndex().forEachIndexed { index, token ->
            var value = if (index < gridSize) (index + 1) else (index - gridSize + 1)
            val charList = mutableListOf<Char>()
            while (value > 0) {
                val digit = (value % 10)
                val char = if (digit == 0) {
                    ('a' + 9)
                } else {
                    ('a'.code + digit - 1).toChar()
                }
                charList.add(0, char)
                value /= 10
            }

            charList[0] = charList[0].uppercaseChar()
            tokenList.add(charList.joinToString(""))
        }
        val shuffledList = tokenList.shuffled(random)
        val tokenMap = shuffledList.withIndex().associate { (index, token) ->
            val value = if (index < gridSize) (index + 1).toString() else (index - gridSize + 1).toString()
            token to value
        }
        return tokenMap
    }
}
