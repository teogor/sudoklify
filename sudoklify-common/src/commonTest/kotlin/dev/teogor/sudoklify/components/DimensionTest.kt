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

package dev.teogor.sudoklify.components

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@OptIn(ExperimentalSudoklifyApi::class)
class DimensionTest {
  @Test
  fun constructor_shouldInitializePropertiesCorrectlyForSudoku4x4() {
    val dimension = Dimension.FourByFour

    assertEquals(4, dimension.uniqueDigitsCount, "Unique digits count for Sudoku4x4 should be 4")
    assertEquals(16, dimension.totalCells, "Total cells for Sudoku4x4 should be 16")
    assertTrue(dimension.isSquare, "Sudoku4x4 should be a square grid")
    assertEquals(2, dimension.boxWidth, "Box width for Sudoku4x4 should be 2")
    assertEquals(2, dimension.boxHeight, "Box height for Sudoku4x4 should be 2")
    assertEquals("4x4", dimension.name, "Name for Sudoku4x4 should be '4x4'")
  }

  @Test
  fun getAllDigits_shouldReturnCorrectDigitsForSudoku9x9() {
    val dimension = Dimension.NineByNine
    val expectedDigits = (1..9).toList()

    val allDigits = dimension.getAllDigits()

    assertEquals(expectedDigits, allDigits, "All digits for Sudoku9x9 should be from 1 to 9")
  }

  @Test
  fun isDigitValid_shouldReturnTrueForValidDigitsInSudoku6x6() {
    val dimension = Dimension.SixBySix

    assertTrue(dimension.isDigitValid(1), "Digit 1 should be valid in Sudoku6x6")
    assertTrue(dimension.isDigitValid(6), "Digit 6 should be valid in Sudoku6x6")
    assertTrue(!dimension.isDigitValid(7), "Digit 7 should be invalid in Sudoku6x6")
  }

  @Test
  fun getBoxIndex_shouldCalculateCorrectBoxIndexForSudoku9x9() {
    val dimension = Dimension.NineByNine

    assertEquals(0, dimension.getBoxIndex(0, 0), "Box index for cell (0, 0) should be 0")
    assertEquals(0, dimension.getBoxIndex(1, 1), "Box index for cell (1, 1) should be 0")
    assertEquals(1, dimension.getBoxIndex(3, 1), "Box index for cell (3, 1) should be 1")
    assertEquals(4, dimension.getBoxIndex(6, 6), "Box index for cell (6, 6) should be 4")
  }

  @Test
  fun getBoxCoordinates_shouldReturnCorrectCoordinatesForSudoku9x9() {
    val dimension = Dimension.NineByNine

    assertEquals(
      BoxCoordinates(0, 0, 2, 2),
      dimension.getBoxCoordinates(1, 1),
      "Box coordinates for cell (1, 1) should be (0, 0, 2, 2)",
    )
    assertEquals(
      BoxCoordinates(0, 3, 2, 5),
      dimension.getBoxCoordinates(1, 4),
      "Box coordinates for cell (1, 4) should be (0, 3, 2, 5)",
    )
    assertEquals(
      BoxCoordinates(3, 3, 5, 5),
      dimension.getBoxCoordinates(4, 4),
      "Box coordinates for cell (4, 4) should be (3, 3, 5, 5)",
    )
  }

  @Test
  fun getCellRowIndex_shouldCalculateCorrectRowIndexForSudoku16x16() {
    val dimension = Dimension.SixteenBySixteen

    assertEquals(0, dimension.getCellRowIndex(0), "Row index for cell 0 should be 0")
    assertEquals(0, dimension.getCellRowIndex(15), "Row index for cell 15 should be 0")
    assertEquals(1, dimension.getCellRowIndex(16), "Row index for cell 16 should be 1")
    assertEquals(1, dimension.getCellRowIndex(31), "Row index for cell 31 should be 1")
  }

  @Test
  fun getCellColumnIndex_shouldCalculateCorrectColumnIndexForSudoku25x25() {
    val dimension = Dimension.TwentyFiveByTwentyFive

    assertEquals(0, dimension.getCellColumnIndex(0), "Column index for cell 0 should be 0")
    assertEquals(24, dimension.getCellColumnIndex(24), "Column index for cell 24 should be 24")
    assertEquals(0, dimension.getCellColumnIndex(25), "Column index for cell 25 should be 0")
    assertEquals(24, dimension.getCellColumnIndex(49), "Column index for cell 49 should be 24")
  }

  @Test
  fun getCellBoxRowIndex_shouldCalculateCorrectBoxRowIndexForSudoku36x36() {
    val dimension = Dimension.ThirtySixByThirtySix

    assertEquals(0, dimension.getCellBoxRowIndex(0), "Box row index for cell 0 should be 0")
    assertEquals(0, dimension.getCellBoxRowIndex(35), "Box row index for cell 35 should be 0")
    assertEquals(1, dimension.getCellBoxRowIndex(36), "Box row index for cell 36 should be 1")
    assertEquals(1, dimension.getCellBoxRowIndex(71), "Box row index for cell 71 should be 1")
  }

  @Test
  fun getCellBoxColumnIndex_shouldCalculateCorrectBoxColumnIndexForSudoku49x49() {
    val dimension = Dimension.FortyNineByFortyNine

    assertEquals(0, dimension.getCellBoxColumnIndex(0), "Box column index for cell 0 should be 0")
    assertEquals(
      6,
      dimension.getCellBoxColumnIndex(42),
      "Box column index for cell 42 should be 6",
    )
    assertEquals(
      6,
      dimension.getCellBoxColumnIndex(48),
      "Box column index for cell 48 should be 6",
    )
    assertEquals(
      5,
      dimension.getCellBoxColumnIndex(84),
      "Box column index for cell 84 should be 5",
    )
  }

  @Test
  fun areCellsInSameRow_shouldReturnCorrectResult() {
    val dimension = Dimension.EightyOneByEightyOne

    assertTrue(dimension.areCellsInSameRow(0, 8), "Cells (0, 8) should be in the same row")
    assertTrue(!dimension.areCellsInSameRow(0, 81), "Cells (0, 81) should not be in the same row")
  }

  @Test
  fun areCellsInSameColumn_shouldReturnCorrectResult() {
    val dimension = Dimension.EightyOneByEightyOne

    assertTrue(dimension.areCellsInSameColumn(0, 81), "Cells (0, 81) should be in the same column")
    assertTrue(
      !dimension.areCellsInSameColumn(0, 82),
      "Cells (0, 82) should not be in the same column",
    )
  }

  @Test
  fun areCellsInSameBox_shouldReturnCorrectResult() {
    val dimension = Dimension.SixtyFourBySixtyFour

    assertTrue(
      dimension.areCellsInSameBox(0, 0, 3, 3),
      "Cells (0, 0) and (3, 3) should be in the same box",
    )
    assertTrue(
      !dimension.areCellsInSameBox(0, 0, 50, 50),
      "Cells (0, 0) and (50, 50) should not be in the same box",
    )
  }

  @Test
  fun areCellsRelated_shouldReturnCorrectResult() {
    val dimension = Dimension.NineByNine

    assertTrue(dimension.areCellsRelated(0, 0, 0, 3), "Cells (0, 0) and (0, 3) should be related")
    assertTrue(dimension.areCellsRelated(0, 0, 3, 0), "Cells (0, 0) and (3, 0) should be related")
    assertTrue(
      !dimension.areCellsRelated(0, 0, 5, 5),
      "Cells (0, 0) and (5, 5) should not be related",
    )
  }

  @Test
  fun requireValidRowColIndices_shouldThrowExceptionForInvalidIndices() {
    val dimension = Dimension.SixteenBySixteen

    assertFailsWith<InvalidRowOrColumnIndexException>(
      "Invalid indices (16, 0): Invalid row/column indices",
    ) { dimension.requireValidRowColIndices(16, 0) }

    assertFailsWith<InvalidRowOrColumnIndexException>(
      "Invalid indices (0, 16): Invalid row/column indices",
    ) { dimension.requireValidRowColIndices(0, 16) }
  }

  @Test
  fun requireValidCellIndex_shouldThrowExceptionForInvalidIndex() {
    val dimension = Dimension.TwentyFiveByTwentyFive

    assertFailsWith<InvalidCellIndexException>(
      "Invalid index (625): Invalid cell index",
    ) { dimension.requireValidCellIndex(625) }

    assertFailsWith<InvalidCellIndexException>(
      "Invalid index (-1): Invalid cell index",
    ) { dimension.requireValidCellIndex(-1) }
  }

  @Test
  fun fromDigitCount_shouldReturnCorrectDimensionForValidDigitCounts() {
    assertEquals(
      Dimension.FourByFour,
      Dimension.fromDigitCount(4),
      "Expected Dimension.FourByFour for digit count 4",
    )
    assertEquals(
      Dimension.SixBySix,
      Dimension.fromDigitCount(6),
      "Expected Dimension.SixBySix for digit count 6",
    )
    assertEquals(
      Dimension.EightByEight,
      Dimension.fromDigitCount(8),
      "Expected Dimension.EightByEight for digit count 8",
    )
    assertEquals(
      Dimension.NineByNine,
      Dimension.fromDigitCount(9),
      "Expected Dimension.NineByNine for digit count 9",
    )
    assertEquals(
      Dimension.TenByTen,
      Dimension.fromDigitCount(10),
      "Expected Dimension.TenByTen for digit count 10",
    )
    assertEquals(
      Dimension.TwelveByTwelve,
      Dimension.fromDigitCount(12),
      "Expected Dimension.TwelveByTwelve for digit count 12",
    )
    assertEquals(
      Dimension.FifteenByFifteen,
      Dimension.fromDigitCount(15),
      "Expected Dimension.FifteenByFifteen for digit count 15",
    )
    assertEquals(
      Dimension.SixteenBySixteen,
      Dimension.fromDigitCount(16),
      "Expected Dimension.SixteenBySixteen for digit count 16",
    )
    assertEquals(
      Dimension.TwentyFiveByTwentyFive,
      Dimension.fromDigitCount(25),
      "Expected Dimension.TwentyFiveByTwentyFive for digit count 25",
    )
    assertEquals(
      Dimension.ThirtySixByThirtySix,
      Dimension.fromDigitCount(36),
      "Expected Dimension.ThirtySixByThirtySix for digit count 36",
    )
    assertEquals(
      Dimension.FortyNineByFortyNine,
      Dimension.fromDigitCount(49),
      "Expected Dimension.FortyNineByFortyNine for digit count 49",
    )
    assertEquals(
      Dimension.SixtyFourBySixtyFour,
      Dimension.fromDigitCount(64),
      "Expected Dimension.SixtyFourBySixtyFour for digit count 64",
    )
    assertEquals(
      Dimension.EightyOneByEightyOne,
      Dimension.fromDigitCount(81),
      "Expected Dimension.EightyOneByEightyOne for digit count 81",
    )
  }

  @Test
  fun fromDigitCount_shouldThrowExceptionForInvalidDigitCounts() {
    assertFailsWith<InvalidDimensionException> {
      Dimension.fromDigitCount(1)
    }.also {
      assertEquals("No dimension exists for digit count: 1", it.message)
    }

    assertFailsWith<InvalidDimensionException> {
      Dimension.fromDigitCount(2)
    }.also {
      assertEquals("No dimension exists for digit count: 2", it.message)
    }

    assertFailsWith<InvalidDimensionException> {
      Dimension.fromDigitCount(7)
    }.also {
      assertEquals("No dimension exists for digit count: 7", it.message)
    }

    assertFailsWith<InvalidDimensionException> {
      Dimension.fromDigitCount(11)
    }.also {
      assertEquals("No dimension exists for digit count: 11", it.message)
    }

    assertFailsWith<InvalidDimensionException> {
      Dimension.fromDigitCount(50)
    }.also {
      assertEquals("No dimension exists for digit count: 50", it.message)
    }
  }

  @Test
  fun dimension_shouldReturnFourByFourForDigitCount4() {
    val dimension = Dimension(4)
    assertEquals(
      Dimension.FourByFour,
      dimension,
      "Dimension for digit count 4 should be FourByFour",
    )
  }

  @Test
  fun dimension_shouldReturnSixBySixForDigitCount6() {
    val dimension = Dimension(6)
    assertEquals(Dimension.SixBySix, dimension, "Dimension for digit count 6 should be SixBySix")
  }

  @Test
  fun dimension_shouldReturnEightByEightForDigitCount8() {
    val dimension = Dimension(8)
    assertEquals(
      Dimension.EightByEight,
      dimension,
      "Dimension for digit count 8 should be EightByEight",
    )
  }

  @Test
  fun dimension_shouldReturnNineByNineForDigitCount9() {
    val dimension = Dimension(9)
    assertEquals(
      Dimension.NineByNine,
      dimension,
      "Dimension for digit count 9 should be NineByNine",
    )
  }

  @Test
  fun dimension_shouldReturnTenByTenForDigitCount10() {
    val dimension = Dimension(10)
    assertEquals(Dimension.TenByTen, dimension, "Dimension for digit count 10 should be TenByTen")
  }

  @Test
  fun dimension_shouldReturnTwelveByTwelveForDigitCount12() {
    val dimension = Dimension(12)
    assertEquals(
      Dimension.TwelveByTwelve,
      dimension,
      "Dimension for digit count 12 should be TwelveByTwelve",
    )
  }

  @Test
  fun dimension_shouldReturnFifteenByFifteenForDigitCount15() {
    val dimension = Dimension(15)
    assertEquals(
      Dimension.FifteenByFifteen,
      dimension,
      "Dimension for digit count 15 should be FifteenByFifteen",
    )
  }

  @Test
  fun dimension_shouldReturnSixteenBySixteenForDigitCount16() {
    val dimension = Dimension(16)
    assertEquals(
      Dimension.SixteenBySixteen,
      dimension,
      "Dimension for digit count 16 should be SixteenBySixteen",
    )
  }

  @Test
  fun dimension_shouldReturnTwentyFiveByTwentyFiveForDigitCount25() {
    val dimension = Dimension(25)
    assertEquals(
      Dimension.TwentyFiveByTwentyFive,
      dimension,
      "Dimension for digit count 25 should be TwentyFiveByTwentyFive",
    )
  }

  @Test
  fun dimension_shouldReturnThirtySixByThirtySixForDigitCount36() {
    val dimension = Dimension(36)
    assertEquals(
      Dimension.ThirtySixByThirtySix,
      dimension,
      "Dimension for digit count 36 should be ThirtySixByThirtySix",
    )
  }

  @Test
  fun dimension_shouldReturnFortyNineByFortyNineForDigitCount49() {
    val dimension = Dimension(49)
    assertEquals(
      Dimension.FortyNineByFortyNine,
      dimension,
      "Dimension for digit count 49 should be FortyNineByFortyNine",
    )
  }

  @Test
  fun dimension_shouldReturnSixtyFourBySixtyFourForDigitCount64() {
    val dimension = Dimension(64)
    assertEquals(
      Dimension.SixtyFourBySixtyFour,
      dimension,
      "Dimension for digit count 64 should be SixtyFourBySixtyFour",
    )
  }

  @Test
  fun dimension_shouldReturnEightyOneByEightyOneForDigitCount81() {
    val dimension = Dimension(81)
    assertEquals(
      Dimension.EightyOneByEightyOne,
      dimension,
      "Dimension for digit count 81 should be EightyOneByEightyOne",
    )
  }

  @Test
  fun dimension_shouldThrowExceptionForInvalidDigitCount() {
    assertFailsWith<InvalidDimensionException>(
      "Invalid dimension for digit count 7: No dimension exists for digit count: 7",
    ) { Dimension(7) }
  }
}
