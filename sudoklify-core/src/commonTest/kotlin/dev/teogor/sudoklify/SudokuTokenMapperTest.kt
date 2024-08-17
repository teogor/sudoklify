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

import dev.teogor.sudoklify.tokenizer.SudokuString
import dev.teogor.sudoklify.tokenizer.Tokenizer
import dev.teogor.sudoklify.tokenizer.toJEncodedCell
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SudokuTokenMapperTest {
  private val boxDigits = 9
  private val random = Random(1234)
  private val tokenizer =
    Tokenizer.create(
      digits = boxDigits,
    )
  private val tokenMapper = SudokuTokenMapper.default(boxDigits, random, tokenizer)

  @Test
  fun getTokenMap_withValidParameters_shouldGenerateValidTokenMap() {
    val tokenMap = tokenMapper.getTokenMap()
    assertNotNull(tokenMap, "Token map should not be null.")
    assertTrue(tokenMap.isNotEmpty(), "Token map should not be empty.")

    // Validate that the token map has the correct number of tokens
    val expectedTokenCount = boxDigits
    assertEquals(
      expectedTokenCount,
      tokenMap.size,
      "Token map should have $expectedTokenCount entries.",
    )

    // Validate that each token maps to a value and vice versa
    tokenMap.forEach { (token, value) ->
      assertTrue(token.value.isNotEmpty(), "Token should not be empty.")
      assertTrue(value.isNotEmpty(), "Value should not be empty.")
    }
  }

  @Test
  fun getSequence_withValidParameters_shouldGenerateValidTokenSequence() {
    val layout =
      Array(boxDigits) { i ->
        IntArray(boxDigits) { j -> i * boxDigits + j }
      }
    val seedSequence =
      SudokuString(
        "CADHGBEIFGIHDFECABFEBCIAGHDBGIACDFEHHCAEBFIDGEDFIHGABCAFEBDCHGIIBCGAHDFEDHGFEIBCA",
      )
    val tokenMap = tokenMapper.getTokenMap()

    val sequence = tokenMapper.getSequence(layout, seedSequence, tokenMap)
    assertNotNull(sequence, "Token sequence should not be null.")
    assertTrue(sequence.isNotEmpty(), "Token sequence should not be empty.")
    assertEquals(boxDigits, sequence.size, "Token sequence should have $boxDigits rows.")
    assertEquals(
      boxDigits,
      sequence[0].size,
      "Each row in the token sequence should have $boxDigits columns.",
    )

    // Validate that all values in the sequence are tokens
    sequence.forEach { row ->
      row.forEach { cell ->
        val cellValue = cell.toInt().toJEncodedCell()
        assertTrue(tokenMap.containsKey(cellValue), "Cell value '$cell' should be a valid token.")
      }
    }
  }

  @Test
  fun getSequence_withDifferentSeeds_shouldProduceDifferentSequences() {
    val layout =
      Array(boxDigits) { i ->
        IntArray(boxDigits) { j -> i * boxDigits + j }
      }
    val seedSequence1 =
      SudokuString(
        "CADHGBEIFGIHDFECABFEBCIAGHDBGIACDFEHHCAEBFIDGEDFIHGABCAFEBDCHGIIBCGAHDFEDHGFEIBCA",
      )
    val seedSequence2 =
      SudokuString(
        "HCAFGEBDIFIEABDHGCGBDCIHAEFCEBDFIGHAIGFBHADCEDAHGECFIBBDCIAGEFHAHIEDFCBGEFGHCBIAD",
      )
    val tokenMap = tokenMapper.getTokenMap()

    val sequence1 = tokenMapper.getSequence(layout, seedSequence1, tokenMap)
    val sequence2 = tokenMapper.getSequence(layout, seedSequence2, tokenMap)

    assertNotNull(sequence1, "First token sequence should not be null.")
    assertNotNull(sequence2, "Second token sequence should not be null.")
    assertNotEquals(sequence1, sequence2, "Sequences should differ with different seeds.")
  }

  @Test
  fun getSequence_withSameSeed_shouldProduceConsistentOutput() {
    val layout =
      Array(boxDigits) { i ->
        IntArray(boxDigits) { j -> i * boxDigits + j }
      }
    val seedSequence =
      SudokuString(
        "DHIAGECFBBGCHDFIEAAEFICBHDGIABGEHDCFFCHBADGIEEDGCFIBAHGFDEBCAHIHBEDIAFGCCIAFHGEBD",
      )
    val tokenMap = tokenMapper.getTokenMap()

    val sequence1 = tokenMapper.getSequence(layout, seedSequence, tokenMap)
    val sequence2 = tokenMapper.getSequence(layout, seedSequence, tokenMap)

    assertNotNull(sequence1, "First token sequence should not be null.")
    assertNotNull(sequence2, "Second token sequence should not be null.")
    sequence1.forEachIndexed { index, ints ->
      assertContentEquals(
        ints,
        sequence2[index],
        "Sequences should be the same with the same seed.",
      )
    }
  }
}
