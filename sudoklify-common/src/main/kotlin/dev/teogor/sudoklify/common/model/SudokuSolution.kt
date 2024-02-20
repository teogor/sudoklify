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

package dev.teogor.sudoklify.common.model

data class SudokuSolution(
  val elapsedTime: Long,
  val solutionFound: Boolean,
  val stepCount: Int,
  val solvedGrid: Array<IntArray>,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as SudokuSolution

    if (elapsedTime != other.elapsedTime) return false
    if (solutionFound != other.solutionFound) return false
    if (stepCount != other.stepCount) return false
    if (!solvedGrid.contentDeepEquals(other.solvedGrid)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = elapsedTime.hashCode()
    result = 31 * result + solutionFound.hashCode()
    result = 31 * result + stepCount
    result = 31 * result + solvedGrid.contentDeepHashCode()
    return result
  }
}
