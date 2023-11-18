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

/**
 * Represents the difficulty levels of Sudoku puzzles.
 *
 * | Column | Description |
 * |---|---|
 * | Difficulty | The overall difficulty level of the Sudoku puzzle. |
 * | Clues (%) | The percentage of cells in the puzzle that have pre-filled clues. |
 * | Givens (%) | The percentage of cells in the puzzle that need to be filled in. |
 * | Symmetry | Indicates whether the puzzle has symmetry, which can make it easier to solve. |
 * | Description | A brief description of the characteristics and challenges of puzzles at each difficulty level. |
 *
 * | Difficulty | Clues (%) | Givens (%) | Symmetry | Description |
 * |---|---|---|---|---|
 * | VERY_EASY | 60-80 | 80-100 | Yes | These puzzles are designed for beginners and have a large number of clues filled in, making them easy to solve. |
 * | EASY | 40-60 | 60-80 | Yes | These puzzles are still relatively easy to solve but require more effort than VERY_EASY puzzles. |
 * | MEDIUM | 20-40 | 40-60 | Yes | These puzzles are a good challenge for experienced solvers and require careful deduction. |
 * | HARD | 0-20 | 20-40 | No | These puzzles are designed to challenge even the most experienced solvers and require advanced Sudoku techniques. |
 * | VERY_HARD | 0-10 | 10-20 | No | These puzzles are the most difficult to solve and are intended only for Sudoku experts, requiring extreme patience and logical reasoning. |
 */
enum class Difficulty {
  /**
   * Indicates an easy Sudoku puzzle with a large number of clues.
   */
  VERY_EASY,

  /**
   * Indicates an easy Sudoku puzzle with fewer clues than VERY_EASY puzzles.
   */
  EASY,

  /**
   * Indicates a Sudoku puzzle of moderate difficulty with a moderate number of clues.
   */
  MEDIUM,

  /**
   * Indicates a challenging Sudoku puzzle with few clues and intricate arrangements.
   */
  HARD,

  /**
   * Indicates an extremely challenging Sudoku puzzle with almost no clues and highly complex arrangements.
   */
  VERY_HARD,
}
