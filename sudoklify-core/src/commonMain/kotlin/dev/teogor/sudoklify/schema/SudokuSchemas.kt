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

package dev.teogor.sudoklify.schema

import dev.teogor.sudoklify.SudoklifyDsl
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension

/**
 * Represents a set of Sudoku schemas.
 *
 * This class encapsulates a collection of [SudokuSchema] objects. It allows iteration over the schemas,
 * copying the set with modified schemas, and provides standard methods for equality and string representation.
 *
 * **Example:**
 *
 * ```kotlin
 * val schema1 = SudokuSchema(
 *     puzzle = SudokuString("A1B2..."),
 *     solution = SudokuString("1A2B..."),
 *     difficulty = Difficulty.MEDIUM,
 *     sudokuType = SudokuType.Sudoku4x4
 * )
 *
 * val schema2 = SudokuSchema(
 *     puzzle = SudokuString("B1C2..."),
 *     solution = SudokuString("2B3C..."),
 *     difficulty = Difficulty.HARD,
 *     sudokuType = SudokuType.Sudoku4x4
 * )
 *
 * // Create a SudokuSchemas with the schemas
 * val schemaSet = SudokuSchemas(listOf(schema1, schema2))
 *
 * // Iterate over the schemas
 * for (schema in schemaSet) {
 *     println(schema)
 * }
 *
 * // Create a copy of the schema set
 * val copiedSchemaSet = schemaSet.copy()
 * ```
 *
 * @property schemas A list of [SudokuSchema] objects that make up the set.
 *
 * @constructor Creates a new instance of [SudokuSchemas] with the provided list of schemas.
 *
 * @see SudokuSchema
 * @see Collection
 */
public class SudokuSchemas(
  private val schemas: List<SudokuSchema>,
) : Collection<SudokuSchema> {
  /**
   * Returns an iterator over the [SudokuSchema] objects in this set.
   *
   * @return An [Iterator] for iterating over the [schemas].
   */
  override fun iterator(): Iterator<SudokuSchema> {
    return schemas.iterator()
  }

  /**
   * Checks if the Sudoku schemas set is empty.
   *
   * @return True if the schemas set is empty, false otherwise.
   */
  override fun isEmpty(): Boolean {
    return schemas.isEmpty()
  }

  /**
   * Returns the number of schemas in this collection.
   *
   * @return The size of the schemas list.
   */
  override val size: Int
    get() = schemas.size

  /**
   * Checks if the specified [SudokuSchema] is present in the collection.
   *
   * @param element The [SudokuSchema] to check for.
   * @return True if the [element] is in the collection, false otherwise.
   */
  override fun contains(element: SudokuSchema): Boolean {
    return schemas.contains(element)
  }

  /**
   * Checks if all specified [SudokuSchema] instances are present in the collection.
   *
   * @param elements The collection of [SudokuSchema] to check for.
   * @return True if all [elements] are in the collection, false otherwise.
   */
  override fun containsAll(elements: Collection<SudokuSchema>): Boolean {
    return schemas.containsAll(elements)
  }

  /**
   * Adds a single [SudokuSchema] to this [SudokuSchemas] collection.
   *
   * @param schema The [SudokuSchema] to add.
   * @return A new [SudokuSchemas] collection containing the original schemas and the added schema.
   */
  public operator fun plus(schema: SudokuSchema): SudokuSchemas {
    return SudokuSchemas((schemas + schema).distinct())
  }

  /**
   * Adds a collection of [SudokuSchema] objects to this [SudokuSchemas] collection.
   *
   * @param other The collection of [SudokuSchema] objects to add.
   * @return A new [SudokuSchemas] collection containing the original schemas and the added schemas.
   */
  public operator fun plus(other: Collection<SudokuSchema>): SudokuSchemas {
    return SudokuSchemas((schemas + other).distinct())
  }

  /**
   * Adds another [SudokuSchemas] collection to this [SudokuSchemas] collection.
   *
   * @param other The [SudokuSchemas] collection to add.
   * @return A new [SudokuSchemas] collection containing the original schemas and the added schemas from the other collection.
   */
  public operator fun plus(other: SudokuSchemas): SudokuSchemas {
    return SudokuSchemas((schemas + other.schemas).distinct())
  }

  /**
   * Filters the schemas by size (dimension).
   *
   * @param type The Sudoku dimension to filter by.
   * @return A new [SudokuSchemas] instance containing only the schemas with the specified dimension.
   */
  public fun getSeedsBySize(type: Dimension): SudokuSchemas {
    return schemas.filter {
      it.dimension.uniqueDigitsCount == type.uniqueDigitsCount
    }.let { schemas -> SudokuSchemas(schemas) }
  }

  /**
   * Filters the schemas by difficulty.
   *
   * @param difficulty The difficulty level to filter by.
   * @return A new [SudokuSchemas] instance containing only the schemas with the specified difficulty.
   */
  public fun getSeedsByDifficulty(difficulty: Difficulty): SudokuSchemas {
    return schemas.filter {
      it.difficulty == difficulty
    }.let { schemas -> SudokuSchemas(schemas) }
  }

  /**
   * Finds all [SudokuSchema] instances with the specified difficulty.
   *
   * Filters the schemas in the current [SudokuSchemas] collection by the given difficulty level.
   * Returns a new [SudokuSchemas] instance containing only the schemas that match the specified difficulty.
   *
   * @param difficulty The difficulty level to filter by.
   * @return A [SudokuSchemas] instance containing all [SudokuSchema] instances with the specified difficulty.
   */
  public fun findByDifficulty(difficulty: Difficulty): SudokuSchemas {
    return schemas.filter {
      it.difficulty == difficulty
    }.let { schemas -> SudokuSchemas(schemas) }
  }

  /**
   * Finds all [SudokuSchema] instances with the specified Sudoku dimension.
   *
   * Filters the schemas in the current [SudokuSchemas] collection by the given Sudoku dimension.
   * Returns a new [SudokuSchemas] instance containing only the schemas that match the specified dimension.
   *
   * @param dimension The Sudoku dimension to filter by.
   * @return A [SudokuSchemas] instance containing all [SudokuSchema] instances with the specified dimension.
   */
  public fun findBySudokuType(dimension: Dimension): SudokuSchemas {
    return schemas.filter {
      it.dimension == dimension
    }.let { schemas -> SudokuSchemas(schemas) }
  }

  /**
   * Gets all unique Sudoku dimensions present in this set.
   *
   * @return A set of unique [Dimension] instances.
   */
  public fun getUniqueSudokuTypes(): Set<Dimension> {
    return schemas.map { it.dimension }.toSet()
  }

  /**
   * Counts the number of schemas for each difficulty level.
   *
   * @return A map where the keys are [Difficulty] levels and the values are counts of schemas with that difficulty.
   */
  public fun countByDifficulty(): Map<Difficulty, Int> {
    return schemas.groupingBy { it.difficulty }.eachCount()
  }

  /**
   * Creates a copy of this [SudokuSchemas] with an optional new list of schemas.
   *
   * @param schemas The new list of schemas to include in the copied set. If not provided,
   *                the original list is used.
   * @return A new [SudokuSchemas] instance with the specified schemas.
   */
  public fun copy(schemas: List<SudokuSchema> = this.schemas): SudokuSchemas {
    return SudokuSchemas(schemas)
  }

  /**
   * Returns a string representation of this [SudokuSchemas].
   *
   * The string representation includes the class name and the list of schemas.
   *
   * @return A string representation of the [SudokuSchemas].
   */
  override fun toString(): String {
    return "SudokuSchemas(schemas=$schemas)"
  }

  /**
   * Computes a hash code for this [SudokuSchemas].
   *
   * The hash code is based on the list of schemas.
   *
   * @return The hash code of the [SudokuSchemas].
   */
  override fun hashCode(): Int {
    return schemas.hashCode()
  }

  /**
   * Compares this [SudokuSchemas] to another object for equality.
   *
   * Two [SudokuSchemas] instances are considered equal if they contain the same list of schemas.
   *
   * @param other The object to compare with this [SudokuSchemas].
   * @return `true` if the other object is a [SudokuSchemas] with the same list of schemas; otherwise, `false`.
   */
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is SudokuSchemas) return false
    return schemas == other.schemas
  }

  /**
   * Converts this [SudokuSchemas] instance into a [Builder] object.
   *
   * This method creates a new [Builder] instance and initializes it with the schemas from this [SudokuSchemas].
   * The returned builder can be used to add, remove, or modify schemas and then build a new [SudokuSchemas] if needed.
   *
   * **Example:**
   *
   * ```kotlin
   * val existingSet = SudokuSchemas(
   *     listOf(
   *         SudokuSchema(
   *             puzzle = SudokuString("A1B2..."),
   *             solution = SudokuString("1A2B..."),
   *             difficulty = Difficulty.MEDIUM,
   *             sudokuType = SudokuType.Sudoku4x4
   *         )
   *     )
   * )
   *
   * // Convert to a builder
   * val builder = existingSet.toBuilder()
   *
   * // Add a new schema to the builder
   * builder.add(SudokuSchema(
   *     puzzle = SudokuString("B1C2..."),
   *     solution = SudokuString("2B3C..."),
   *     difficulty = Difficulty.HARD,
   *     sudokuType = SudokuType.Sudoku4x4
   * ))
   *
   * // Build a new SudokuSchemas with the modified schemas
   * val newSet = builder.build()
   * ```
   *
   * @return A [Builder] initialized with the schemas from this [SudokuSchemas].
   */
  public fun toBuilder(): Builder {
    val builder = Builder()
    builder.addAll(schemas)
    return builder
  }

  /**
   * Builder class for constructing a [SudokuSchemas] with a DSL-style configuration.
   *
   * The builder allows adding individual schemas or multiple schemas to the set in a flexible manner.
   *
   * **Example:**
   *
   * ```kotlin
   * val schemaSet = SudokuSchemas.Builder().apply {
   *     add(SudokuSchema(
   *         puzzle = SudokuString("A1B2..."),
   *         solution = SudokuString("1A2B..."),
   *         difficulty = Difficulty.MEDIUM,
   *         sudokuType = SudokuType.Sudoku4x4
   *     ))
   *     addAll(listOf(
   *         SudokuSchema(
   *             puzzle = SudokuString("B1C2..."),
   *             solution = SudokuString("2B3C..."),
   *             difficulty = Difficulty.HARD,
   *             sudokuType = SudokuType.Sudoku4x4
   *         )
   *     ))
   * }.build()
   * ```
   */
  public class Builder {
    private val schemas = mutableListOf<SudokuSchema>()

    /**
     * Adds a single [SudokuSchema] to the set.
     *
     * @param schema The schema to add.
     * @return This [Builder] instance for chaining.
     */
    public fun add(schema: SudokuSchema): Builder = apply {
      schemas.add(schema)
    }

    /**
     * Adds multiple [SudokuSchema] instances to the set.
     *
     * @param schemas The schemas to add.
     * @return This [Builder] instance for chaining.
     */
    public fun add(vararg schemas: SudokuSchema): Builder = apply {
      this.schemas.addAll(schemas)
    }

    /**
     * Adds multiple [SudokuSchema] instances to the set from an iterable.
     *
     * @param schemas The iterable of schemas to add.
     * @return This [Builder] instance for chaining.
     */
    public fun addAll(schemas: Iterable<SudokuSchema>): Builder = apply {
      this.schemas.addAll(schemas)
    }

    /**
     * Builds the [SudokuSchemas] instance using the schemas added to the builder.
     *
     * @return A new [SudokuSchemas] instance with the configured schemas.
     */
    public fun build(): SudokuSchemas = SudokuSchemas(schemas)
  }
}

/**
 * Creates a [SudokuSchemas] using a [SudokuSchemas.Builder] with a DSL-style configuration.
 *
 * This function provides a convenient way to construct a [SudokuSchemas] by configuring it using
 * a lambda with receiver that operates on the [SudokuSchemas.Builder]. The builder pattern allows
 * for flexible and readable configuration of the schemas included in the set.
 *
 * **Example:**
 *
 * ```kotlin
 * val schemaSet = sudokuSchemas {
 *     // Add individual schemas to the set
 *     add(SudokuSchema(
 *         puzzle = SudokuString("A1B2..."),
 *         solution = SudokuString("1A2B..."),
 *         difficulty = Difficulty.MEDIUM,
 *         sudokuType = SudokuType.Sudoku4x4
 *     ))
 *
 *     // Add multiple schemas at once
 *     add(
 *         SudokuSchema(
 *             puzzle = SudokuString("B1C2..."),
 *             solution = SudokuString("2B3C..."),
 *             difficulty = Difficulty.HARD,
 *             sudokuType = SudokuType.Sudoku4x4
 *         ),
 *         SudokuSchema(
 *             puzzle = SudokuString("C1D2..."),
 *             solution = SudokuString("3C4D..."),
 *             difficulty = Difficulty.EASY,
 *             sudokuType = SudokuType.Sudoku4x4
 *         )
 *     )
 *
 *     // Add a list of schemas
 *     addAll(
 *         listOf(
 *             SudokuSchema(
 *                 puzzle = SudokuString("D1E2..."),
 *                 solution = SudokuString("4D5E..."),
 *                 difficulty = Difficulty.EASY,
 *                 sudokuType = SudokuType.Sudoku4x4
 *             ),
 *             SudokuSchema(
 *                 puzzle = SudokuString("E1F2..."),
 *                 solution = SudokuString("5E6F..."),
 *                 difficulty = Difficulty.MEDIUM,
 *                 sudokuType = SudokuType.Sudoku4x4
 *             )
 *         )
 *     )
 * }
 * ```
 *
 * @param builder A lambda with receiver of type [SudokuSchemas.Builder] used to configure the schemas.
 * @return A [SudokuSchemas] containing the schemas configured in the builder.
 *
 * @see SudokuSchemas
 * @see SudokuSchema
 * @see SudokuSchemas.Builder
 * @see SudokuSchemas.Builder.add
 * @see SudokuSchemas.Builder.addAll
 */
@SudoklifyDsl
public inline fun SudokuSchemas(
  builder: SudokuSchemas.Builder.() -> Unit,
): SudokuSchemas {
  return SudokuSchemas.Builder().apply(builder).build()
}

/**
 * Updates an existing [SudokuSchemas] using a [SudokuSchemas.Builder] configured with a DSL-style lambda.
 *
 * This function converts the existing [SudokuSchemas] into a [SudokuSchemas.Builder] object, applies the provided
 * lambda to configure the builder, and then builds a new [SudokuSchemas] with the updated schemas.
 *
 * **Example:**
 *
 * ```kotlin
 * val existingSet = SudokuSchemas(
 *     listOf(
 *         SudokuSchema(
 *             puzzle = SudokuString("A1B2..."),
 *             solution = SudokuString("1A2B..."),
 *             difficulty = Difficulty.MEDIUM,
 *             sudokuType = SudokuType.Sudoku4x4
 *         )
 *     )
 * )
 *
 * // Update the existing set with a new schema
 * val updatedSet = sudokuSchemas(existingSet) {
 *     add(SudokuSchema(
 *         puzzle = SudokuString("B1C2..."),
 *         solution = SudokuString("2B3C..."),
 *         difficulty = Difficulty.HARD,
 *         sudokuType = SudokuType.Sudoku4x4
 *     ))
 * }
 * ```
 *
 * @param existingSet The existing [SudokuSchemas] to be updated.
 * @param builder A lambda function that configures the [SudokuSchemas.Builder] with new schemas or modifications.
 * @return A new [SudokuSchemas] built from the updated configurations.
 *
 * @see SudokuSchemas
 * @see SudokuSchema
 * @see SudokuSchemas.Builder
 * @see SudokuSchemas.Builder.add
 * @see SudokuSchemas.Builder.addAll
 */
@SudoklifyDsl
public inline fun SudokuSchemas(
  existingSet: SudokuSchemas,
  builder: SudokuSchemas.Builder.() -> Unit,
): SudokuSchemas {
  return existingSet.toBuilder().apply(builder).build()
}

/**
 * Requires that there are Sudoku schemas for the given type.
 *
 * @param type The Sudoku type to check for.
 * @throws NoSchemasForTypeException if no schemas are found for the type.
 */
public fun SudokuSchemas.requireSchemasForType(type: Dimension) {
  if (findBySudokuType(type).isEmpty()) {
    throw NoSchemasForTypeException(type)
  }
}

/**
 * Adds multiple [SudokuSchema] instances to the set from an array.
 *
 * This function allows you to add a collection of [SudokuSchema] objects to the builder
 * at once. The schemas are converted to a list internally before being added.
 *
 * **Example usage:**
 * ```kotlin
 * val schemasArray = arrayOf(...)
 *
 * val schemas = sudokuSchemas {
 *   addAll(schemasArray)
 * }
 * ```
 *
 * @param schemas The array of [SudokuSchema] objects to add.
 * @return This [SudokuSchemas.Builder] instance for chaining.
 */
public fun SudokuSchemas.Builder.addAll(schemas: Array<SudokuSchema>) {
  addAll(schemas.toList())
}

/**
 * Requires that there are Sudoku schemas for the given difficulty.
 *
 * @param difficulty The difficulty level to check for.
 * @throws NoSchemasForDifficultyException if no schemas are found for the difficulty.
 */
public fun SudokuSchemas.requireSchemasForDifficulty(difficulty: Difficulty) {
  if (findByDifficulty(difficulty).isEmpty()) {
    throw NoSchemasForDifficultyException(difficulty)
  }
}

/**
 * Exception thrown when no Sudoku schemas are found for a given Sudoku type.
 */
public class NoSchemasForTypeException(
  type: Dimension,
) : Exception("No Sudoku schemas found for type: $type")

/**
 * Exception thrown when no Sudoku schemas are found for a given difficulty.
 */
public class NoSchemasForDifficultyException(
  difficulty: Difficulty,
) : Exception("No Sudoku schemas found for difficulty: $difficulty")
