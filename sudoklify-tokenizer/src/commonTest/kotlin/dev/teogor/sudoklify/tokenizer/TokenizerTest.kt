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

class TokenizerTest {
  private val singleDigitTokenizer = Tokenizer.create(9) as Tokenizer.SingleDigitTokenizer
  private val multiDigitTokenizer = Tokenizer.create(16) as Tokenizer.MultiDigitTokenizer

  private fun getTokenMap(boxDigits: Int): TokenMap {
    val gridList = (1..boxDigits)
    val tokenList =
      gridList.withIndex().map { (index, _) ->
        val value = if (index < boxDigits) (index + 1) else (index - boxDigits + 1)
        value.toJEncodedCell()
      }

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

  private val singleDigitTokenMap: TokenMap = getTokenMap(9)
  private val multiDigitTokenMap: TokenMap = getTokenMap(16)

  // @Test
  // fun singleDigitTokenizer_replaceTokens_shouldReplaceTokensCorrectly() {
  //   val sequence = "123456789"
  //   val expected = "OneTwoThreeFourFiveSixSevenEightNine"
  //
  //   val result = singleDigitTokenizer.replaceTokens(sequence, singleDigitTokenMap)
  //
  //   assertEquals(expected, result, "Tokens should be replaced correctly in the sequence.")
  // }
  //
  // @Test
  // fun singleDigitTokenizer_populateLayout_shouldPopulateLayoutCorrectly() {
  //   val boxDigits = 9
  //   val layout =
  //     Array(boxDigits) { i ->
  //       IntArray(boxDigits) { j -> i * boxDigits + j }
  //     }
  //   val sequence = "123"
  //   val expected =
  //     arrayOf(
  //       arrayOf("One", "Two", "Three"),
  //       arrayOf("One", "Two", "Three"),
  //       arrayOf("One", "Two", "Three"),
  //     )
  //
  //   val result = singleDigitTokenizer.populateLayout(layout, sequence, singleDigitTokenMap)
  //
  //   assertNotNull(result, "Populated layout should not be null.")
  //   assertContentEquals(expected, result, "Layout should be populated correctly.")
  // }
  //
  // @Test
  // fun multiDigitTokenizer_replaceTokens_shouldReplaceTokensCorrectly() {
  //   val sequence = "AdAAaAfBFAcEHDAbGAjIAeCAeFHAjAbADCAdBIAcAfAaGEIAcAbEGAdAeHAaAjCAfAFBDDCGBIAjAfAaFAEAeAcAdHAbAcAbCAaFAeEIDGAjAHBAfAdAfBAjDAdAcHGAbAeFEICAAaEIAeACAfAbBAcAaAdHDGAjFHGAdFDAaAjACIAfBAbAeEAcAjAeAAcHCFAdGAfAaAbEDIBBAdAfIAeGAaAbEHDAjCAcFAAbAaECAjBADAeFAcIGAfAdHGDFHAfEIAcAAdBCAaAjAbAeFAjBAbEDAdAfIAcHAaAeACGAaHAcGAAbBAjAfCAeAdFEDICEDAdAaIGAeAjAbAFBHAcAfAAfIAeAcHCFBEGDAdAbAaAj"
  //   val expected = "Alpha Bravo Charlie Ten Eleven"
  //
  //   val result = multiDigitTokenizer.replaceTokens(sequence, multiDigitTokenMap)
  //
  //   assertEquals(expected, result, "Tokens should be replaced correctly in the sequence.")
  // }
  //
  // @Test
  // fun multiDigitTokenizer_populateLayout_shouldPopulateLayoutCorrectly() {
  //   val boxDigits = 16
  //   val layout =
  //     Array(boxDigits) { i ->
  //       IntArray(boxDigits) { j -> i * boxDigits + j }
  //     }
  //   val sequence = "A B 10"
  //   val expected =
  //     arrayOf(
  //       arrayOf("Alpha", "Bravo", "Ten"),
  //       arrayOf("Alpha", "Bravo", "Ten"),
  //       arrayOf("Alpha", "Bravo", "Ten"),
  //     )
  //
  //   val result = multiDigitTokenizer.populateLayout(layout, sequence, multiDigitTokenMap)
  //
  //   assertNotNull(result, "Populated layout should not be null.")
  //   assertContentEquals(expected, result, "Layout should be populated correctly.")
  // }
  //
  // @Test
  // fun multiDigitTokenizer_extractTokens_shouldExtractTokensCorrectly() {
  //   val sequence = "A B C 10 11"
  //   val expectedTokens = listOf("Alpha", "Bravo", "Charlie", "Ten", "Eleven")
  //
  //   val result = multiDigitTokenizer.extractTokens(sequence, multiDigitTokenMap)
  //
  //   assertContentEquals(expectedTokens, result, "Tokens should be extracted correctly.")
  // }
}
