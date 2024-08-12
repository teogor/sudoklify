/*
 * Copyright 2023 Teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.teogor.sudoklify.common.types

// TODO get box size and row/col size

/**
 * Represents the different game types available in the Sudoku game.
 *
 * Each game type is defined by its grid height and grid width. The grid height and width
 * determine the size of the puzzle grid and the number of digits in the puzzle.
 *
 * The `GameType` enum also includes a `cells` property that returns the total number of cells in the
 * puzzle, and an `isSquare` property that returns whether the puzzle is a square.
 */
@Deprecated(
  message = "GameType is deprecated, use SudokuType instead.",
  replaceWith =
    ReplaceWith(
      expression = "SudokuType",
      "dev.teogor.sudoklify.common.types.SudokuType",
    ),
)
enum class GameType(
  val gridHeight: Int,
  val gridWidth: Int,
) {
  /**
   * The unspecified game type.
   */
  @Deprecated(
    message = "GameType.Unspecified is deprecated, use SudokuType.Unspecified instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Unspecified",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  Unspecified(0, 0),

  /**
   * The 4x4 game type.
   */
  @Deprecated(
    message = "GameType.FourDigits is deprecated, use SudokuType.Sudoku4x4 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku4x4",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  FourDigits(2, 2),

  /**
   * The 6x6 game type.
   */
  @Deprecated(
    message = "GameType.SixDigits is deprecated, use SudokuType.Sudoku6x6 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku6x6",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  SixDigits(2, 3),

  /**
   * The 8x8 game type.
   */
  @Deprecated(
    message = "GameType.EightDigits is deprecated, use SudokuType.Sudoku8x8 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku8x8",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  EightDigits(2, 4),

  /**
   * The 9x9 game type.
   */
  @Deprecated(
    message = "GameType.NineDigits is deprecated, use SudokuType.Sudoku9x9 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku9x9",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  NineDigits(3, 3),

  /**
   * The 10x10 game type.
   */
  @Deprecated(
    message = "GameType.TenDigits is deprecated, use SudokuType.Sudoku10x10 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku10x10",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  TenDigits(2, 5),

  /**
   * The 12x12 game type.
   */
  @Deprecated(
    message = "GameType.TwelveDigits is deprecated, use SudokuType.Sudoku12x12 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku12x12",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  TwelveDigits(3, 4),

  /**
   * The 15x15 game type.
   */
  @Deprecated(
    message = "GameType.FifteenDigits is deprecated, use SudokuType.Sudoku15x15 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku15x15",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  FifteenDigits(3, 5),

  /**
   * The 16x16 game type.
   */
  @Deprecated(
    message = "GameType.SixteenDigits is deprecated, use SudokuType.Sudoku16x16 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku16x16",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  SixteenDigits(4, 4),

  /**
   * The 25x25 game type.
   */
  @Deprecated(
    message = "GameType.TwentyFiveDigits is deprecated, use SudokuType.Sudoku25x25 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku25x25",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  TwentyFiveDigits(5, 5),

  /**
   * The 36x36 game type.
   */
  @Deprecated(
    message = "GameType.ThirtySixDigits is deprecated, use SudokuType.Sudoku36x36 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku36x36",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  ThirtySixDigits(6, 6),

  /**
   * The 49x49 game type.
   */
  @Deprecated(
    message = "GameType.FortyNineDigits is deprecated, use SudokuType.Sudoku49x49 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku49x49",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  FortyNineDigits(7, 7),

  /**
   * The 64x64 game type.
   */
  @Deprecated(
    message = "GameType.SixtyFourDigits is deprecated, use SudokuType.Sudoku64x64 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku64x64",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  SixtyFourDigits(8, 8),

  /**
   * The 81x81 game type.
   */
  @Deprecated(
    message = "GameType.EightyOneDigits is deprecated, use SudokuType.Sudoku81x81 instead.",
    replaceWith =
      ReplaceWith(
        expression = "SudokuType.Sudoku81x81",
        "dev.teogor.sudoklify.common.types.SudokuType",
      ),
  )
  EightyOneDigits(9, 9),
  ;

  /**
   * The total number of cells in the puzzle.
   */
  val cells: Int
    get() = gridHeight * gridWidth

  /**
   * Whether the puzzle is a square.
   */
  val isSquare: Boolean
    get() = gridHeight == gridWidth

  /**
   * Returns a string representation of the game type.
   */
  override fun toString(): String {
    return "${cells}x$cells"
  }
}
