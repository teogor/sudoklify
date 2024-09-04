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

import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

class SudokuSchemasTest {
  private val schema1 =
    SudokuSchema(
      puzzle = "E-Aj",
      solution = "EBAj",
      difficulty = Difficulty.MEDIUM,
      dimension = Dimension.FourByFour,
    )

  private val schema2 =
    SudokuSchema(
      puzzle = "EAiB-",
      solution = "EAiBC",
      difficulty = Difficulty.HARD,
      dimension = Dimension.FourByFour,
    )

  @Test
  fun createSet_shouldContainAllElements() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    assertEquals(2, schemas.size, "Set should contain all provided schemas")
    assertTrue(schemas.contains(schema1), "Set should contain schema1")
    assertTrue(schemas.contains(schema2), "Set should contain schema2")
  }

  @Test
  fun iterate_shouldReturnAllElements() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    assertEquals(2, schemas.size, "List should contain all schemas")
    assertTrue(schemas.contains(schema1), "List should contain schema1")
    assertTrue(schemas.contains(schema2), "List should contain schema2")
  }

  @Test
  fun copy_shouldCreateNewInstance() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    val copiedSet = schemas.copy()
    assertEquals(schemas, copiedSet, "Copied set should have the same content")
    assertNotSame(schemas, copiedSet, "Copied set should be a new instance")
  }

  @Test
  fun toBuilder_shouldAllowModifications() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    val builder = schemas.toBuilder()
    val newSet = builder.build()
    assertEquals(schemas, newSet, "New set should initially be the same")
    assertNotSame(schemas, newSet, "Builder should create a new instance")
  }

  @Test
  fun dslCreation_shouldAddElements() {
    val schemas =
      SudokuSchemas {
        add(schema1)
        add(schema2)
      }
    assertEquals(2, schemas.size, "Set should contain all added schemas")
    assertTrue(schemas.contains(schema1), "Set should contain schema1")
    assertTrue(schemas.contains(schema2), "Set should contain schema2")
  }

  @Test
  fun dslUpdate_shouldModifyExistingSet() {
    val initialSet = SudokuSchemas(listOf(schema1))
    val updatedSet =
      SudokuSchemas(initialSet) {
        add(schema2)
      }
    assertEquals(2, updatedSet.size, "Updated set should have one more schema")
    assertTrue(updatedSet.contains(schema1), "Updated set should still contain schema1")
    assertTrue(updatedSet.contains(schema2), "Updated set should now contain schema2")
  }

  @Test
  fun equality_shouldMatchEquivalentSets() {
    val set1 = SudokuSchemas(listOf(schema1, schema2))
    val set2 = SudokuSchemas(listOf(schema1, schema2))
    val set3 = SudokuSchemas(listOf(schema2, schema1))

    assertEquals(set1, set2, "Sets with the same elements should be equal")
    assertEquals(set1.hashCode(), set2.hashCode(), "Equal sets should have the same hash code")
    assertNotEquals(set1, set3, "Sets with different order should not be equal")
  }

  @Test
  fun toString_shouldShowContent() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    val expectedString = "SudokuSchemas(schemas=[$schema1, $schema2])"
    assertEquals(expectedString, schemas.toString(), "toString should represent set content")
  }

  @Test
  fun findByDifficulty_shouldFindMatchingSchemas() {
    val schemas = SudokuSchemas(listOf(schema1))
    val easySchemas = schemas.findByDifficulty(Difficulty.MEDIUM)
    assertEquals(1, easySchemas.size, "Should find one medium schema")
    assertTrue(
      easySchemas.all { it.difficulty == Difficulty.MEDIUM },
      "All schemas should be medium",
    )
  }

  @Test
  fun findBySudokuType_shouldFindMatchingSchemas() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    val standardSchemas = schemas.findBySudokuType(Dimension.FourByFour)
    assertEquals(2, standardSchemas.size, "Should find two standard schemas")
    assertTrue(
      standardSchemas.all {
        it.dimension == Dimension.FourByFour
      },
      "All schemas should be standard",
    )
  }

  @Test
  fun getUniqueSudokuTypes_shouldFindUniqueTypes() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    val uniqueTypes = schemas.getUniqueSudokuTypes()
    assertEquals(1, uniqueTypes.size, "Should find one unique sudoku types")
    assertTrue(uniqueTypes.contains(Dimension.FourByFour), "Sudoku4x4 type should be present")
  }

  @Test
  fun countByDifficulty_shouldCountSchemasPerDifficulty() {
    val schemas = SudokuSchemas(listOf(schema1, schema2))
    val difficultyCounts = schemas.countByDifficulty()
    assertEquals(2, difficultyCounts.size, "Should have counts for both difficulties")
    assertEquals(1, difficultyCounts[Difficulty.HARD], "Hard difficulty should have 1 schema")
    assertEquals(1, difficultyCounts[Difficulty.MEDIUM], "Medium difficulty should have 1 schema")
  }

  @Test
  fun plusOperator_shouldAddSingleSchema() {
    val initialSet = SudokuSchemas(listOf(schema1))
    val newSet = initialSet + schema2
    assertEquals(2, newSet.size, "New set should contain both schemas")
    assertTrue(newSet.contains(schema1), "New set should contain schema1")
    assertTrue(newSet.contains(schema2), "New set should contain schema2")
  }

  @Test
  fun plusOperator_shouldAddCollectionOfSchemas() {
    val initialSet = SudokuSchemas(listOf(schema1))
    val schemasToAdd = listOf(schema2)
    val newSet = initialSet + schemasToAdd
    assertEquals(2, newSet.size, "New set should contain both schemas")
    assertTrue(newSet.contains(schema1), "New set should contain schema1")
    assertTrue(newSet.contains(schema2), "New set should contain schema2")
  }

  @Test
  fun plusOperator_shouldAddAnotherSudokuSchemasCollection() {
    val initialSet = SudokuSchemas(listOf(schema1))
    val anotherSet = SudokuSchemas(listOf(schema2))
    val combinedSet = initialSet + anotherSet
    assertEquals(2, combinedSet.size, "Combined set should contain both schemas")
    assertTrue(combinedSet.contains(schema1), "Combined set should contain schema1")
    assertTrue(combinedSet.contains(schema2), "Combined set should contain schema2")
  }

  @Test
  fun plusOperator_shouldNotModifyOriginalSets() {
    val initialSet = SudokuSchemas(listOf(schema1))
    val anotherSet = SudokuSchemas(listOf(schema2))
    val combinedSet = initialSet + anotherSet

    // Ensure original sets remain unchanged
    assertEquals(1, initialSet.size, "Original set should still contain one schema")
    assertEquals(1, anotherSet.size, "Another set should still contain one schema")
    assertEquals(2, combinedSet.size, "Combined set should contain both schemas")
  }

  @Test
  fun plusOperator_shouldHandleEmptySets() {
    val emptySet = SudokuSchemas(emptyList())
    val initialSet = SudokuSchemas(listOf(schema1))

    val resultSet1 = emptySet + schema1
    assertEquals(1, resultSet1.size, "Resulting set should contain one schema")

    val resultSet2 = emptySet + initialSet
    assertEquals(1, resultSet2.size, "Resulting set should contain one schema")

    val resultSet3 = initialSet + emptySet
    assertEquals(1, resultSet3.size, "Resulting set should contain one schema")
    assertTrue(resultSet3.contains(schema1), "Resulting set should contain schema1")
  }

  @Test
  fun plusOperator_shouldAddSingleSchemaAndRemoveDuplicates() {
    val duplicateSchema = schema1.copy()
    val initialSet = SudokuSchemas(listOf(schema1))
    val newSet = initialSet + duplicateSchema
    assertEquals(1, newSet.size, "New set should contain only one unique schema")
    assertTrue(newSet.contains(schema1), "New set should contain schema1")
  }

  @Test
  fun plusOperator_shouldAddCollectionOfSchemasAndRemoveDuplicates() {
    val duplicateSchema = schema2.copy()
    val initialSet = SudokuSchemas(listOf(schema1))
    val schemasToAdd = listOf(schema2, duplicateSchema)
    val newSet = initialSet + schemasToAdd
    assertEquals(2, newSet.size, "New set should contain only unique schemas")
    assertTrue(newSet.contains(schema1), "New set should contain schema1")
    assertTrue(newSet.contains(schema2), "New set should contain schema2")
  }

  @Test
  fun plusOperator_shouldAddAnotherSudokuSchemasCollectionAndRemoveDuplicates() {
    val duplicateSchema = schema1.copy()
    val initialSet = SudokuSchemas(listOf(schema1))
    val anotherSet = SudokuSchemas(listOf(schema2, duplicateSchema))
    val combinedSet = initialSet + anotherSet
    assertEquals(2, combinedSet.size, "Combined set should contain only unique schemas")
    assertTrue(combinedSet.contains(schema1), "Combined set should contain schema1")
    assertTrue(combinedSet.contains(schema2), "Combined set should contain schema2")
  }

  @Test
  fun plusOperator_shouldHandleEmptySetsAndRemoveDuplicates() {
    val emptySet = SudokuSchemas(emptyList())
    val initialSet = SudokuSchemas(listOf(schema1))

    val resultSet1 = emptySet + schema1
    assertEquals(1, resultSet1.size, "Resulting set should contain one schema")

    val resultSet2 = emptySet + initialSet
    assertEquals(1, resultSet2.size, "Resulting set should contain one schema")

    val resultSet3 = initialSet + emptySet
    assertEquals(1, resultSet3.size, "Resulting set should contain one schema")
    assertTrue(resultSet3.contains(schema1), "Resulting set should contain schema1")
  }

  @Test
  fun plusOperator_shouldRemoveAllDuplicates() {
    val duplicateSchema1 = schema1.copy()
    val duplicateSchema2 = schema2.copy()
    val initialSet = SudokuSchemas(listOf(schema1, schema2))
    val anotherSet = SudokuSchemas(listOf(duplicateSchema1, duplicateSchema2))
    val combinedSet = initialSet + anotherSet
    assertEquals(2, combinedSet.size, "Combined set should only contain unique schemas")
    assertTrue(combinedSet.contains(schema1), "Combined set should contain schema1")
    assertTrue(combinedSet.contains(schema2), "Combined set should contain schema2")
  }
}
