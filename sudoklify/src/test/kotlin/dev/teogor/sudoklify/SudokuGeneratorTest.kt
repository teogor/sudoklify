package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.types.toNumber
import dev.teogor.sudoklify.types.toToken
import org.junit.Assert.assertEquals
import org.junit.Test

class SudokuGeneratorTest {

  @Test
  fun testGenerateSudoku() {
    val gameType = GameType.THREE_BY_THREE
    val sudoku = SudokuGenerator.getSudoku(Difficulty.MEDIUM, 12345, gameType)

    // Verify that the generated sudoku has the correct type
    assertEquals(gameType, sudoku.type)

    // Verify that the puzzle and solution have the correct lengths
    assertEquals(gameType.sectionHeight * gameType.sectionWidth * gameType.sectionHeight * gameType.sectionWidth, sudoku.puzzle.length)
    assertEquals(gameType.sectionHeight * gameType.sectionWidth * gameType.sectionHeight * gameType.sectionWidth, sudoku.solution.length)
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
