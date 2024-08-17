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

package dev.teogor.sudoklify.tokenizer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

// @OptIn(InternalSudoklifyApi::class)
class JEncodedCellTest {
  @Test
  fun testToInt_whenSingleDigit_thenCorrectInteger() {
    val encodedCell = JEncodedCell("E")
    val intValue = encodedCell.toInt()
    assertEquals(5, intValue, "Expected 5 but got $intValue")
  }

  @Test
  fun testToInt_whenMultiDigit_thenCorrectInteger() {
    val encodedCell = JEncodedCell("Aj")
    val intValue = encodedCell.toInt()
    assertEquals(10, intValue, "Expected 10 but got $intValue")
  }

  @Test
  fun testToInt_whenZero_thenCorrectInteger() {
    val encodedCell = JEncodedCell("-")
    val intValue = encodedCell.toInt()
    assertEquals(0, intValue, "Expected 0 but got $intValue")
  }

  @Test
  fun testToJEncodedCell_whenSingleDigit_thenCorrectEncodedCell() {
    val intValue = 5
    val encodedCell = intValue.toJEncodedCell()
    assertEquals(JEncodedCell("E"), encodedCell, "Expected 'E' but got ${encodedCell.value}")
  }

  @Test
  fun testToJEncodedCell_whenMultiDigit_thenCorrectEncodedCell() {
    val intValue = 10
    val encodedCell = intValue.toJEncodedCell()
    assertEquals(JEncodedCell("Aj"), encodedCell, "Expected 'Aj' but got ${encodedCell.value}")
  }

  @Test
  fun testToJEncodedCell_whenZero_thenCorrectEncodedCell() {
    val intValue = 0
    val encodedCell = intValue.toJEncodedCell()
    assertEquals(JEncodedCell("-"), encodedCell, "Expected '-' but got ${encodedCell.value}")
  }

  @Test
  fun testJEncodedCell_whenEqualValues_thenEqual() {
    val encodedCell1 = JEncodedCell("E")
    val encodedCell2 = JEncodedCell("E")
    val encodedCell3 = JEncodedCell("Aj")
    assertEquals(encodedCell1, encodedCell2, "Expected $encodedCell1 to equal $encodedCell2")
    assertNotEquals(encodedCell1, encodedCell3, "Expected $encodedCell1 to not equal $encodedCell3")
  }

  @Test
  fun testJEncodedCell_whenDifferentValues_thenNotEqual() {
    val encodedCell = JEncodedCell("E")
    assertEquals("E", encodedCell.toString(), "Expected 'E' but got $encodedCell")
  }
}
