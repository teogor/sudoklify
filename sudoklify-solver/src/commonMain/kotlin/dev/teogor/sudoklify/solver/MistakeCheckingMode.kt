/*
 * Copyright 2024 Teogor (Teodor Grigor)
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

package dev.teogor.sudoklify.solver

/**
 * Represents the different modes for mistake checking in the Sudoku game.
 *
 * The [MistakeCheckingMode] enum defines how mistakes should be handled during gameplay.
 * Depending on the selected mode, the application will either disable mistake checking,
 * check for violations only, or check against the final solution.
 */
enum class MistakeCheckingMode {
  /**
   * Disables mistake checking.
   *
   * In this mode, the player can enter numbers without any feedback
   * on whether the entered number is correct or incorrect.
   * This mode is suitable for players who want to play without hints or assistance.
   */
  NoChecking,

  /**
   * Checks for violations only.
   *
   * In this mode, the application checks the player's input against the
   * Sudoku rules and notifies the player when a violation is detected.
   * However, it does not compare the input to the final solution.
   * This mode is useful for players who want to be alerted to rule violations
   * without being given the final solution.
   */
  CheckViolations,

  /**
   * Checks against the final solution.
   *
   * In this mode, the player's input is compared directly with the final solution.
   * The player is notified if the entered number does not match the correct number in the solution.
   * This mode is ideal for players who want to ensure their entries are correct
   * as per the final solution, offering a higher level of assistance.
   */
  CheckAgainstSolution,
}
