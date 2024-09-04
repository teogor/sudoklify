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

package dev.teogor.sudoklify.tokenizer

import kotlin.jvm.JvmInline

@JvmInline
public value class SudokuString(public val value: String) {
  init {
    if (value.isEmpty()) {
      throw EmptySudokuStringException("Value must not be empty.")
    }

    if (!JEncodedCell.isValid(value)) {
      val invalidComponents = JEncodedCell.findInvalidComponents(value)
      throw InvalidSudokuValueException("Value contains invalid values: $invalidComponents")
    }
  }

  override fun toString(): String = value
}

public fun String.toSudokuString(): SudokuString {
  return SudokuString(this)
}

public class EmptySudokuStringException(message: String) : IllegalArgumentException(message)

public class InvalidSudokuValueException(message: String) : IllegalArgumentException(message)
