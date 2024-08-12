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

import kotlin.test.Test
import kotlin.test.assertEquals

class BoardCellExtensionsTest {
  @Test
  fun testZeroToBoardCell() {
    val cell = 0.toJEncodedCell()
    assertEquals("-", cell)
  }

  @Test
  fun testSingleDigitToBoardCell() {
    assertEquals("E", 5.toJEncodedCell())
    assertEquals(2, "B".toInt())
  }

  @Test
  fun testDoubleDigitToBoardCell() {
    assertEquals("Bc", 23.toJEncodedCell())
    assertEquals(64, "Fd".toInt())
  }

  @Test
  fun testTripleDigitToBoardCell() {
    assertEquals("Gdc", 743.toJEncodedCell())
    assertEquals(409, "Dji".toInt())
  }

  @Test
  fun testDashToInt() {
    val value = "-".toInt()
    assertEquals(0, value)
  }
}
