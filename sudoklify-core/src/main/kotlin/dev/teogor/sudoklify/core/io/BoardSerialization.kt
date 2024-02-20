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

package dev.teogor.sudoklify.core.io

import dev.teogor.sudoklify.common.types.GameType
import dev.teogor.sudoklify.common.types.Token
import dev.teogor.sudoklify.common.types.TokenMap

/**
 * Converts an integer value to a base-36 string representation for use in
 * game board encoding.
 *
 * - Handles the special case of 0 being represented as "-".
 * - Uses nested `when` expressions for efficient character construction.
 *
 * @receiver The integer value to be converted.
 * @return The string representation of the value.
 */
fun Int.toToken(): Token =
  when {
    this == 0 -> "-"

    else -> {
      var valueCopy = this
      buildString {
        while (valueCopy > 0) {
          val char =
            when (val digit = (valueCopy % 10)) {
              0 -> 'j'
              else -> ('a' + digit - 1)
            }
          append(char)
          valueCopy /= 10
        }
        reverse()
        this[0] = this[0].uppercaseChar()
      }
    }
  }

/**
 * Converts a string representation back to an integer value.
 *
 * - Handles the special case of "-" being represented as 0.
 * - Uses `map` and `fold` with nested `when` expressions for efficient conversion.
 *
 * @receiver The string representation to be converted.
 * @return The integer value represented by the token.
 */
fun Token.toNumber(): Int =
  when {
    this == "-" -> 0

    else ->
      map { char ->
        when {
          char.isUpperCase() -> {
            char - 'A' + 1
          }

          else -> {
            char - 'a' + 1
          }
        }
      }.fold(0) { acc, digit -> acc * 10 + digit }
  }

/**
 * Serializes a list of lists (representing a game board) as a base-36 string.
 *
 * - Uses the provided valueMapper function to convert individual cell values
 * to integers before encoding.
 *
 * @receiver The grid of values to be serialized.
 * @param valueMapper A function to map each cell value to its corresponding
 * integer for encoding.
 * @return The base-36 string representation of the board.
 */
inline fun <T> List<List<T>>.encodeAsString(crossinline valueMapper: T.() -> Int) =
  flatMap { cells ->
    cells.map { cell ->
      valueMapper(cell).toToken()
    }
  }.joinToString("")

/**
 * Deserializes a base-36 string into a list of lists (representing a game board).
 *
 * - Uses the provided gameType to validate the board size.
 * - Uses the provided valueMapper function to convert decoded integers back to
 * cell values.
 *
 * @receiver The string representation of the grid.
 * @param gameType The game type, providing information about the expected board
 * size.
 * @param valueMapper A function to map each decoded integer to its corresponding
 * cell value.
 * @return The decoded board as a list of lists.
 */
inline fun <T> String.decodeAsBoard(
  gameType: GameType,
  crossinline valueMapper: Int.() -> T,
): List<List<T>> {
  val regex = Regex("([A-I][a-z]+)|-|[A-I]")
  val matches = regex.findAll(this)
  val matchedTokens = ArrayList<String>()
  matches.forEach { matchedTokens.add(it.value) }
  return matchedTokens
    .chunked(gameType.cells)
    .map { row -> row.map { valueMapper(it.toNumber()) } }
}

/**
 * Generates a mapping between token values and their corresponding
 * string representations.
 *
 * TODO annotation for internal use
 *
 * @param boxDigits The number of digits used to represent each box
 * in the Sudoku puzzle.
 *
 * @return A `TokenMap` containing the mapping between token values
 * and their string representations.
 */
fun generateTokenMap(boxDigits: Int): TokenMap {
  val gridList = (1..boxDigits)
  val tokenList =
    gridList.withIndex().map { (index, _) ->
      val value = if (index < boxDigits) (index + 1) else (index - boxDigits + 1)
      value.toToken()
    }

  val tokenMap =
    tokenList.withIndex().associate { (index, token) ->
      val value =
        if (index < boxDigits) (index + 1).toString() else (index - boxDigits + 1).toString()
      token to value
    }
  return tokenMap
}
