package dev.teogor.sudoklify.utils

import dev.teogor.sudoklify.model.Difficulty

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
