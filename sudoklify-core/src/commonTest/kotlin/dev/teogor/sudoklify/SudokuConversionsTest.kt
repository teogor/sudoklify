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

package dev.teogor.sudoklify

import dev.teogor.sudoklify.components.Dimension
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

@OptIn(ExperimentalSudoklifyApi::class)
class SudokuConversionsTest {
  @Test
  fun mapIterableToSudokuString_shouldProduceExpectedResult() {
    val board: Iterable<Iterable<Int>> =
      listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
      )
    val expected = "ABCDEFGHI"
    val result = board.mapToSudokuString()
    assertEquals(
      expected,
      result,
      "mapToSudokuString with Iterable<Int> did not produce the correct result",
    )
  }

  @Test
  fun mapIterableWithMapperToSudokuString_shouldProduceExpectedResult() {
    data class Cell(val number: Int)
    val board: Iterable<Iterable<Cell>> =
      listOf(
        listOf(Cell(1), Cell(2), Cell(3)),
        listOf(Cell(4), Cell(5), Cell(6)),
        listOf(Cell(7), Cell(8), Cell(9)),
      )
    val expected = "ABCDEFGHI"
    val result = board.mapToSudokuString { number }
    assertEquals(
      expected,
      result,
      "mapToSudokuString with custom mapper did not produce the correct result",
    )
  }

  @Test
  fun mapSudokuStringToBoard_shouldProduceExpectedBoard() {
    val sudokuString = "ABCDDCBAABCDBADC"
    val expected =
      listOf(
        listOf(1, 2, 3, 4),
        listOf(4, 3, 2, 1),
        listOf(1, 2, 3, 4),
        listOf(2, 1, 4, 3),
      )
    val result = sudokuString.mapToSudokuBoard(Dimension.FourByFour)
    assertContentEquals(expected, result, "mapToSudokuBoard did not produce the correct board")
  }

  @Test
  fun mapSudokuStringWithMapperToBoard_shouldProduceExpectedBoard() {
    val sudokuString = "ABCDDCBAABCDBADC"
    val expected =
      listOf(
        listOf("1", "2", "3", "4"),
        listOf("4", "3", "2", "1"),
        listOf("1", "2", "3", "4"),
        listOf("2", "1", "4", "3"),
      )
    val result = sudokuString.mapToSudokuBoard(Dimension.FourByFour) { toString() }
    assertContentEquals(
      expected,
      result,
      "mapToSudokuBoard with custom mapper did not produce the correct board",
    )
  }

  @Test
  fun mapIndexedSudokuStringToBoard_shouldProduceExpectedResult() {
    val sudokuString = "ABCDDCBAABCDBADC"
    val expected =
      listOf(
        listOf("1,0,0", "2,0,1", "3,0,2", "4,0,3"),
        listOf("4,1,0", "3,1,1", "2,1,2", "1,1,3"),
        listOf("1,2,0", "2,2,1", "3,2,2", "4,2,3"),
        listOf("2,3,0", "1,3,1", "4,3,2", "3,3,3"),
      )
    val result =
      sudokuString.mapIndexedToSudokuBoard(Dimension.FourByFour) { value, row, col ->
        "$value,$row,$col"
      }
    assertContentEquals(
      expected,
      result,
      "mapIndexedToSudokuBoard did not produce the correct result with index-based mapping",
    )
  }

  @Test
  fun mapEmptyIterableToSudokuString_shouldProduceEmptyString() {
    val board: Iterable<Iterable<Int>> = emptyList()
    val expected = ""
    val result = board.mapToSudokuString()
    assertEquals(
      expected,
      result,
      "mapToSudokuString with empty Iterable did not produce the correct result",
    )
  }
}
