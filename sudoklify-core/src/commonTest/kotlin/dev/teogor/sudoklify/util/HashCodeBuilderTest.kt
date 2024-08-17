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

import kotlin.test.Test
import kotlin.test.assertEquals

class HashCodeBuilderTest {
  @Test
  fun initialHashCode_shouldBe31() {
    val builder =
      HashCodeBuilder()
        .build()
    assertEquals(31, builder)
  }

  @Test
  fun appendNullValue_shouldResultInInitialHashCode() {
    val hashCodeActual =
      buildHashCode {
        append(null)
      }
    val hashCodeExpected = 31
    assertEquals(
      expected = hashCodeExpected,
      actual = hashCodeActual,
      message = "Null value should result in initial hash code (31)",
    )
  }

  @Test
  fun appendSingleValue_shouldCalculateHashCodeCorrectly() {
    val hashCodeActual =
      buildHashCode {
        append("test")
      }
    val hashCodeExpected = 31 * 31 + "test".hashCode()
    assertEquals(
      expected = hashCodeExpected,
      actual = hashCodeActual,
      message = "Hash code should be calculated using initial value and string's hash code",
    )
  }

  @Test
  fun appendMultipleValues_shouldCalculateHashCodeCorrectly() {
    val hashCodeActual =
      buildHashCode {
        append("test")
        append(123)
        append(45.67)
      }
    val hashCodeExpected =
      (
        (31 * 31 + "test".hashCode()) * 31 + 123.hashCode()
      ) * 31 + 45.67.hashCode()
    assertEquals(
      expected = hashCodeExpected,
      actual = hashCodeActual,
    )
  }

  @Test
  fun appendSameValueMultipleTimes_shouldCalculateHashCodeCorrectly() {
    val hashCodeActual =
      buildHashCode {
        append("same")
        append("same")
        append("same")
      }
    val hashCodeExpected =
      (
        (31 * 31 + "same".hashCode()) * 31 + "same".hashCode()
      ) * 31 + "same".hashCode()
    assertEquals(
      expected = hashCodeExpected,
      actual = hashCodeActual,
    )
  }

  @Test
  fun hashCodeConsistency_shouldBeMaintained() {
    val builder =
      HashCodeBuilder()
        .append("consistent")
        .append("value")
    val hashCode1 = builder.build()
    val hashCode2 = builder.build()
    assertEquals(hashCode1, hashCode2)
  }
}
