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
import kotlin.test.assertFailsWith

class SudokuStringTest {
  @Test
  fun emptySudokuString_shouldThrowException() {
    val exception =
      assertFailsWith<EmptySudokuStringException> {
        SudokuString("")
      }
    assertEquals("Value must not be empty.", exception.message)
  }

  @Test
  fun invalidSudokuString_shouldThrowException() {
    val exception =
      assertFailsWith<InvalidSudokuValueException> {
        SudokuString("ABCDXy-9w")
      }
    assertEquals("Value contains invalid values: [Xy, 9w]", exception.message)
  }

  @Test
  fun toString_shouldReturnUnderlyingString() {
    val string = "ABCAiA".toSudokuString()
    assertEquals(
      "ABCAiA",
      string.toString(),
      "toString() should return the underlying string value",
    )
  }
}
