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

import dev.teogor.sudoklify.exntensions.sortRandom
import dev.teogor.sudoklify.exntensions.toBoard
import dev.teogor.sudoklify.exntensions.toSequenceString
import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.Sudoku
import dev.teogor.sudoklify.model.SudokuBlueprint
import dev.teogor.sudoklify.model.Type
import dev.teogor.sudoklify.tokenizer.MultiDigitTokenizer
import dev.teogor.sudoklify.tokenizer.Tokenizer
import dev.teogor.sudoklify.tokenizer.tokenizer
import dev.teogor.sudoklify.types.Board
import dev.teogor.sudoklify.types.Layout
import dev.teogor.sudoklify.types.SudokuString
import dev.teogor.sudoklify.types.TokenMap
import dev.teogor.sudoklify.types.toToken
import kotlin.math.sqrt
import kotlin.random.Random

internal class SudokuGenerator internal constructor(
  private val random: Random,
  private val type: Type,
  private val difficulty: Difficulty,
) {

  private val boxDigits = type.rows * type.cols
  private val totalDigits = boxDigits * boxDigits
  private val baseLayout: Layout = generateBaseLayout()
  private val tokenizer: Tokenizer = boxDigits.tokenizer

  private fun generateBaseLayout(): Layout {
    return Array(boxDigits) { i ->
      IntArray(boxDigits) { j -> i * boxDigits + j }
    }
  }

  internal fun composeSudokuPuzzle(): Sudoku {
    val seed = getSeed(SEEDS, difficulty)
    val layout = getLayout(baseLayout)
    val tokenMap = getTokenMap()

    val puzzle = getSequence(layout, seed.puzzle.toSequenceString(), tokenMap)
    val solution = getSequence(layout, seed.solution.toSequenceString(), tokenMap)

    return Sudoku(puzzle, solution, seed.difficulty, type)
  }

  private fun getSequence(layout: Layout, seedSequence: String, tokenMap: TokenMap): Board {
    val grid = if (tokenizer is MultiDigitTokenizer) {
      tokenizer.populateLayout(layout, seedSequence, tokenMap)
    } else {
      tokenizer.populateLayout(layout, tokenizer.replaceTokens(seedSequence, tokenMap))
    }
    return grid
  }

  @Deprecated(message = "toSequenceString")
  private fun boardToSequence(board: Board): SudokuString = board.joinToString("") {
    it.joinToString("")
  }

  @Deprecated(message = "toBoard")
  private fun sequenceToBoard(sequence: SudokuString): Board {
    return sequence.chunked(boxDigits)
      .map { chunk -> chunk.map { it.toString() }.toTypedArray() }
      .toTypedArray()
  }

  private fun getLayout(baseLayout: Layout): Layout = shuffleLayout(rotateLayout(baseLayout))

  private fun getLayoutBands(layout: Layout): Array<Array<IntArray>> {
    val bandSize = sqrt(boxDigits.toDouble()).toInt()
    val bands = mutableListOf<Array<IntArray>>()
    for (i in 0 until boxDigits step bandSize) {
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
    getLayoutBands(layout).sortedWith(compareBy { random.sortRandom() }).flatMap { it.toList() }
      .toTypedArray()

  private fun shuffleLayoutColumns(layout: Layout): Layout =
    rotateLayout270(shuffleLayoutRows(rotateLayout90(layout)))

  private fun shuffleLayoutRows(layout: Layout): Layout =
    getLayoutBands(layout).map { rows -> rows.sortedWith(compareBy { random.sortRandom() }) }
      .flatMap { it.toList() }
      .toTypedArray()

  private fun shuffleLayoutStacks(layout: Layout): Layout =
    rotateLayout270(shuffleLayoutBands(rotateLayout90(layout)))

  private fun getRandomItem(items: List<(Layout) -> Layout>): (Layout) -> Layout =
    items.shuffled(random).first()

  private fun getSeed(seeds: Array<SudokuBlueprint>, difficulty: Difficulty): Sudoku {
    val randomItem = getRandomItem(getSeedsByDifficulty(getSeedsBySize(seeds, boxDigits), difficulty))
    return Sudoku(
      puzzle = randomItem.puzzle.toBoard(boxDigits),
      solution = randomItem.solution.toBoard(boxDigits),
      difficulty = randomItem.difficulty,
      type = randomItem.type,
    )
  }

  private fun getSeedsByDifficulty(seeds: Array<SudokuBlueprint>, difficulty: Difficulty): Array<SudokuBlueprint> =
    seeds.filter { seed -> seed.difficulty == difficulty }.toTypedArray()

  private fun getSeedsBySize(seeds: Array<SudokuBlueprint>, size: Int): Array<SudokuBlueprint> =
    seeds.filter { seed -> seed.type.cols * seed.type.rows == size }.toTypedArray()

  private fun getRandomItem(items: Array<SudokuBlueprint>): SudokuBlueprint =
    items[random.nextInt(items.size)]

  private fun getTokenMap(): TokenMap {
    val gridList = (1..boxDigits)
    val tokenList = gridList.withIndex().map { (index, _) ->
      val value = if (index < boxDigits) (index + 1) else (index - boxDigits + 1)
      value.toToken()
    }.shuffled(random)

    val tokenMap = tokenList.withIndex().associate { (index, token) ->
      val value =
        if (index < boxDigits) (index + 1).toString() else (index - boxDigits + 1).toString()
      token to value
    }
    return tokenMap
  }
}
