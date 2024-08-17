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
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class BoxCoordinatesTest {
  @Test
  fun constructor_shouldSetPropertiesCorrectly() {
    val box = BoxCoordinates(1, 2, 3, 4)
    assertEquals(1, box.topLeftRow, "topLeftRow should be set correctly")
    assertEquals(2, box.topLeftCol, "topLeftCol should be set correctly")
    assertEquals(3, box.bottomRightRow, "bottomRightRow should be set correctly")
    assertEquals(4, box.bottomRightCol, "bottomRightCol should be set correctly")
  }

  @Test
  fun width_shouldCalculateCorrectlyForStandardBox() {
    val box = BoxCoordinates(0, 0, 2, 2)
    assertEquals(3, box.width, "Width should be calculated correctly for a standard 3x3 box")
  }

  @Test
  fun width_shouldHandleSingleColumnBox() {
    val box = BoxCoordinates(0, 0, 2, 0)
    assertEquals(1, box.width, "Width should be 1 for a single-column box")
  }

  @Test
  fun height_shouldCalculateCorrectlyForStandardBox() {
    val box = BoxCoordinates(0, 0, 2, 2)
    assertEquals(3, box.height, "Height should be calculated correctly for a standard 3x3 box")
  }

  @Test
  fun height_shouldHandleSingleRowBox() {
    val box = BoxCoordinates(0, 0, 0, 2)
    assertEquals(1, box.height, "Height should be 1 for a single-row box")
  }

  @Test
  fun width_shouldCalculateCorrectly() {
    val box = BoxCoordinates(1, 2, 3, 4)
    assertEquals(3, box.width, "width should be calculated correctly")
  }

  @Test
  fun height_shouldCalculateCorrectly() {
    val box = BoxCoordinates(1, 2, 3, 4)
    assertEquals(3, box.height, "height should be calculated correctly")
  }

  @Test
  fun topLeft_shouldReturnCorrectPair() {
    val box = BoxCoordinates(1, 2, 3, 4)
    assertEquals(Pair(1, 2), box.topLeft, "topLeft should return the correct pair")
  }

  @Test
  fun bottomRight_shouldCalculateCorrectly() {
    val box = BoxCoordinates(1, 2, 3, 4)
    assertEquals(Pair(5, 6), box.bottomRight, "bottomRight should consider width and height")
  }

  @Test
  fun equality_shouldMatchEquivalentBoxes() {
    val box1 = BoxCoordinates(1, 2, 3, 4)
    val box2 = BoxCoordinates(1, 2, 3, 4)
    val box3 = BoxCoordinates(2, 3, 4, 5)

    assertEquals(box1, box2, "Equivalent boxes should be equal")
    assertNotEquals(box1, box3, "Boxes with different coordinates should not be equal")
  }

  @Test
  fun contains_shouldIdentifyCellsWithinBox() {
    val box = BoxCoordinates(2, 3, 4, 5)
    assertTrue(box.contains(3, 4), "Cell within box should be identified")
    assertFalse(box.contains(1, 2), "Cell outside box should not be identified")
  }

  @Test
  fun expand_shouldIncreaseDimensions() {
    val box = BoxCoordinates(1, 2, 3, 4)
    val expandedBox = box.expand(1, 2)
    assertEquals(BoxCoordinates(1, 2, 4, 6), expandedBox, "Box should be expanded correctly")
  }

  @Test
  fun toFormattedString_shouldProvideHumanReadableFormat() {
    val box = BoxCoordinates(2, 3, 4, 5)
    val expectedString =
      buildString {
        append("Box Coordinates:\n")
        append("  Top-Left: (2, 3)\n")
        append("  Bottom-Right: (4, 5)\n")
        append("  Width: 3\n")
        append("  Height: 3")
      }
    assertEquals(expectedString, box.toFormattedString(), "Formatted string should be correct")
  }

  @Test
  fun intersect_shouldFindCommonAreaForOverlappingBoxes() {
    val box1 = BoxCoordinates(1, 2, 3, 4)
    val box2 = BoxCoordinates(2, 3, 4, 5)
    val intersection = box1.intersect(box2)
    assertEquals(BoxCoordinates(2, 3, 3, 4), intersection, "Intersection should be the common area")
  }
}
