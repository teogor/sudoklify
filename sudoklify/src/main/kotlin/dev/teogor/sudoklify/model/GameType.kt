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

package dev.teogor.sudoklify.model

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
enum class GameType(
  val gridHeight: Int,
  val gridWidth: Int,
) {
  /**
   * The unspecified game type.
   */
  Unspecified(0, 0),

  /**
   * The 4x4 game type.
   */
  FourDigits(2, 2),

  /**
   * The 6x6 game type.
   */
  SixDigits(2, 3),

  /**
   * The 8x8 game type.
   */
  EightDigits(2, 4),

  /**
   * The 9x9 game type.
   */
  NineDigits(3, 3),

  /**
   * The 10x10 game type.
   */
  TenDigits(2, 5),

  /**
   * The 12x12 game type.
   */
  TwelveDigits(3, 4),

  /**
   * The 15x15 game type.
   */
  FifteenDigits(3, 5),

  /**
   * The 16x16 game type.
   */
  SixteenDigits(4, 4),

  /**
   * The 25x25 game type.
   */
  TwentyFiveDigits(5, 5),

  /**
   * The 36x36 game type.
   */
  ThirtySixDigits(6, 6),

  /**
   * The 49x49 game type.
   */
  FortyNineDigits(7, 7),

  /**
   * The 64x64 game type.
   */
  SixtyFourDigits(8, 8),

  /**
   * The 81x81 game type.
   */
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
  @Override
  override fun toString(): String {
    return "${cells}x$cells"
  }
}
