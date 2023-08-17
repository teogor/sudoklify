package dev.teogor.sudoklify.tokenizer

internal val Int.tokenizer: Tokenizer
  get() = if (this < 10) {
    SingleDigitTokenizer()
  } else {
    MultiDigitTokenizer(this)
  }
