package dev.teogor.sudoklify.extensions

import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.types.Board
import dev.teogor.sudoklify.types.SudokuString

/**
 * Converts a `Board` object to a Sudoku string representation.
 *
 * @return A `SudokuString` representing the board.
 */
fun Board.toSequenceString(): SudokuString = joinToString("") {
  it.joinToString("")
}

/**
 * Converts a `SudokuString` to a `Board` object with the specified
 * number of digits per box.
 *
 * @param boxDigits The number of digits used to represent each box
 * in the Sudoku puzzle.
 *
 * @return A `Board` representing the Sudoku puzzle.
 */
@Deprecated("Use the `toBoard(gameType: GameType)` function instead.")
fun SudokuString.toBoard(
  boxDigits: Int,
): Board {
  return chunked(boxDigits)
    .map { chunk -> chunk.map { it.toString() }.toTypedArray() }
    .toTypedArray()
}

/**
 * Converts a `SudokuString` to a list of lists of strings, representing
 * the Sudoku puzzle in a more granular format.
 *
 * @param gameType The game type, which determines the size of the Sudoku
 * puzzle.
 *
 * @return A list of lists of strings representing the Sudoku puzzle.
 */
fun SudokuString.toBoard(
  gameType: GameType,
): Board {
  val regex = Regex("([A-I][a-z]+)|-|[A-I]")
  val matches = regex.findAll(this)
  val matchedTokens = ArrayList<String>()
  matches.forEach { matchedTokens.add(it.value) }
  return matchedTokens
    .chunked(gameType.gridWidth * gameType.gridWidth)
    .map { row -> row.map { it }.toTypedArray() }
    .toTypedArray()
}
