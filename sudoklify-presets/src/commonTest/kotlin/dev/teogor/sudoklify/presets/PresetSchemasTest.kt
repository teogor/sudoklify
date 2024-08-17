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

package dev.teogor.sudoklify.presets

import dev.teogor.sudoklify.ExperimentalSudoklifyApi
import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.mapToSudokuBoard
import dev.teogor.sudoklify.schema.NoSchemasForTypeException
import dev.teogor.sudoklify.schema.SudokuSchema
import dev.teogor.sudoklify.schema.requireSchemasForType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalSudoklifyApi::class)
class PresetSchemasTest {

  @Test
  fun loadSudoklifySchemas_initializesCorrectly() {
    val schemas = loadPresetSchemas()
    assertNotNull(schemas, "SudokuSchemas should be initialized correctly.")
    assertTrue(schemas.isNotEmpty(), "SudokuSchemas should not be empty.")
  }

  @Test
  fun loadSudoklifySchemas_containsExpectedNumberOfSchemas() {
    val schemas = loadPresetSchemas()
    assertTrue(
      schemas.findBySudokuType(Dimension.FourByFour).isNotEmpty(),
      "SudokuSchemas should contain 4x4 schemas.",
    )
    assertTrue(
      schemas.findBySudokuType(Dimension.NineByNine).isNotEmpty(),
      "SudokuSchemas should contain 9x9 schemas.",
    )
    assertTrue(
      schemas.findBySudokuType(Dimension.SixteenBySixteen).isNotEmpty(),
      "SudokuSchemas should contain 16x16 schemas.",
    )
    assertTrue(
      schemas.findBySudokuType(Dimension.TwentyFiveByTwentyFive).isNotEmpty(),
      "SudokuSchemas should contain 25x25 schemas.",
    )
  }

  @Test
  fun loadSudoklifySchemas_filtersByTypeCorrectly() {
    val schemas = loadPresetSchemas()
    val filteredSchemas = schemas.getSeedsBySize(Dimension.FourByFour)
    assertTrue(
      filteredSchemas.findBySudokuType(Dimension.FourByFour).isNotEmpty(),
      "Filtered schemas should contain only 4x4 Sudoku schemas.",
    )
  }

  @Test
  fun loadSudoklifySchemas_filtersByDifficultyCorrectly() {
    val schemas = loadPresetSchemas()
    val filteredSchemas = schemas.getSeedsByDifficulty(Difficulty.HARD)
    assertTrue(
      filteredSchemas.findByDifficulty(Difficulty.HARD).isNotEmpty(),
      "Filtered schemas should contain only hard difficulty Sudoku schemas.",
    )
  }

  @Test
  fun loadSudoklifySchemas_throwsExceptionForMissingType() {
    val schemas = loadPresetSchemas()
    assertFailsWith<NoSchemasForTypeException> {
      schemas.requireSchemasForType(Dimension.TwelveByTwelve)
    }
  }

  @Test
  fun loadSudoklifySchemas_copyWorksCorrectly() {
    val schemas = loadPresetSchemas()
    val copiedSchemas = schemas.copy()
    assertEquals(
      schemas,
      copiedSchemas,
      "Copied schemas should be equal to the original schemas.",
    )
  }

  @Test
  fun loadSudoklifySchemas_uniqueTypesCorrectlyIdentified() {
    val schemas = loadPresetSchemas()
    val uniqueTypes = schemas.getUniqueSudokuTypes()
    assertTrue(
      uniqueTypes.contains(Dimension.FourByFour),
      "Unique types should contain 4x4 Sudoku.",
    )
    assertTrue(
      uniqueTypes.contains(Dimension.NineByNine),
      "Unique types should contain 9x9 Sudoku.",
    )
  }

  @Test
  fun loadSudoklifySchemas_countsByDifficultyCorrectly() {
    val schemas = loadPresetSchemas()
    val counts = schemas.countByDifficulty()
    assertTrue(counts.containsKey(Difficulty.EASY), "Counts should include the EASY difficulty.")
    assertTrue(
      counts[Difficulty.EASY]!! > 0,
      "There should be at least one EASY difficulty Sudoku.",
    )
  }

  @Test
  fun sudokuSchema_toSudokuBoard_shouldMapCorrectlyForEachType() {
    val schemas = loadPresetSchemas()
    schemas
      .groupBy {
        it.dimension
      }
      .forEach { (type, schemas) ->
        // Test mapping for each SudokuType
        schemas.forEach { sudokuSchema: SudokuSchema ->
          val solutionBoard = sudokuSchema.solution.value
            .mapToSudokuBoard(type)
          val puzzleBoard = sudokuSchema.puzzle.value
            .mapToSudokuBoard(type)

          // Add assertions to verify that the boards are correctly mapped
          assertNotNull(solutionBoard, "Solution board should not be null for type $type")
          assertNotNull(puzzleBoard, "Puzzle board should not be null for type $type")

          // Optionally, add more detailed checks to validate the board's correctness
          assertTrue(
            solutionBoard.isNotEmpty(),
            "Solution board should be valid for SudokuType $type"
          )
          assertTrue(
            puzzleBoard.isNotEmpty(),
            "Puzzle board should be valid for SudokuType $type"
          )
        }
      }
  }
}
