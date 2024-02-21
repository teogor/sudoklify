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

package dev.teogor.sudoklify.ktx

import dev.teogor.sudoklify.common.types.BoardCell

fun Int.toBoardCell(): BoardCell {
  return when {
    this == 0 -> "-"

    else -> {
      var valueCopy = this
      buildString {
        while (valueCopy > 0) {
          val char =
            when (val digit = (valueCopy % 10)) {
              0 -> 'j'
              else -> ('a' + digit - 1)
            }
          append(char)
          valueCopy /= 10
        }
        reverse()
        this[0] = this[0].uppercaseChar()
      }
    }
  }
}

fun BoardCell.toInt(): Int {
  return when {
    this == "-" -> 0

    else ->
      map { char ->
        when {
          char.isUpperCase() -> {
            char - 'A' + 1
          }

          else -> {
            char - 'a' + 1
          }
        }
      }.fold(0) { acc, digit -> acc * 10 + digit }
  }
}
