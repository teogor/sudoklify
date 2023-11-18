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

package dev.teogor.sudoklify.types

typealias Token = String

fun Int.toToken(): Token {
  var valueCopy = this
  val charList = mutableListOf<Char>()
  while (valueCopy > 0) {
    val digit = (valueCopy % 10)
    val char = if (digit == 0) {
      ('a' + 9)
    } else {
      ('a'.code + digit - 1).toChar()
    }
    charList.add(0, char)
    valueCopy /= 10
  }

  charList[0] = charList[0].uppercaseChar()
  return charList.joinToString("")
}

fun Token.toNumber(): Int {
  var value = 0
  for (char in this) {
    val digitValue = if (char.isUpperCase()) {
      char - 'A' + 1
    } else {
      char - 'a' + 1
    }
    value = value * 10 + digitValue
  }
  return value
}
