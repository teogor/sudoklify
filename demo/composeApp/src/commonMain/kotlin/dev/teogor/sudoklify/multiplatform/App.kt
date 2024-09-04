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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.teogor.paletteon.PaletteonDynamicTheme
import dev.teogor.paletteon.ui.components.PaletteonThemedSurface
import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.Seed
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Main entry point of the Sudoku game UI.
 *
 * @param sudokuGameViewModel The ViewModel managing the game state and logic. Defaults to a new instance.
 */
@OptIn(ExperimentalSudoklifyApi::class)
@Composable
@Preview
fun App(
  sudokuGameViewModel: SudokuGameViewModel = viewModel {
    SudokuGameViewModel(SavedStateHandle())
  },
) {
  PaletteonDynamicTheme(
    seedColor = Color.Magenta,
    useDarkTheme = isSystemInDarkTheme(),
    animate = true,
  ) {
    PaletteonThemedSurface(
      modifier = Modifier.fillMaxSize(),
    ) {
      var startNewGame by remember { mutableStateOf(false) }
      Column(
        modifier = Modifier.fillMaxWidth()
          .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Button(
          onClick = {
            sudokuGameViewModel.generateSudoku(
              difficulty = Difficulty.HARD,
              dimension = Dimension(9),
              seed = Seed.Random(),
            )
            startNewGame = true
          },
        ) {
          Text("Start new game.")
        }
        val gameState by sudokuGameViewModel.sudokuGameState.collectAsState()
        AnimatedVisibility(startNewGame) {
          SudokuBoard(
            dimension = Dimension(9),
            grid = gameState.grid,
            selectedCell = gameState.selectedCell,
            onCellSelected = { row, col ->
              sudokuGameViewModel.selectCell(row, col)
            },
            onCellValueUpdated = { row, col, value ->
              sudokuGameViewModel.makeMove(row, col, value)
            },
          )
        }
      }
    }
  }
}
