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

package dev.teogor.sudoklify.tokenizer

import dev.teogor.sudoklify.types.Board
import dev.teogor.sudoklify.types.Layout
import dev.teogor.sudoklify.types.TokenMap

internal class MultiDigitTokenizer(
  private val boxDigits: Int,
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
  ): Board {
    return layout.map { row ->
      row.map { cell ->
        sequence[cell].toString()
      }.toTypedArray()
    }.toTypedArray()
  }

  fun populateLayout(
    layout: Layout,
    sequence: String,
    tokenMap: TokenMap,
  ): Board {
    val regex = Regex("([A-I][a-j]+)|-|[A-I]")
    val elements = mutableListOf<String>()
    regex.findAll(sequence)
      .map { it.value }
      .joinToString("") { token ->
        val e = tokenMap[token] ?: token
        elements.add(e)
        e
      }
    return layout.map { row ->
      row.map { cell ->
        val index = if (cell < boxDigits) cell else cell - boxDigits
        elements[index]
      }.toTypedArray()
    }.toTypedArray()
  }
}
