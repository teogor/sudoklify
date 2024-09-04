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

import kotlin.jvm.JvmInline

/**
 * A value class representing a J-Encoded string for Sudoku cells.
 * The J-Encoded string uses letters 'a' to 'j', where 'j' represents 0.
 *
 * Example:
 * ```kotlin
 * val encodedCell = JEncodedCell("E")  // Represents 5
 * val intValue = encodedCell.toInt()   // Converts back to integer value
 * ```
 */
@JvmInline
public value class JEncodedCell(public val value: String) {
  /**
   * Converts this [JEncodedCell] to its corresponding integer value.
   *
   * @return The integer value represented by the [JEncodedCell], using base-10 encoding.
   */
  public fun toInt(): Int {
    return when {
      value == "-" -> 0

      else ->
        value.map { char ->
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

  override fun toString(): String = value

  public companion object {
    public val regex: Regex = """([A-I][a-z]+)|-|[A-I]""".toRegex()

    /**
     * Validates if a given string contains only valid JEncodedCells.
     *
     * @param input The string to validate.
     * @return `true` if the string is valid, `false` otherwise.
     */
    public fun isValid(input: String): Boolean {
      val matches = regex.findAll(input)
      // Join all the matched parts and compare it with the original string
      // This ensures that the entire string is composed of valid components
      val matchedString = matches.joinToString("") { it.value }
      return matchedString == input
    }

    /**
     * Extracts individual JEncodedCells from a string.
     *
     * @param input The string representing the Sudoku board.
     * @return A list of [JEncodedCell] extracted from the input string.
     */
    public fun extractCells(input: String): List<JEncodedCell> {
      return regex.findAll(input).map { JEncodedCell(it.value) }.toList()
    }

    /**
     * Finds and returns the invalid components in the given string.
     *
     * @param input The string to check.
     * @return A list of invalid components found in the string.
     */
    public fun findInvalidComponents(input: String): List<String> {
      val validMatches = regex.findAll(input).map { it.range }.toList()
      val invalidComponents = mutableListOf<String>()

      // Identify gaps between valid matches, which correspond to invalid components.
      var lastEndIndex = 0
      for (range in validMatches) {
        if (range.first > lastEndIndex) {
          // Extract the invalid part between the previous valid end and the current valid start
          invalidComponents.add(input.substring(lastEndIndex, range.first))
        }
        lastEndIndex = range.last + 1
      }

      // Check if there's any invalid component after the last valid match
      if (lastEndIndex < input.length) {
        invalidComponents.add(input.substring(lastEndIndex))
      }

      return invalidComponents
    }
  }
}

/**
 * Converts an integer representing a Sudoku cell value to its J-Encoded string representation.
 *
 * Example:
 * ```kotlin
 * 5.toJEncodedCell() // Returns JEncodedCell("E")
 * 10.toJEncodedCell() // Returns JEncodedCell("Aj")
 * ```
 *
 * @receiver The integer representing the Sudoku cell value.
 *
 * @return The [JEncodedCell] string representation of the cell, using letters 'a' to 'j'
 * (where 'j' is used for 0), and capitalizing the first letter.
 */
public fun Int.toJEncodedCell(): JEncodedCell {
  val encodedString =
    when {
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

  return JEncodedCell(encodedString)
}

/**
 * Converts a string representation to a [JEncodedCell].
 *
 * This function wraps a string into a [JEncodedCell], which is a value class used for representing
 * Sudoku cell values in their J-Encoded string format. The conversion is straightforward as it
 * simply creates an instance of [JEncodedCell] with the provided string.
 *
 * **Example:**
 *
 * ```kotlin
 * val cellString = "A" // Represents a J-Encoded cell value
 *
 * // Convert the string to a JEncodedCell
 * val encodedCell: JEncodedCell = cellString.toJEncodedCell()
 * ```
 *
 * @receiver The string representation of the Sudoku cell.
 * @return A [JEncodedCell] instance initialized with the string.
 */
public fun String.toJEncodedCell(): JEncodedCell {
  return JEncodedCell(this)
}
