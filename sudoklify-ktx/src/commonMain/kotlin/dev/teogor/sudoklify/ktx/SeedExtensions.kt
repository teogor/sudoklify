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

import dev.teogor.sudoklify.common.types.Seed

/**
 * Creates a new [Seed] object from this [Long] value, representing an explicit seed.
 *
 * @throws IllegalArgumentException if the value is negative.
 *
 * @see Seed
 * @see Seed.Explicit
 * @see createSeed
 */
fun Long.toSeed(): Seed = createSeed(this)

/**
 * Creates a new [Seed] object with the specified [value], representing an explicit seed.
 *
 * @param value The long value to use for the seed. Must be non-negative.
 *
 * @return The created [Seed] object.
 *
 * @throws IllegalArgumentException if the value is negative.
 *
 * @see Seed
 * @see Seed.Explicit
 */
fun createSeed(value: Long): Seed {
  if (value < 0) {
    throw IllegalArgumentException("Seed value must be non-negative.")
  }
  return Seed.Explicit(value)
}
