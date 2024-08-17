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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class SeedTest {
  @Test
  fun explicitSeed_withPositiveValue_shouldInitializeCorrectly() {
    val seed = Seed.Explicit(12345L)
    assertEquals(12345L, seed.value, "Explicit seed value should match the provided value")
  }

  @Test
  fun explicitSeed_withNegativeValue_shouldThrowException() {
    assertFailsWith<InvalidSeedException> {
      Seed.Explicit(-1L)
    }
  }

  @Test
  fun randomSeed_value_shouldBeNonNegative() {
    val seed = Seed.Random()
    assertTrue(seed.value >= 0, "Random seed value should be non-negative")
  }

  @Test
  fun explicitSeed_nextSeed_shouldIncrementValue() {
    val explicitSeed = Seed.Explicit(12345L)
    val nextSeed = explicitSeed.nextSeed()
    assertEquals(
      Seed.Explicit(12346L),
      nextSeed,
      "Next seed should be incremented for Explicit seed",
    )
  }

  @Test
  fun randomSeed_nextSeed_shouldRemainRandom() {
    val randomSeed = Seed.Random()
    val nextSeed = randomSeed.nextSeed()
    assertEquals(Seed.Random(), nextSeed, "Next seed for Random should remain Random")
  }

  @Test
  fun explicitSeed_toString_shouldMatchFormat() {
    val explicitSeed = Seed.Explicit(12345L)
    assertEquals(
      "ExplicitSeed(12345)",
      explicitSeed.toString(),
      "Explicit seed string representation should match format",
    )
  }

  @Test
  fun randomSeed_toString_shouldStartWithRandomSeedPrefix() {
    val randomSeed = Seed.Random()
    assertTrue(
      randomSeed.toString().startsWith("RandomSeed("),
      "Random seed string representation should start with 'RandomSeed('",
    )
  }

  @Test
  fun seed_copy_shouldBeEqualToOriginal() {
    val explicitSeed = Seed.Explicit(12345L)
    val copiedSeed = explicitSeed.copy()
    assertEquals(explicitSeed, copiedSeed, "Copied seed should be equal to original")
  }

  @Test
  fun seed_copy_withDifferentValue_shouldNotBeEqual() {
    val explicitSeed = Seed.Explicit(12345L)
    val copiedSeed = explicitSeed.copy()
    val copiedSeedWithValue = explicitSeed.copy(seed = 98765L)
    assertNotEquals(
      explicitSeed,
      copiedSeedWithValue,
      "Copied seed with different value should not be equal",
    )
  }

  @Test
  fun seed_comparison_shouldReflectCorrectOrderAndEquality() {
    val seed1 = Seed.Explicit(10L)
    val seed2 = Seed.Explicit(20L)
    val randomSeed = Seed.Random()

    assertTrue(seed1 < seed2, "Seed1 should be less than seed2")
    assertTrue(seed2 > seed1, "Seed2 should be greater than seed1")
    assertEquals(0, randomSeed.compareTo(randomSeed), "Random seed should be equal to itself")
    assertTrue(seed1 != randomSeed, "Explicit seed should not be equal to Random seed")
  }
}
