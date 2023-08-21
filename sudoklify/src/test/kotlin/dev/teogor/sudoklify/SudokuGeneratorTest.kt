package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.Type
import dev.teogor.sudoklify.types.toNumber
import dev.teogor.sudoklify.types.toToken
import org.junit.Assert.assertEquals
import org.junit.Test

class SudokuGeneratorTest {

  @Test
  fun testGenerateSudoku() {
    val type = Type.THREE_BY_THREE
    val sudoku = SudokuGenerator.getSudoku(Difficulty.MEDIUM, 12345, type)

    // Verify that the generated sudoku has the correct type
    assertEquals(type, sudoku.type)

    // Verify that the puzzle and solution have the correct lengths
    assertEquals(type.rows * type.cols * type.rows * type.cols, sudoku.puzzle.length)
    assertEquals(type.rows * type.cols * type.rows * type.cols, sudoku.solution.length)
  }

  @Test
  fun testTokenConversion() {
    val value = 5
    val token = value.toToken()
    val convertedValue = token.toNumber()

    assertEquals(value, convertedValue)
  }

  // Add more tests for other methods as needed...
}
