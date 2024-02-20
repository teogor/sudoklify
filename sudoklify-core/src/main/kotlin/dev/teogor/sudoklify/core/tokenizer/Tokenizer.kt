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

import dev.teogor.sudoklify.common.types.Board
import dev.teogor.sudoklify.common.types.Layout
import dev.teogor.sudoklify.common.types.TokenMap

// TODO: convert to Sealed Class
open class Tokenizer {
  open fun replaceTokens(
    sequence: String,
    tokenMap: TokenMap,
  ): String {
    return ""
  }

  open fun populateLayout(
    layout: Layout,
    sequence: String,
  ): Board {
    return emptyArray()
  }

  companion object {
    fun create(digits: Int): Tokenizer {
      return if (digits < 10) {
        SingleDigitTokenizer()
      } else {
        MultiDigitTokenizer(digits)
      }
    }
  }
}
