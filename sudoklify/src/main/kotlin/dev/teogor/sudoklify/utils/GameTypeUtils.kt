package dev.teogor.sudoklify.utils

import dev.teogor.sudoklify.model.GameType

/**
 * Finds a GameType based on the given number of rows and columns.
 *
 * @param rows The number of rows in the Sudoku grid.
 * @param cols The number of columns in the Sudoku grid.
 * @return The matching GameType, or GameType.Unspecified if no match is found.
 */
fun findBySize(rows: Int, cols: Int): GameType {
  return GameType.values()
    .find {
      it.sectionHeight == rows && it.sectionWidth == cols
    } ?: GameType.Unspecified
}
