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

package dev.teogor.sudoklify.ktx

import dev.teogor.sudoklify.common.types.Difficulty

/**
 * Converts the difficulty level to a string representation using stars.
 *
 * @return A string representation of the difficulty level using stars.
 */
fun Difficulty.toStars(): String {
  return "★".repeat(ordinal + 1)
}

/**
 * Converts the difficulty level to a string representation using the
 * provided difficulty labels.
 *
 * @param labels An array of difficulty labels.
 *
 * @return The corresponding difficulty label based on the ordinal position
 * of the `Difficulty` enum value within the array.
 */
fun Difficulty.toLabel(labels: Array<String>): String {
  return labels[ordinal]
}
