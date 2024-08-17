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

import dev.teogor.sudoklify.random.randomItem
import dev.teogor.sudoklify.random.randomOrderFactor
import dev.teogor.sudoklify.tokenizer.Layout
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * Interface for manipulating Sudoku layouts.
 *
 * This interface defines methods for creating a base Sudoku layout, shuffling it, and rotating it.
 * Implementations of this interface are expected to handle the generation of Sudoku grid layouts and
 * apply transformations to modify them. The companion object provides a default implementation with
 * basic shuffling and rotation capabilities.
 *
 * **Example Usage:**
 *
 * ```kotlin
 * val boxDigits = 9
 * val random = Random(1234)
 * val manipulator = SudokuLayoutManipulator.default(boxDigits, random)
 *
 * val baseLayout = manipulator.constructBase()
 * val shuffledLayout = manipulator.shuffle(baseLayout)
 * val rotatedLayout = manipulator.rotate(shuffledLayout)
 * ```
 */
interface SudokuLayoutManipulator {
  /**
   * Constructs the base Sudoku layout based on the specified size.
   *
   * This method creates a standard Sudoku grid where each cell contains a value from 0 to `boxDigits - 1`,
   * representing the initial state of the Sudoku puzzle.
   *
   * @return A 2D array representing the base Sudoku layout.
   */
  fun constructBase(): Layout

  /**
   * Shuffles the given Sudoku layout by applying a series of transformations.
   *
   * This method applies random shuffling to rows, columns, and bands of the layout to generate a new
   * layout configuration.
   *
   * @param layout The Sudoku layout to be shuffled.
   * @return A new Sudoku layout with applied shuffling.
   */
  fun shuffle(layout: Layout): Layout

  /**
   * Rotates the given Sudoku layout by a random multiple of 90 degrees.
   *
   * This method randomly selects one of four possible rotations (0°, 90°, 180°, or 270°) and applies it
   * to the layout.
   *
   * @param layout The Sudoku layout to be rotated.
   * @return The rotated Sudoku layout.
   */
  fun rotate(layout: Layout): Layout

  companion object {
    /**
     * Provides a default implementation of [SudokuLayoutManipulator].
     *
     * This implementation includes basic functionality for constructing a base layout, shuffling,
     * and rotating the layout. The shuffling involves randomizing rows, columns, and bands, and rotation
     * includes all four possible angles (0°, 90°, 180°, and 270°).
     *
     * @param boxDigits The size of the Sudoku grid (e.g., 9 for a 9x9 Sudoku).
     * @param random The random number generator used for shuffling and rotation.
     * @return A [SudokuLayoutManipulator] instance with the default implementation.
     */
    fun default(
      boxDigits: Int,
      random: Random,
    ): SudokuLayoutManipulator {
      return object : SudokuLayoutManipulator {
        override fun constructBase(): Layout {
          return Array(boxDigits) { i ->
            IntArray(boxDigits) { j -> i * boxDigits + j }
          }
        }

        override fun shuffle(layout: Layout): Layout {
          return shuffleLayoutColumns(
            shuffleLayoutRows(shuffleLayoutStacks(shuffleLayoutBands(layout))),
          )
        }

        private fun shuffleLayoutBands(layout: Layout): Layout =
          getLayoutBands(layout).sortedWith(compareBy { random.randomOrderFactor() })
            .flatMap { it.toList() }
            .toTypedArray()

        private fun shuffleLayoutColumns(layout: Layout): Layout =
          rotateLayout270(shuffleLayoutRows(rotateLayout90(layout)))

        private fun shuffleLayoutRows(layout: Layout): Layout =
          getLayoutBands(layout).map { rows ->
            rows.sortedWith(compareBy { random.randomOrderFactor() })
          }
            .flatMap { it.toList() }
            .toTypedArray()

        private fun shuffleLayoutStacks(layout: Layout): Layout =
          rotateLayout270(shuffleLayoutBands(rotateLayout90(layout)))

        private fun rotateLayout0(layout: Layout): Layout = layout

        private fun rotateLayout90(layout: Layout): Layout =
          layout[0].mapIndexed { index, _ ->
            layout.map { row -> row[index] }.reversed().toIntArray()
          }
            .toTypedArray()

        private fun rotateLayout180(layout: Layout): Layout =
          layout.map { row -> row.reversed().toIntArray() }.reversed().toTypedArray()

        private fun rotateLayout270(layout: Layout): Layout =
          layout[0].mapIndexed { index, _ ->
            layout.map { row -> row.reversed()[index] }.toIntArray()
          }
            .toTypedArray()

        override fun rotate(layout: Layout): Layout {
          return getRandomLayout(
            listOf(
              ::rotateLayout0,
              ::rotateLayout90,
              ::rotateLayout180,
              ::rotateLayout270,
            ),
          )(
            layout,
          )
        }

        private fun getRandomLayout(items: List<(Layout) -> Layout>): (Layout) -> Layout =
          items.randomItem(random)

        private fun getLayoutBands(layout: Layout): Array<Array<IntArray>> {
          val bandSize = sqrt(boxDigits.toDouble()).toInt()
          val bands = mutableListOf<Array<IntArray>>()
          for (i in 0 until boxDigits step bandSize) {
            val band = layout.slice(i until i + bandSize).toTypedArray()
            bands.add(band)
          }
          return bands.toTypedArray()
        }
      }
    }
  }
}
