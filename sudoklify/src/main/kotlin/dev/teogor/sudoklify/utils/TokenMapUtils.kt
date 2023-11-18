package dev.teogor.sudoklify.utils

import dev.teogor.sudoklify.types.TokenMap
import dev.teogor.sudoklify.types.toToken

/**
 * Generates a mapping between token values and their corresponding
 * string representations.
 *
 * TODO annotation for internal use
 *
 * @param boxDigits The number of digits used to represent each box
 * in the Sudoku puzzle.
 *
 * @return A `TokenMap` containing the mapping between token values
 * and their string representations.
 */
fun generateTokenMap(boxDigits: Int): TokenMap {
  val gridList = (1..boxDigits)
  val tokenList = gridList.withIndex().map { (index, _) ->
    val value = if (index < boxDigits) (index + 1) else (index - boxDigits + 1)
    value.toToken()
  }

  val tokenMap = tokenList.withIndex().associate { (index, token) ->
    val value =
      if (index < boxDigits) (index + 1).toString() else (index - boxDigits + 1).toString()
    token to value
  }
  return tokenMap
}
