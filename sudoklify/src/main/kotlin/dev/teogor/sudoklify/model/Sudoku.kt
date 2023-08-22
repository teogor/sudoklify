package dev.teogor.sudoklify.model

import dev.teogor.sudoklify.types.Board

data class Sudoku(
  val puzzle: Board,
  val solution: Board,
  val difficulty: Difficulty,
  val type: Type,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Sudoku

    if (!puzzle.contentDeepEquals(other.puzzle)) return false
    if (!solution.contentDeepEquals(other.solution)) return false
    if (difficulty != other.difficulty) return false
    if (type != other.type) return false

    return true
  }

  override fun hashCode(): Int {
    var result = puzzle.contentDeepHashCode()
    result = 31 * result + solution.contentDeepHashCode()
    result = 31 * result + difficulty.hashCode()
    result = 31 * result + type.hashCode()
    return result
  }
}
