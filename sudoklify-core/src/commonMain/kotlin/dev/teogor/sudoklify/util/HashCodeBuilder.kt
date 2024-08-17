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

package dev.teogor.sudoklify.util

import dev.teogor.sudoklify.InternalSudoklifyApi
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * A utility class for building hash codes using a custom algorithm.
 *
 * @property initialValue The initial value for the hash code computation. Defaults to `31`.
 * @property multiplier The multiplier used to calculate the hash code. Defaults to `31`.
 *
 * This class allows you to append values to a hash code and compute the final hash code using
 * a specified multiplier and initial value.
 *
 * @see [buildHashCode] for creating a hash code using a builder action.
 */
class HashCodeBuilder(
  private val initialValue: Int = 31,
  private val multiplier: Int = 31,
) {
  private var hashCode = initialValue

  /**
   * Appends the hash code of the given value to the current hash code.
   *
   * @param value The value to append. If the value is an array, its deep hash code is used.
   *              Null values are treated as `0`. If the hash code of the value is `0`, it is
   *              not included.
   * @return The current instance of [HashCodeBuilder], for chaining.
   *
   * @see [buildHashCode] for an example of how to use this method.
   */
  fun append(value: Any?): HashCodeBuilder =
    apply {
      val currentHashCode =
        when (value) {
          is Array<*> -> value.contentDeepHashCode()
          else -> value?.hashCode() ?: 0
        }
      if (currentHashCode != 0) {
        hashCode = multiplier * hashCode + currentHashCode
      }
    }

  /**
   * Computes and returns the final hash code.
   *
   * @return The final hash code computed by the builder.
   *
   * @see [buildHashCode] for an example of how to use this method.
   */
  fun build(): Int = hashCode
}

/**
 * Creates a hash code using a custom builder action.
 *
 * @param initialValue The initial value for the hash code computation. Defaults to `31`.
 * @param multiplier The multiplier used in hash code calculation. Defaults to `31`.
 * @param builderAction A lambda function to build the hash code using [HashCodeBuilder].
 *
 * @return The computed hash code.
 *
 * @see [HashCodeBuilder] for details on how hash codes are computed.
 */
@OptIn(ExperimentalContracts::class)
inline fun buildHashCode(
  initialValue: Int = 31,
  multiplier: Int = 31,
  builderAction: HashCodeBuilder.() -> Unit,
): Int {
  contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
  return HashCodeBuilder(initialValue, multiplier).apply(builderAction).build()
}

/**
 * Creates a lazy hash code using an initializer function.
 *
 * @param initializer A function that returns the hash code value.
 *
 * @return A [Lazy] instance that computes the hash code when accessed.
 *
 * @see [buildLazyHashCode] for creating a lazy hash code using a [HashCodeBuilder].
 */
@InternalSudoklifyApi
fun lazyHashCode(initializer: () -> Int): Lazy<Int> =
  lazy(LazyThreadSafetyMode.PUBLICATION) { initializer() }

/**
 * Creates a lazy hash code using a builder initializer function.
 *
 * @param initializer A lambda function to build the hash code using [HashCodeBuilder].
 *
 * @return A [Lazy] instance that computes the hash code when accessed.
 *
 * @see [HashCodeBuilder] for details on hash code computation.
 */
@OptIn(InternalSudoklifyApi::class)
inline fun buildLazyHashCode(crossinline initializer: HashCodeBuilder.() -> Unit): Lazy<Int> =
  lazyHashCode { HashCodeBuilder().apply(initializer).build() }
