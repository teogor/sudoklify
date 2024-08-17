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

package dev.teogor.sudoklify.random

import dev.teogor.sudoklify.components.Seed
import kotlin.random.Random

class SeededRandom(seed: Seed) : Random() {
  private val internalRandom: Random = Random(seed.value)

  override fun nextBits(bitCount: Int): Int = internalRandom.nextBits(bitCount)

  override fun nextInt(): Int = internalRandom.nextInt()

  override fun nextInt(until: Int): Int = internalRandom.nextInt(until)

  override fun nextInt(
    from: Int,
    until: Int,
  ): Int = internalRandom.nextInt(from, until)

  override fun nextLong(): Long = internalRandom.nextLong()

  override fun nextLong(until: Long): Long = internalRandom.nextLong(until)

  override fun nextLong(
    from: Long,
    until: Long,
  ): Long = internalRandom.nextLong(from, until)

  override fun nextDouble(
    from: Double,
    until: Double,
  ): Double =
    internalRandom.nextDouble(
      from,
      until,
    )

  override fun nextDouble(until: Double): Double = internalRandom.nextDouble(until)

  override fun nextBoolean(): Boolean = internalRandom.nextBoolean()

  override fun nextDouble(): Double = internalRandom.nextDouble()

  override fun nextFloat(): Float = internalRandom.nextFloat()

  override fun nextBytes(array: ByteArray): ByteArray = internalRandom.nextBytes(array)

  override fun nextBytes(size: Int): ByteArray = internalRandom.nextBytes(size)

  override fun nextBytes(
    array: ByteArray,
    fromIndex: Int,
    toIndex: Int,
  ): ByteArray =
    internalRandom.nextBytes(
      array,
      fromIndex,
      toIndex,
    )
}

fun Random.randomOrderFactor(): Int = if (nextDouble() < 0.5) 1 else -1

fun <T> Iterable<T>.randomItem(random: Random): T = shuffled(random).first()

fun <T> Array<T>.randomItem(random: Random): T = random(random)

// region DEPRECATED
@Deprecated(
  message = "Use randomOrderFactor() instead.",
  replaceWith = ReplaceWith("randomOrderFactor()"),
)
fun Random.sortRandom(): Int = if (nextDouble() < 0.5) 1 else -1
// endregion
