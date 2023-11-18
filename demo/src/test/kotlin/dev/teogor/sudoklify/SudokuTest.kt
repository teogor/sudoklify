package dev.teogor.sudoklify
import dev.teogor.sudoklify.demo.gen.impl.comparePuzzles
import dev.teogor.sudoklify.extensions.generateSudoku
import dev.teogor.sudoklify.extensions.toBoard
import dev.teogor.sudoklify.extensions.toSequenceString
import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SudokuTest {

  @Test
  fun `test Verify Solution Generation for 4x4 Sudoku`() {
    val expectedSolution = "3214412314322341"

    val gameType = GameType.FourDigits
    val sudokuParams = sudokuParamsBuilder {
      seed { 0L }
      gameType { gameType }
      difficulty { Difficulty.EASY }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(expectedSolution, sudoku.solution.toSequenceString())
  }

  @Test
  fun `test Verify Solution Generation for 9x9 Sudoku`() {
    val expectedSolution = "395126784178549623642783915531678249964215378827934561486352197759861432213497856"

    val gameType = GameType.NineDigits
    val sudokuParams = sudokuParamsBuilder {
      seed { 0L }
      gameType { gameType }
      difficulty { Difficulty.EASY }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(expectedSolution, sudoku.solution.toSequenceString())
  }

  @Test
  fun `test Verify Solution Generation for 16x16 Sudoku`() {
    val expectedSolution = "31127813914516611041215311278139145166110412158610161245111915214713351512927101131314461681610134151212711814953161114512987616431511021316311541113109526712148213145710119812163411561381611012147415115236915591411163862171213104101271161542133981614159782143165121101315641112161161584151413932710741583212161651013119141931013146152741158161214141316615310871211952"

    val gameType = GameType.SixteenDigits
    val sudokuParams = sudokuParamsBuilder {
      seed { 0L }
      gameType { gameType }
      difficulty { Difficulty.EASY }
    }
    val sudoku = sudokuParams.generateSudoku()

    assertEquals(expectedSolution, sudoku.solution.toSequenceString())
  }

  @Test
  fun `test Verify Puzzle Solutions Against Generated Puzzles`() {
    SEEDS.forEach { sudoku ->
      val isValid = comparePuzzles(
        puzzle = sudoku.puzzle.toBoard(sudoku.gameType),
        solution = sudoku.solution.toBoard(sudoku.gameType),
      )

      assertEquals(true, isValid, "Invalid puzzle for seed ${sudoku.solution}")
    }
  }
}
