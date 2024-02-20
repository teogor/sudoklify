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

package dev.teogor.sudoklify.common.types

/**
 * Represents a seed value for generating randomization in various contexts.
 *
 * Seed values are typically used to control the starting point of a random sequence,
 * ensuring reproducibility and consistency across different runs. This sealed class
 * provides a safe and structured way to handle different types of seeds.
 *
 * @property value The underlying long value used for the seed. Must be non-negative.
 *
 * @throws IllegalArgumentException if the initial value is negative.
 */
sealed class Seed(
  val value: Long,
) : Comparable<Seed> {
  init {
    if (value < 0) {
      throw IllegalArgumentException("Seed value must be non-negative.")
    }
  }

  /**
   * Generates a new [Seed] object with an incremented value, if applicable.
   * This is the default behavior for [Explicit] seeds, while [Random] seeds remain the same.
   * Subclasses can override this method to provide different behavior.
   *
   * @return A new [Seed] object with an incremented value.
   */
  open fun nextSeed(): Seed =
    when (this) {
      is Random -> Random()
      is Explicit -> Explicit(value + 1)
    }

  /**
   * Returns a [kotlin.random.Random] instance based on the value of this seed.
   *
   * This function creates a new [kotlin.random.Random] instance using the [value]
   * property as the seed.
   *
   * @return A [kotlin.random.Random] instance.
   */
  fun toRandom(): kotlin.random.Random = kotlin.random.Random(value)

  /**
   * Returns a string representation of the [Seed] object.
   * It indicates the type (Random or Explicit) and the underlying value.
   *
   * @return A string representation of the [Seed] object.
   */
  override fun toString(): String =
    when (this) {
      is Random -> "RandomSeed($value)"
      is Explicit -> "ExplicitSeed($value)"
    }

  /**
   * Creates a copy of the [Seed] object with an optional new value.
   * If no value is provided, the current value is used.
   *
   * @param seed The new value for the seed (optional).
   *
   * @return A new [Seed] object with the specified value.
   */
  fun copy(seed: Long? = null): Seed {
    return when (this) {
      is Random -> Random()
      is Explicit -> Explicit(seed ?: this.value)
    }
  }

  /**
   * Compares this [Seed] object with another [Seed] object.
   * Random seeds are considered equal to other Random seeds, while Explicit seeds are
   * compared based on their values.
   *
   * @param other The other [Seed] object to compare with.
   *
   * @return 0 if the seeds are equal, -1 if this seed is less than the other, 1 if it's
   * greater.
   */
  override fun compareTo(other: Seed): Int {
    return when (this) {
      is Random -> {
        if (other is Random) 0 else 1
      }

      is Explicit -> {
        if (other is Explicit) value.compareTo(other.value) else -1
      }
    }
  }

  /**
   * Represents a random seed generated using the system's random number generator.
   *
   * @see kotlin.random.Random
   */
  class Random : Seed(kotlin.random.Random.nextLong())

  /**
   * Represents an explicit seed with a specific long value.
   *
   * @param value The value for the explicit seed.
   *
   * @throws IllegalArgumentException if the value is negative.
   */
  class Explicit(value: Long) : Seed(value)
}
