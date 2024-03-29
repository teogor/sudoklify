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

import dev.teogor.sudoklify.ktx.toJEncodedCell
import java.util.regex.Pattern

class SudokuDecoder {
  private val cellRegex: Pattern = Pattern.compile("<td class=\"[a-zA-Z]*\">(.*?)</td>")
  private var matchCount = 0

  fun parsePuzzle(fileContent: String): String {
    val puzzleBuilder = StringBuilder()

    val matchResults = cellRegex.matcher(fileContent)

    while (matchResults.find()) {
      matchCount++
      val cellContent = matchResults.group(1)
      val parsedNumber =
        if (cellContent == "&nbsp;") {
          "-"
        } else {
          cellContent.toInt().toJEncodedCell()
        }
      puzzleBuilder.append(parsedNumber)
    }

    return puzzleBuilder.toString()
  }

  fun getMatchCount(): Int {
    return matchCount
  }
}
