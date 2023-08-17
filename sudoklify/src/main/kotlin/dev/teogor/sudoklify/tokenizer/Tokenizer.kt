package dev.teogor.sudoklify.tokenizer

import dev.teogor.sudoklify.types.Board
import dev.teogor.sudoklify.types.Layout
import dev.teogor.sudoklify.types.TokenMap

internal open class Tokenizer {
  open fun replaceTokens(sequence: String, tokenMap: TokenMap): String {
    return ""
  }

  open fun populateLayout(layout: Layout, sequence: String): Board {
    return emptyArray()
  }
}
