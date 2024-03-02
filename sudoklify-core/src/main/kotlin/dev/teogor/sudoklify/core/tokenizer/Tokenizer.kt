/*
 * Copyright 2023 Teogor (Teodor Grigor)
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

package dev.teogor.sudoklify.core.tokenizer

import dev.teogor.sudoklify.common.types.Layout
import dev.teogor.sudoklify.common.types.TokenMap

/**
 * Represents a tokenizer responsible for replacing tokens in sequences and populating
 * layouts.
 *
 * @constructor Creates a Tokenizer instance with the specified number of digits.
 */
sealed class Tokenizer {
  /**
   * Replaces tokens in a given sequence using a token map.
   *
   * @param sequence The input sequence containing tokens.
   * @param tokenMap A map of tokens to their replacement values.
   * @return The sequence with replaced tokens.
   */
  abstract fun replaceTokens(
    sequence: String,
    tokenMap: TokenMap,
  ): String

  /**
   * Populates a layout with values from a sequence, replacing tokens as needed.
   *
   * @param layout The layout to be populated.
   * @param sequence The input sequence containing tokens.
   * @param tokenMap A map of tokens to their replacement values.
   * @return The populated board.
   */
  abstract fun populateLayout(
    layout: Layout,
    sequence: String,
    tokenMap: TokenMap,
  ): Array<Array<String>>

  companion object {
    /**
     * Creates a Tokenizer instance based on the number of digits.
     *
     * @param digits The number of digits used for tokenization.
     * @return A SingleDigitTokenizer for 1-9 digits, or a MultiDigitTokenizer otherwise.
     */
    fun create(digits: Int): Tokenizer {
      return when (digits) {
        in 1..9 -> SingleDigitTokenizer
        else -> MultiDigitTokenizer(digits)
      }
    }
  }

  /**
   * A tokenizer for sequences with single-digit tokens.
   */
  data object SingleDigitTokenizer : Tokenizer() {
    override fun replaceTokens(
      sequence: String,
      tokenMap: TokenMap,
    ): String {
      val result = StringBuilder()
      sequence.forEach { char ->
        result.append(tokenMap[char.toString()] ?: char)
      }
      return result.toString()
    }

    override fun populateLayout(
      layout: Layout,
      sequence: String,
      tokenMap: TokenMap,
    ): Array<Array<String>> {
      with(replaceTokens(sequence, tokenMap)) {
        return layout.map { row ->
          row.map { cell ->
            this[cell].toString()
          }.toTypedArray()
        }.toTypedArray()
      }
    }
  }

  /**
   * A tokenizer for sequences with multi-digit tokens.
   */
  class MultiDigitTokenizer(
    private val digits: Int,
  ) : Tokenizer() {
    override fun replaceTokens(
      sequence: String,
      tokenMap: TokenMap,
    ): String {
      val regex = Regex("([A-I][a-z]+)|-|[A-I][A-I]+")
      return regex.replace(sequence) { matchResult ->
        val token = matchResult.value
        tokenMap[token] ?: token
      }
    }

    override fun populateLayout(
      layout: Layout,
      sequence: String,
      tokenMap: TokenMap,
    ): Array<Array<String>> {
      val tokens = extractTokens(sequence, tokenMap)
      return layout.map { row ->
        row.map { cell ->
          val index = if (cell < digits) cell else cell - digits
          tokens[index]
        }.toTypedArray()
      }.toTypedArray()
    }

    private fun extractTokens(
      sequence: String,
      tokenMap: TokenMap,
    ): List<String> {
      val regex = Regex("([A-I][a-j]+)|-|[A-I]")
      return regex.findAll(sequence).map { match ->
        tokenMap[match.value] ?: match.value
      }.toList()
    }
  }
}
