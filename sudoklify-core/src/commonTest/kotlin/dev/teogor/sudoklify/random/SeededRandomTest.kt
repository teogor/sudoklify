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

package dev.teogor.sudoklify.random

import dev.teogor.sudoklify.components.Seed
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SeededRandomTest {
  @Test
  fun constructor_shouldInitializeRandomWithSeed() {
    val seed = Seed.Explicit(12345L)
    val seededRandom = SeededRandom(seed)
    val random = Random(seed.value)

    repeat(10) {
      assertEquals(seededRandom.nextInt(), random.nextInt())
      assertEquals(seededRandom.nextDouble(), random.nextDouble())
      assertEquals(seededRandom.nextBoolean(), random.nextBoolean())
    }
  }

  @Test
  fun nextBits_shouldReturnCorrectNumberOfBits() {
    val seed = Seed.Explicit(54321L)
    val seededRandom = SeededRandom(seed)
    val bits = seededRandom.nextBits(8)
    assertTrue(bits in 0..255, "Bits should be in the range of 0 to 255 for 8 bits")
  }

  @Test
  fun nextInt_withRange_shouldReturnValueInRange() {
    val seed = Seed.Explicit(67890L)
    val seededRandom = SeededRandom(seed)
    val value = seededRandom.nextInt(10, 20)
    assertTrue(value in 10 until 20, "Value should be in the range 10 until 20")
  }

  @Test
  fun nextLong_withRange_shouldReturnValueInRange() {
    val seed = Seed.Explicit(98765L)
    val seededRandom = SeededRandom(seed)
    val value = seededRandom.nextLong(100L, 200L)
    assertTrue(value in 100L until 200L, "Value should be in the range 100L until 200L")
  }

  @Test
  fun nextDouble_withRange_shouldReturnValueInRange() {
    val seed = Seed.Explicit(11223L)
    val seededRandom = SeededRandom(seed)
    val value = seededRandom.nextDouble(0.0, 1.0)
    assertTrue(value in 0.0..1.0, "Value should be in the range 0.0 to 1.0")
  }

  @Test
  fun nextBytes_withSize_shouldReturnCorrectByteArray() {
    val seed = Seed.Explicit(33445L)
    val seededRandom = SeededRandom(seed)
    val byteArray = seededRandom.nextBytes(10)
    assertEquals(10, byteArray.size, "Byte array size should be 10")
  }

  @Test
  fun nextBytes_withArray_shouldFillArray() {
    val seed = Seed.Explicit(55667L)
    val seededRandom = SeededRandom(seed)
    val byteArray = ByteArray(5)
    seededRandom.nextBytes(byteArray)
    assertTrue(byteArray.isNotEmpty(), "Byte array should be filled with random bytes")
  }

  @Test
  fun randomOrderFactor_shouldReturnOneOrNegativeOne() {
    val random = Random(12345L)
    val factors = List(1000) { random.randomOrderFactor() }
    assertTrue(
      factors.all { it == 1 || it == -1 },
      "randomOrderFactor should return either 1 or -1",
    )
  }

  @Test
  fun iterableRandomItem_shouldReturnItemFromIterable() {
    val items = listOf("apple", "banana", "cherry")
    val random = Random(23456L)
    val selectedItems = List(100) { items.randomItem(random) }
    assertTrue(
      selectedItems.all { it in items },
      "Selected items should be from the original iterable",
    )
  }

  @Test
  fun arrayRandomItem_shouldReturnItemFromArray() {
    val items = arrayOf("apple", "banana", "cherry")
    val random = Random(34567L)
    val selectedItems = List(100) { items.randomItem(random) }
    assertTrue(
      selectedItems.all { it in items },
      "Selected items should be from the original array",
    )
  }
}
