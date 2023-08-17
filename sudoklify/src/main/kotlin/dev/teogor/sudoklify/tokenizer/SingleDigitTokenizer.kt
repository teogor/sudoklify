package dev.teogor.sudoklify.tokenizer

import dev.teogor.sudoklify.types.Board
import dev.teogor.sudoklify.types.Layout
import dev.teogor.sudoklify.types.TokenMap

internal class SingleDigitTokenizer : Tokenizer() {
  override fun replaceTokens(sequence: String, tokenMap: TokenMap): String {
    return sequence
      .split("").joinToString("") { token ->
        tokenMap[token] ?: token
      }
  }

  override fun populateLayout(layout: Layout, sequence: String): Board {
    return layout.map { row ->
      row.map { cell ->
        sequence[cell].toString()
      }.toTypedArray()
    }.toTypedArray()
  }
}
