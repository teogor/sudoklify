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

import dev.teogor.sudoklify.tokenizer.Layout
import dev.teogor.sudoklify.tokenizer.SudokuString
import dev.teogor.sudoklify.tokenizer.TokenMap
import dev.teogor.sudoklify.tokenizer.Tokenizer
import dev.teogor.sudoklify.tokenizer.toJEncodedCell
import kotlin.random.Random

/**
 * Interface for mapping Sudoku layouts to token sequences.
 *
 * This interface provides methods for generating a map of tokens to values and for producing a sequence
 * of tokens based on a Sudoku layout and a seed sequence. Implementations should handle the conversion
 * of Sudoku layouts into a tokenized format suitable for further processing or display.
 *
 * **Example Usage:**
 *
 * ```kotlin
 * val boxDigits = 9
 * val random = Random(1234)
 * val tokenizer: Tokenizer = ... // Your tokenizer implementation
 * val tokenMapper = SudokuTokenMapper.default(boxDigits, random, tokenizer)
 *
 * val tokenMap = tokenMapper.getTokenMap()
 * val sequence = tokenMapper.getSequence(layout, seedSequence, tokenMap)
 * ```
 */
public interface SudokuTokenMapper {
  /**
   * Generates a map of tokens to their corresponding values.
   *
   * This method creates a mapping where each token is associated with a specific value, which can be
   * used to replace tokens in a Sudoku layout with their respective values.
   *
   * @return A [TokenMap] where each token is mapped to its corresponding value.
   */
  public fun getTokenMap(): TokenMap

  /**
   * Produces a sequence of tokens for the given Sudoku layout and seed sequence.
   *
   * This method uses the provided [tokenMap] to convert the layout into a sequence of tokens and then applies
   * the seed sequence to ensure reproducibility or specific tokenization patterns.
   *
   * @param layout The Sudoku layout to be tokenized.
   * @param seedSequence The seed sequence used to generate reproducible token sequences.
   * @param tokenMap The map of tokens to values used for tokenization.
   * @return A 2D array of tokens representing the tokenized Sudoku layout.
   */
  public fun getSequence(
    layout: Layout,
    seedSequence: SudokuString,
    tokenMap: TokenMap,
  ): Array<Array<String>>

  public companion object {
    /**
     * Provides a default implementation of [SudokuTokenMapper].
     *
     * This implementation includes functionality to generate a token map based on the size of the Sudoku grid
     * and to produce a tokenized sequence of the Sudoku layout using a [Tokenizer].
     *
     * @param boxDigits The size of the Sudoku grid (e.g., 9 for a 9x9 Sudoku).
     * @param random The random number generator used for shuffling tokens.
     * @param tokenizer The [Tokenizer] used to populate the Sudoku layout with tokens.
     * @return A [SudokuTokenMapper] instance with the default implementation.
     */
    public fun default(
      boxDigits: Int,
      random: Random,
      tokenizer: Tokenizer,
    ): SudokuTokenMapper {
      return object : SudokuTokenMapper {
        override fun getTokenMap(): TokenMap {
          val gridList = (1..boxDigits)
          val tokenList =
            gridList.withIndex().map { (index, _) ->
              val value = if (index < boxDigits) (index + 1) else (index - boxDigits + 1)
              value.toJEncodedCell()
            }.shuffled(random)

          return tokenList.withIndex().associate { (index, token) ->
            val value =
              if (index < boxDigits) {
                (index + 1).toString()
              } else {
                (index - boxDigits + 1).toString()
              }
            token to value
          }
        }

        override fun getSequence(
          layout: Layout,
          seedSequence: SudokuString,
          tokenMap: TokenMap,
        ): Array<Array<String>> {
          return tokenizer.populateLayout(layout, seedSequence.value, tokenMap)
        }
      }
    }
  }
}
