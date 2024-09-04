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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.components.getBoxCoordinates
import dev.teogor.sudoklify.components.getCellCoordinates
import dev.teogor.sudoklify.components.isAlternateBox
import dev.teogor.sudoklify.components.isBottomEnd
import dev.teogor.sudoklify.components.isBottomStart
import dev.teogor.sudoklify.components.isTopEnd
import dev.teogor.sudoklify.components.isTopStart

/**
 * Displays the Sudoku board based on the provided grid and dimension.
 *
 * @param dimension The dimension of the Sudoku board (e.g., 9x9).
 * @param grid The current state of the Sudoku board, represented as a list of rows with cells.
 * @param selectedCell The currently selected cell, if any.
 * @param onCellSelected Callback invoked when a cell is selected by the user.
 * @param onCellValueUpdated Callback invoked when the value of a cell is updated by the user.
 */
@OptIn(ExperimentalSudoklifyApi::class)
@Composable
fun SudokuBoard(
  dimension: Dimension,
  grid: List<List<Cell>>,
  selectedCell: Cell.Position?,
  onCellSelected: (row: Int, col: Int) -> Unit,
  onCellValueUpdated: (row: Int, col: Int, newValue: Int) -> Unit,
) {
  BoardSquare {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      for (row in 0 until dimension.height) {
        Row(modifier = Modifier.fillMaxWidth()) {
          for (col in 0 until dimension.width) {
            val cell = grid[row][col]
            val cellCoordinates = dimension.getCellCoordinates(row, col)
            val boxCoordinates = dimension.getBoxCoordinates(row, col)
            val shape = RoundedCornerShape(
              topStart = if (cellCoordinates.isTopStart(dimension)) 10.dp else 0.dp,
              topEnd = if (cellCoordinates.isTopEnd(dimension)) 10.dp else 0.dp,
              bottomStart = if (cellCoordinates.isBottomStart(dimension)) 10.dp else 0.dp,
              bottomEnd = if (cellCoordinates.isBottomEnd(dimension)) 10.dp else 0.dp,
            )

            var showDropDown by remember { mutableStateOf(false) }
            if (!cell.isGiven) {
              val digits = (1..dimension.uniqueDigitsCount).toList()

              DropdownMenu(
                expanded = showDropDown,
                onDismissRequest = {
                  showDropDown = false
                },
              ) {
                DropdownMenuItem(
                  text = { Text("Clear") },
                  onClick = {
                    onCellValueUpdated(row, col, 0) // Clear the cell value
                    showDropDown = false
                  },
                )
                digits.forEach { digit ->
                  DropdownMenuItem(
                    text = { Text(digit.toString()) },
                    onClick = {
                      onCellValueUpdated(row, col, digit) // Update the cell value
                      showDropDown = false
                    },
                  )
                }
              }
            }

            SudokuCell(
              cell = cell,
              modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .border(
                  width = 2.dp,
                  color = MaterialTheme.colorScheme.secondary,
                  shape = shape,
                )
                .clip(shape)
                .background(
                  if (selectedCell == Cell.Position(row, col)) {
                    Color.Green.copy(alpha = .2f)
                  } else if (cell.isError) {
                    Color.Red.copy(alpha = .2f)
                  } else if (boxCoordinates.isAlternateBox(dimension)) {
                    MaterialTheme.colorScheme.primaryContainer
                  } else {
                    MaterialTheme.colorScheme.background
                  },
                  shape,
                )
                .clickable {
                  onCellSelected(row, col)
                  showDropDown = true
                },
            )
          }
        }
      }
    }
  }
}

@Composable
private fun BoardSquare(
  content: @Composable BoxScope.() -> Unit,
) {
  var columnHeightPx by remember { mutableFloatStateOf(0f) }
  var columnWidthPx by remember { mutableFloatStateOf(0f) }
  Box(
    modifier = Modifier
      .fillMaxWidth(.5f)
      .aspectRatio(1f)
      .onGloballyPositioned { coordinates ->
        columnHeightPx = coordinates.size.height.toFloat()
        columnWidthPx = coordinates.size.width.toFloat()
      },
  ) {
    content()
  }

}

/**
 * Represents an individual cell in the Sudoku grid.
 *
 * @param cell The cell data to display, including its value and state.
 * @param modifier The modifier to apply to the composable.
 */
@Composable
fun SudokuCell(
  cell: Cell,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .aspectRatio(1f),
  ) {
    Text(
      text = if (cell.value != 0) cell.value.toString() else "",
      fontSize = 24.sp,
      modifier = Modifier.align(Alignment.Center),
      color = if (cell.isGiven) {
        MaterialTheme.colorScheme.tertiary
      } else Color.Unspecified,
    )
  }
}
