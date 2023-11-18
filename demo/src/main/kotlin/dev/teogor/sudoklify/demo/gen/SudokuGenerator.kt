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

package dev.teogor.sudoklify.demo.gen

import kotlin.math.sqrt

class InvalidSizeError : Exception("Requested size is invalid (4-n)")

class Generator(private val size: Int, private val removeableCells: Int) {
  private val sqrSize: Int
  private val board: Array<Array<Int>>

  init {
    if (size < 4) throw InvalidSizeError()

    sqrSize = sqrt(size.toDouble()).toInt()
    board = Array(size) { Array(size) { 0 } }
  }

  fun build(): Array<Array<Int>> {
    fillDiagonal()
    fillRemaining()
    setDifficulty()
    return board
  }

  fun print() {
    for (i in 0 until size) {
      for (j in 0 until size) {
        val value = if (board[i][j] == 0) {
          "."
        } else {
          board[i][j].toString()
        }
        print("$value")
      }
      println()
    }
    println()
  }

  private fun fillDiagonal() {
    for (i in 0 until size step sqrSize) {
      fillBox(i, i)
    }
  }

  private fun fillBox(row: Int, col: Int) {
    var num: Int
    for (i in 0 until sqrSize) {
      for (j in 0 until sqrSize) {
        do {
          num = randomNum(size)
        } while (!canFillBox(row, col, num))

        board[row + i][col + j] = num
      }
    }
  }

  private fun canFillBox(rowStart: Int, colStart: Int, num: Int): Boolean {
    for (i in 0 until sqrSize) {
      for (j in 0 until sqrSize) {
        if (board[rowStart + i][colStart + j] == num) return false
      }
    }
    return true
  }

  private fun randomNum(max: Int): Int {
    return (1..max).shuffled().first()
  }

  private fun canFill(i: Int, j: Int, num: Int): Boolean {
    return canFillRow(i, num) &&
      canFillCol(j, num) &&
      canFillBox(i - i % sqrSize, j - j % sqrSize, num)
  }

  private fun canFillRow(i: Int, num: Int): Boolean {
    for (j in 0 until size) {
      if (board[i][j] == num) return false
    }
    return true
  }

  private fun canFillCol(j: Int, num: Int): Boolean {
    for (i in 0 until size) {
      if (board[i][j] == num) return false
    }
    return true
  }

  private fun fillRemaining(argI: Int = 0, argJ: Int = sqrSize): Boolean {
    var i = argI
    var j = argJ

    if (j >= size && i < size - 1) {
      i += 1
      j = 0
    }
    if (i >= size && j >= size) {
      return true
    }

    if (i < sqrSize) {
      if (j < sqrSize) {
        j = sqrSize
      }
    } else if (i < size - sqrSize) {
      if (j == (i / sqrSize) * sqrSize) {
        j += sqrSize
      }
    } else {
      if (j == size - sqrSize) {
        i += 1
        j = 0
        if (i >= size) {
          return true
        }
      }
    }

    (1..size).shuffled().forEach { num ->
      if (canFill(i, j, num)) {
        board[i][j] = num
        if (fillRemaining(i, j + 1)) {
          return true
        }

        board[i][j] = 0
      }
    }
    return false
  }

  private fun setDifficulty() {
    repeat(removeableCells) {
      var i: Int
      var j: Int
      do {
        val cellId = randomNum(size * size) - 1
        i = cellId / size
        j = cellId % size
      } while (board[i][j] == 0)

      board[i][j] = 0
    }
  }
}

fun main() {
  val size = 16
  val filledCells = 60
  val removeableCells = size * size - filledCells
  val puzzle = Generator(16, removeableCells)
  puzzle.build()
  puzzle.print()
}
