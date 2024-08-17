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

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SudokuLayoutManipulatorTest {
  private val boxDigits = 9
  private val random = Random(1234)
  private val manipulator = SudokuLayoutManipulator.default(boxDigits, random)

  @Test
  fun constructBase_withValidBoxDigits_shouldReturnCorrectBaseLayout() {
    val baseLayout = manipulator.constructBase()

    val expectedLayout =
      Array(boxDigits) { i ->
        IntArray(boxDigits) { j -> i * boxDigits + j }
      }

    expectedLayout.forEachIndexed { index, ints ->
      assertContentEquals(
        ints,
        baseLayout[index],
        "Base layout should match the expected initial configuration.",
      )
    }
  }

  @Test
  fun shuffle_withBaseLayout_shouldReturnDifferentLayout() {
    val baseLayout = manipulator.constructBase()
    val shuffledLayout = manipulator.shuffle(baseLayout)

    assertNotEquals(baseLayout, shuffledLayout, "Shuffling should alter the layout.")
  }

  @Test
  fun rotate_withBaseLayout_shouldReturnValidRotatedLayout() {
    val baseLayout = manipulator.constructBase()
    val rotatedLayouts =
      listOf(
        manipulator.rotate(baseLayout),
        manipulator.rotate(baseLayout),
        manipulator.rotate(baseLayout),
        manipulator.rotate(baseLayout),
      )

    assertNotNull(rotatedLayouts[0], "Rotated layout should not be null.")
    assertNotNull(rotatedLayouts[1], "Rotated layout should not be null.")
    assertNotNull(rotatedLayouts[2], "Rotated layout should not be null.")
    assertNotNull(rotatedLayouts[3], "Rotated layout should not be null.")

    assertNotEquals(
      rotatedLayouts[0],
      rotatedLayouts[1],
      "Rotation should produce different layouts.",
    )
    assertNotEquals(
      rotatedLayouts[1],
      rotatedLayouts[2],
      "Rotation should produce different layouts.",
    )
    assertNotEquals(
      rotatedLayouts[2],
      rotatedLayouts[3],
      "Rotation should produce different layouts.",
    )
  }

  @Test
  fun shuffleAndRotate_withBaseLayout_shouldReturnValidLayout() {
    val baseLayout = manipulator.constructBase()
    val shuffledLayout = manipulator.shuffle(baseLayout)
    val rotatedShuffledLayout = manipulator.rotate(shuffledLayout)

    assertNotNull(rotatedShuffledLayout, "Rotated shuffled layout should not be null.")
    assertNotEquals(
      baseLayout,
      rotatedShuffledLayout,
      "Shuffling and rotating should produce a new layout.",
    )
  }

  @Test
  fun shuffleAndRotate_shouldProduceUniqueLayouts() {
    val layout1 = manipulator.shuffle(manipulator.constructBase())
    val layout2 = manipulator.rotate(manipulator.rotate(layout1))
    val layout3 = manipulator.shuffle(layout2)

    assertNotEquals(layout1, layout2, "Shuffling and rotating should produce unique layouts.")
    assertNotEquals(layout2, layout3, "Shuffling and rotating should produce unique layouts.")
    assertNotEquals(layout1, layout3, "Shuffling and rotating should produce unique layouts.")
  }
}
