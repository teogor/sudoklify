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

package dev.teogor.sudoklify.multiplatform

/**
 * Represents the various states of the Sudoku game.
 */
sealed class GameState {

  /**
   * State indicating that the game has not started yet.
   */
  data object NotStarted : GameState()

  /**
   * State indicating that the game is in the process of loading.
   */
  data object Loading : GameState()

  /**
   * State indicating that the game is currently active and in progress.
   */
  data object Active : GameState()

  /**
   * State indicating that the game is paused.
   */
  data object Paused : GameState()

  /**
   * State indicating that the game has been completed.
   *
   * @property isWon Boolean flag indicating if the player won the game.
   */
  data class Completed(val isWon: Boolean) : GameState()
}
