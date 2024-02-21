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

import dev.teogor.sudoklify.common.types.JEncodedCell

/**
 * Converts an integer representing a Sudoku cell value to its J-Encoded string representation.
 *
 * @receiver The integer representing the Sudoku cell value.
 * @return The [JEncodedCell] string representation of the cell, using letters 'a' to 'j'
 * (where 'j' is used for 0), and capitalizing the first letter.
 *
 * Example:
 * ```kotlin
 * 5.toJEncodedCell() // Returns "E"
 * 10.toJEncodedCell() // Returns "Aj"
 * ```
 */
fun Int.toJEncodedCell(): JEncodedCell {
  return when {
    this == 0 -> "-"

    this in 1..9 -> ('a' + this - 1).uppercase()

    else -> {
      var valueCopy = this
      buildString {
        while (valueCopy > 0) {
          val digit = valueCopy % 10
          append(
            if (digit != 0) {
              ('a' + digit - 1)
            } else {
              'j'
            },
          )
          valueCopy /= 10
        }
        reverse()
        this[0] = this[0].uppercaseChar()
      }
    }
  }
}

/**
 * Converts a string representation of a Sudoku cell ([JEncodedCell], [String]) to its
 * corresponding integer value.
 *
 * @receiver The string representation of the Sudoku cell.
 * @return The integer value represented by the [JEncodedCell], using the base-10 encoding with
 * letters 'a' to 'j' (where 'j' represents 0).
 *
 * Example:
 * ```kotlin
 * "E".toInt() // Returns 5
 * "Aj".toInt() // Returns 10
 * ```
 */
fun JEncodedCell.toInt(): Int {
  return when {
    this == "-" -> 0

    else ->
      map { char ->
        when {
          char == 'j' || char == 'J' -> {
            0
          }

          char.isUpperCase() -> {
            char - 'A' + 1
          }

          else -> {
            char - 'a' + 1
          }
        }
      }.fold(0) { acc, digit -> acc * 10 + digit }
  }
}
