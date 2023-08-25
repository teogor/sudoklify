package dev.teogor.sudoklify.extensions

import dev.teogor.sudoklify.types.Board
import dev.teogor.sudoklify.types.SudokuString

fun Board.toSequenceString(): SudokuString = joinToString("") {
  it.joinToString("")
}

fun SudokuString.toBoard(
  boxDigits: Int,
): Board {
  return chunked(boxDigits)
    .map { chunk -> chunk.map { it.toString() }.toTypedArray() }
    .toTypedArray()
}
