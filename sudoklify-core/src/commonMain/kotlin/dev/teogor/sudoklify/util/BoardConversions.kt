/*
 * Copyright 2024 Teogor (Teodor Grigor)
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

package dev.teogor.sudoklify.util

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.tokenizer.SudokuString

/**
 * Converts a [SudokuString] to a list of lists of strings, representing
 * the Sudoku puzzle in a more granular format.
 *
 * @param dimension The sudoku type, which determines the size of the Sudoku
 * puzzle.
 *
 * @return A list of lists of strings representing the Sudoku puzzle.
 */
@ExperimentalSudoklifyApi
fun String.toBoard(dimension: Dimension): Array<Array<String>> {
  val regex = Regex("([A-I][a-z]+)|-|[A-I]")
  val matches = regex.findAll(this)
  val matchedTokens = ArrayList<String>()
  matches.forEach { matchedTokens.add(it.value) }
  return matchedTokens
    .chunked(dimension.uniqueDigitsCount)
    .map { row -> row.map { it }.toTypedArray() }
    .toTypedArray()
}
