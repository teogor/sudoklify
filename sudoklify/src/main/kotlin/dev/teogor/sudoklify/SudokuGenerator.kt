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

package dev.teogor.sudoklify

import kotlin.math.sqrt
import kotlin.random.Random

typealias Cell = String
typealias SudokuString = String
typealias PuzzleString = SudokuString
typealias SolutionString = SudokuString
typealias Token = String
typealias Board = Array<Array<Cell>>
typealias Layout = Array<IntArray>
typealias TokenMap = Map<Token, Cell>

enum class Type(val rows: Int, val cols: Int) {
  TWO_BY_TWO(2, 2),
  TWO_BY_THREE(2, 3),
  TWO_BY_FOUR(2, 4),
  THREE_BY_THREE(3, 3),
  TWO_BY_FIVE(2, 5),
  THREE_BY_FOUR(3, 4),
  THREE_BY_FIVE(3, 5),
  FOUR_BY_FOUR(4, 4),
  FIVE_BY_FIVE(5, 5),
  SIX_BY_SIX(6, 6),
  SEVEN_BY_SEVEN(7, 7),
  EIGHT_BY_EIGHT(8, 8),
  NINE_BY_NINE(9, 9),
}

enum class Difficulty {
  EASY,
  MEDIUM,
  HARD,
  EXPERT,
}

data class Sudoku(
  val puzzle: PuzzleString,
  val solution: SolutionString,
  val difficulty: Difficulty,
  val type: Type,
)

class SudokuGenerator(
  private val random: Random,
  private val type: Type,
) {
  companion object {
    fun getSudoku(difficulty: Difficulty, seed: Long, type: Type): Sudoku {
      val sudokuGenerator = SudokuGenerator(
        random = Random(seed),
        type = type,
      )
      return sudokuGenerator.getSudoku(difficulty)
    }
  }

  private val gridSize = type.rows * type.cols

  internal open class Tokenizer {
    open fun replaceTokens(sequence: String, tokenMap: TokenMap): String {
      return ""
    }

    open fun populateLayout(layout: Layout, sequence: String): Board {
      return emptyArray()
    }
  }

  private val gridArea = gridSize * gridSize
  private val baseLayout: Layout = generateBaseLayout()
  private val tokenizer: Tokenizer = getTokenizerImpl()

  private fun getTokenizerImpl(): Tokenizer = if (gridSize < 10) {
    SingleDigitTokenizer()
  } else {
    MultiDigitTokenizer()
  }

  private class SingleDigitTokenizer : Tokenizer() {
    override fun replaceTokens(sequence: String, tokenMap: TokenMap): String {
      return sequence
        .split("").joinToString("") { token ->
          tokenMap[token] ?: token
        }
    }

    override fun populateLayout(layout: Layout, sequence: String): Board {
      return layout.map { row ->
        row.map { cell ->
          sequence[cell].toString()
        }.toTypedArray()
      }.toTypedArray()
    }
  }

  private class MultiDigitTokenizer : Tokenizer() {
    override fun replaceTokens(sequence: String, tokenMap: TokenMap): String {
      val regex = Regex("([A-I][a-z]+)|-|[A-I]")
      return regex.findAll(sequence)
        .map { it.value }
        .joinToString("") { token ->
          tokenMap[token] ?: token
        }
    }

    override fun populateLayout(layout: Layout, sequence: String): Board {
      return layout.map { row ->
        row.map { cell ->
          sequence[cell].toString()
        }.toTypedArray()
      }.toTypedArray()
    }

    fun populateLayout(layout: Layout, sequence: String, tokenMap: TokenMap): Board {
      val regex = Regex("([A-I][a-z]+)|-|[A-I]")
      val elements = mutableListOf<String>()
      regex.findAll(sequence)
        .map { it.value }
        .joinToString("") { token ->
          val e = tokenMap[token] ?: token
          elements.add(e)
          e
        }
      return layout.map { row ->
        row.map { cell ->
          elements[cell]
        }.toTypedArray()
      }.toTypedArray()
    }
  }

  private fun generateBaseLayout(): Layout {
    return Array(gridSize) { i ->
      IntArray(gridSize) { j -> i * gridSize + j }
    }
  }

  private fun getSudoku(difficulty: Difficulty): Sudoku {
    val seed = getSeed(SEEDS, difficulty)
    val layout = getLayout(baseLayout)
    val tokenMap = getTokenMap()

    val puzzle = getSequence(layout, seed.puzzle, tokenMap)
    val solution = getSequence(layout, seed.solution, tokenMap)

    return Sudoku(puzzle, solution, seed.difficulty, type)
  }

  private fun boardToSequence(board: Board): String = board.joinToString("") {
    it.joinToString("")
  }

  private fun <T> getRandomItem(items: List<T>): T = items.random(random)

  private fun getSequence(layout: Layout, seedSequence: String, tokenMap: TokenMap): String {
    val populatedLayout = if (tokenizer is MultiDigitTokenizer) {
      tokenizer.populateLayout(layout, seedSequence, tokenMap)
    } else {
      tokenizer.populateLayout(layout, tokenizer.replaceTokens(seedSequence, tokenMap))
    }
    return boardToSequence(populatedLayout)
  }

  private fun sequenceToBoard(sequence: String): Board {
    return sequence.chunked(gridSize)
      .map { chunk -> chunk.map { it.toString() }.toTypedArray() }
      .toTypedArray()
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

  private fun rotateLayout(layout: Layout): Layout =
    getRandomItem(listOf(::rotateLayout0, ::rotateLayout90, ::rotateLayout180, ::rotateLayout270))(
      layout,
    )

  private fun rotateLayout0(layout: Layout): Layout = layout

  private fun rotateLayout90(layout: Layout): Layout =
    layout[0].mapIndexed { index, _ -> layout.map { row -> row[index] }.reversed().toIntArray() }
      .toTypedArray()

  private fun rotateLayout180(layout: Layout): Layout =
    layout.map { row -> row.reversed().toIntArray() }.reversed().toTypedArray()

  private fun rotateLayout270(layout: Layout): Layout =
    layout[0].mapIndexed { index, _ -> layout.map { row -> row.reversed()[index] }.toIntArray() }
      .toTypedArray()

  private fun shuffleLayout(layout: Layout): Layout =
    shuffleLayoutColumns(shuffleLayoutRows(shuffleLayoutStacks(shuffleLayoutBands(layout))))

  private fun shuffleLayoutBands(layout: Layout): Layout =
    getLayoutBands(layout).sortedWith(compareBy { sortRandom() }).flatMap { it.toList() }
      .toTypedArray()

  private fun shuffleLayoutColumns(layout: Layout): Layout =
    rotateLayout270(shuffleLayoutRows(rotateLayout90(layout)))

  private fun shuffleLayoutRows(layout: Layout): Layout =
    getLayoutBands(layout).map { rows -> rows.sortedWith(compareBy { sortRandom() }) }
      .flatMap { it.toList() }
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
    seeds.filter { seed -> seed.type.cols * seed.type.rows == size }.toTypedArray()

  private fun getRandomItem(items: Array<Sudoku>): Sudoku =
    items[random.nextInt(items.size)]

  private fun getTokenMap(): TokenMap {
    val gridList = (1..gridSize)
    val tokenList = gridList.withIndex().map { (index, _) ->
      val value = if (index < gridSize) (index + 1) else (index - gridSize + 1)
      numberToToken(value)
    }.shuffled(random)

    val tokenMap = tokenList.withIndex().associate { (index, token) ->
      val value =
        if (index < gridSize) (index + 1).toString() else (index - gridSize + 1).toString()
      token to value
    }
    return tokenMap
  }

  private fun numberToToken(value: Int): Token {
    var valueCopy = value
    val charList = mutableListOf<Char>()
    while (valueCopy > 0) {
      val digit = (valueCopy % 10)
      val char = if (digit == 0) {
        ('a' + 9)
      } else {
        ('a'.code + digit - 1).toChar()
      }
      charList.add(0, char)
      valueCopy /= 10
    }

    charList[0] = charList[0].uppercaseChar()
    return charList.joinToString("")
  }
}
