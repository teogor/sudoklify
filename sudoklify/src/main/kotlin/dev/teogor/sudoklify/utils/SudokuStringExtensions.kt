package dev.teogor.sudoklify.utils

import dev.teogor.sudoklify.types.PuzzleString

/**
 * Counts the number of remaining cells in the Sudoku puzzle.
 *
 * A remaining cell is a cell that is currently empty and needs to be filled in by
 * the user. The function traverses the provided PuzzleString and counts the number
 * of occurrences of the '-' character, which represents an empty cell.
 *
 * @return The number of remaining cells in the Sudoku puzzle.
 */
fun PuzzleString.countRemainingCells(): Int {
  return count { it == '-' }
}
