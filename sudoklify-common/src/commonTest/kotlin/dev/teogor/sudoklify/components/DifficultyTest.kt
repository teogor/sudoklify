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

class DifficultyTest {
  @Test
  fun toStars_shouldConvertDifficultyToStarsCorrectly() {
    assertEquals("★", Difficulty.VERY_EASY.toStars(), "VERY_EASY should map to one star")
    assertEquals("★★★★", Difficulty.HARD.toStars(), "HARD should map to four stars")
    assertEquals("★★★★★", Difficulty.VERY_HARD.toStars(), "VERY_HARD should map to five stars")
  }

  @Test
  fun toLabel_shouldUseCorrectLabelFromProvidedArray() {
    val difficultyLabels = arrayOf("Beginner", "Intermediate", "Advanced", "Expert", "Master")
    assertEquals(
      "Beginner",
      Difficulty.VERY_EASY.toLabel(difficultyLabels),
      "VERY_EASY should map to Beginner",
    )
    assertEquals(
      "Advanced",
      Difficulty.MEDIUM.toLabel(difficultyLabels),
      "MEDIUM should map to Advanced",
    )
    assertEquals(
      "Master",
      Difficulty.VERY_HARD.toLabel(difficultyLabels),
      "VERY_HARD should map to Master",
    )

    // Test with an invalid label array size
    val exception =
      assertFailsWith<DifficultyLabelOutOfBoundsException> {
        Difficulty.EASY.toLabel(arrayOf("Easy"))
      }
    assertEquals(
      "Difficulty index 1 is out of bounds for labels array of size 1.",
      exception.message,
    )
  }

  @Test
  fun ordinal_shouldReflectEnumOrder() {
    assertEquals(0, Difficulty.VERY_EASY.ordinal, "VERY_EASY should have ordinal 0")
    assertEquals(2, Difficulty.MEDIUM.ordinal, "MEDIUM should have ordinal 2")
    assertEquals(4, Difficulty.VERY_HARD.ordinal, "VERY_HARD should have ordinal 4")
  }
}
