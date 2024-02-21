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

import dev.teogor.sudoklify.common.InternalSudoklifyApi
import dev.teogor.sudoklify.common.types.TokenMap
import dev.teogor.sudoklify.ktx.toJEncodedCell

/**
 * Generates a mapping between token values and their corresponding
 * string representations.
 *
 * @param boxDigits The number of digits used to represent each box
 * in the Sudoku puzzle.
 *
 * @return A `TokenMap` containing the mapping between token values
 * and their string representations.
 */
@InternalSudoklifyApi
fun generateTokenMap(boxDigits: Int): TokenMap {
  val gridList = (1..boxDigits)
  val tokenList =
    gridList.withIndex().map { (index, _) ->
      val value = if (index < boxDigits) (index + 1) else (index - boxDigits + 1)
      value.toJEncodedCell()
    }

  val tokenMap =
    tokenList.withIndex().associate { (index, token) ->
      val value =
        if (index < boxDigits) (index + 1).toString() else (index - boxDigits + 1).toString()
      token to value
    }
  return tokenMap
}
