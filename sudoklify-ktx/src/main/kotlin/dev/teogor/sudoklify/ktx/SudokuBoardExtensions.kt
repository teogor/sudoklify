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

package dev.teogor.sudoklify.ktx

import dev.teogor.sudoklify.common.InternalSudoklifyApi
import dev.teogor.sudoklify.common.types.SudokuType

inline fun <T> List<List<T>>.mapToSudokuString(crossinline valueMapper: T.() -> Int): String {
  return flatMap { cells ->
    cells.map { cell ->
      valueMapper(cell).toBoardCell()
    }
  }.joinToString("")
}

@OptIn(InternalSudoklifyApi::class)
inline fun <T> String.mapToSudokuBoard(
  sudokuType: SudokuType,
  crossinline valueMapper: Int.() -> T,
): List<List<T>> {
  return getCells()
    .chunked(sudokuType.cells)
    .map { row -> row.map { valueMapper(it.toInt()) } }
}

@OptIn(InternalSudoklifyApi::class)
inline fun <T> String.mapIndexedToSudokuBoard(
  sudokuType: SudokuType,
  crossinline valueMapper: (value: Int, row: Int, column: Int) -> T,
): List<List<T>> {
  return getCells()
    .chunked(sudokuType.cells)
    .mapIndexed { row, rowElements ->
      rowElements.mapIndexed { column, value ->
        valueMapper(value.toInt(), row, column)
      }
    }
}

@InternalSudoklifyApi
fun String.getCells(): ArrayList<String> {
  val regex = Regex("([A-I][a-z]+)|-|[A-I]")
  val matches = regex.findAll(this)
  val matchedTokens = ArrayList<String>()
  matches.forEach { matchedTokens.add(it.value) }
  return matchedTokens
}
