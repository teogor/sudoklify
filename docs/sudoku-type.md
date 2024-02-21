# Sudoku Type Documentation

This document aims to provide a comprehensive overview of the `SudokuType` class present in
the `dev.teogor.sudoklify.common.types` package. This class serves as a foundation for representing
various Sudoku puzzle sizes and their associated properties.

## **Key Features**

* **Encapsulated Grid Size:** Each `SudokuType` object encapsulates the grid size
  information (`width` and `height`) through its `gridSize` property. This ensures clarity and
  consistency in representing different Sudoku variations.
* **Derived Properties:** Several properties provide convenient access to derived information based
  on the grid size, such as `digits` (total number of cells), `cells` (total cells in the
  grid), `isSquare` (whether the grid is square), `width`, and `height`.
* **Human-Readable Name:** Each `SudokuType` object holds a `name` property, presenting a
  user-friendly representation like "4x4" for clarity and easy identification.
* **Dedicated Objects:** Individual objects represent common Sudoku sizes,
  including `Sudoku4x4`, `Sudoku6x6`, `Sudoku9x9`, and many more, offering convenient access to
  specific grid dimensions.
* **`Unspecified` Object:** To handle cases where the Sudoku size is unknown or unspecified,
  an `Unspecified` object exists within the class.

## **Working with Sudoku Types**

### **Benefits of Using SudokuType**

* **Type Safety:** Enforces correct grid size representation and prevents errors or unexpected
  behavior.
* **Clarity and Maintainability:** Improves code readability and simplifies maintenance thanks to
  well-defined types and properties.
* **Flexibility:** Accommodates various Sudoku sizes while ensuring consistency and ease of use.

### **Creating Sudoku Types**

#### 1. **Accessing Existing Objects**

Utilize the predefined objects like `Sudoku4x4` or `Sudoku9x9` for commonly used sizes:

```kotlin
val puzzle4x4: SudokuType = SudokuType.Sudoku4x4
val puzzle9x9: SudokuType = SudokuType.Sudoku9x9
```

#### 2. **Creating a Custom Objects**

For unsupported sizes, create a new instance using the `GridSize` constructor and assign it to a
variable of type `SudokuType`:

```kotlin
val customSize: SudokuType = SudokuType(GridSize(5, 8))
println("Custom Sudoku size: ${customSize}")
```

### **Utilizing `SudokuType` Properties**

Directly access various properties available on any `SudokuType` object to gain insights into the
grid size and structure. These properties offer convenient ways to work with different Sudoku
variations.

#### 1. `SudokuType.width` and `SudokuType.height`

- **Description:** These properties directly represent the dimensions of the Sudoku grid, providing
  the width and height in individual integer values.
- **Use Cases:**
  * Determine the total number of cells in the grid (`width * height`).
  * Calculate the size of individual boxes within the grid (`boxWidth = width / boxCount`,
    where `boxCount` is the number of boxes horizontally or vertically).
  * Create a visual representation of the grid based on its dimensions.
- **Example:**

```kotlin
val puzzleSize = SudokuType.Sudoku6x6
println("Grid size: ${puzzleSize.width}x${puzzleSize.height}") // Output: Grid size: 6x6
```

- **Use Cases:**
  - Determine the total number of rows and columns in the grid.
  - Define constraints for placing elements based on grid dimensions.
  - Calculate box sizes within the grid (using `boxWidth` and `boxHeight`).

#### 2. `SudokuType.digits`

- **Description:** This property represents the total number of digits used in the Sudoku puzzle,
  calculated as `width * height`.
- **Use Cases:**
  * Validate the number of digits present in a puzzle to ensure it adheres to Sudoku rules.
  * Determine the permissible range of values that can be used in each cell (typically 1
    to `digits`).
  * Calculate the number of unique values needed in a solved puzzle (`digits * digits`).
- **Example:**

```kotlin
val puzzleCells = SudokuType.Sudoku9x9.digits
println("Total cells: $puzzleCells") // Output: Total cells: 81
```

#### 3. `SudokuType.cells`

- **Description:** This property provides the total number of cells in the Sudoku grid, calculated
  as `digits * digits`.
- **Use Cases:**
  * Iterate through all cells in the grid using loops or indexing based on `cells`.
  * Check if a specific cell index falls within the valid range (0 to `cells - 1`).
  * Determine the completion status of a puzzle by counting filled cells.

```kotlin
val puzzle = SudokuType.Sudoku9x9 // 9x9 Sudoku with 81 cells

// Iterate through all cells using a loop:
for (cellIndex in 0..<puzzle.cells) {
  // Perform operations on each cell based on its index
  println("Processing cell: $cellIndex")
}

// Check if a specific cell index is valid:
val targetCell = 42 // Cell in row 5, column 6
if (targetCell in 0..<puzzle.cells) {
  println("Cell $targetCell is within the valid range")
} else {
  println("Cell $targetCell is out of bounds")
}

// Simulate counting filled cells (replace with actual logic):
var filledCells = 0
for (cellIndex in 0..<puzzle.cells) {
  if (isCellFilled(cellIndex)) {
    // Replace with your logic to check if cell is filled
    filledCells++
  }
}
println("Filled cells: $filledCells")
```

#### 4. `SudokuType.isSquare`

- **Description:** This boolean property indicates whether the Sudoku grid is square,
  meaning `width == height`.
- **Use Cases:**
  * Apply different logic or algorithms depending on whether the grid is square or rectangular.
  * Optimize calculations or data structures based on the grid shape.
  * Determine the number of boxes in each row or column (equal to `width` or `height` if square).
- **Example:**

```kotlin
val isSquareSudoku = SudokuType.Sudoku4x4.isSquare
println("Is square: $isSquareSudoku") // Output: Is square: true

val isNotSquareSudoku = SudokuType.Sudoku6x6.isSquare
println("Is square: $isNotSquareSudoku") // Output: Is square: false
```

- **Use Cases:**
  - Apply specific algorithms or validation rules based on grid shape.
  - Optimize computations or data structures for square grids.
  - Differentiate between different Sudoku types based on their shape.

#### 5. `SudokuType.boxWidth` and `SudokuType.boxHeight`

- **Description:** These properties represent the width and height of a single box within the grid,
  calculated as `width / boxCount` and `height / boxCount`, respectively.
- **Use Cases:**
  * Identify the group of cells belonging to the same box based on their position and box
    dimensions.
  * Enforce Sudoku rules within boxes, ensuring no digit is repeated in any box.
  * Visualize the box structure of the grid for better understanding.
- **Example:**

```kotlin
val puzzle: SudokuType = SudokuType.Sudoku9x9 // 9x9 Sudoku with 3x3 boxes

val boxWidth = puzzle.boxWidth
val boxHeight = puzzle.boxHeight

println("Box size: ${boxWidth}x${boxHeight}") // Output: Box size: 3x3
```

#### 6. `SudokuType.name`

- **Description:** This property provides a human-readable name for the Sudoku type, formatted as "
  width x height" (e.g., "4x4" or "9x9").
- **Use Cases:**
  * Log or display information about the Sudoku type in a user-friendly way.
  * Differentiate between different Sudoku types when working with multiple puzzles.
  * Associate puzzle data with specific Sudoku types for targeted algorithms or strategies.
- **Example:**

```kotlin
val sudokuName = SudokuType.Sudoku16x16.name
println("Sudoku name: $sudokuName") // Output: Sudoku name: 16x16
```

- **Use Cases:**
  - Display user-friendly information about the Sudoku puzzle being generated or solved.
  - Log or store information about different Sudoku types being handled.
  - Differentiate between different Sudoku types in text output or user interfaces.

## **Working with Sudoku Types Extensions by Use Case**

These `SudokuType` extensions offer convenient methods for various common operations related to
Sudoku grids. They are categorized by their primary use cases:

### **Analyzing Grid Structure**

* **`SudokuType.getAllDigits()`**
  * **Use Case:** Retrieve the valid range of digits (1 to `digits`) for the specific Sudoku type.
  * **Params:** None
  * **Example:**
      ```kotlin
      val puzzle = SudokuType.Sudoku9x9
      // [1, 2, 3, 4, 5, 6, 7, 8, 9]
      val validDigits = puzzle.getAllDigits()
      ```
* **`SudokuType.isDigitValid(digit: Int)`**
  * **Use Case:** Check if a given digit is allowed within the Sudoku's valid range.
  * **Params:** `digit: Int` (value to check)
  * **Example:**
      ```kotlin
      val isValid = puzzle.isDigitValid(3) // True
      val isInvalid = puzzle.isDigitValid(10) // False
      ```

### **Accessing Cell Information**

* **`SudokuType.getCellRowIndex(cellIndex: Int)`**
  * **Use Case:** Get the row index (0 to `height - 1`) for a given cell index.
  * **Params:** `cellIndex: Int` (index of the cell)
  * **Example:**
      ```kotlin
      // 2 (row index for cell 8 in a 4x4 Sudoku)
      val rowIndex = puzzle.getCellRowIndex(8)
      ```

* **`SudokuType.getCellColumnIndex(cellIndex: Int)`**
  * **Use Case:** Get the column index (0 to `width - 1`) for a given cell index.
  * **Params:** `cellIndex: Int` (index of the cell)
  * **Example:**
      ```kotlin
      // 5 (column index for cell 17 in a 6x6 Sudoku)
      val colIndex = puzzle.getCellColumnIndex(17)
      ```

### **Working with Boxes**

* **`SudokuType.getBoxIndex(row: Int, col: Int)`**
  * **Use Case:** Get the box index (0 to `boxes - 1`) for a cell at a specific row and column.
  * **Params:** `row: Int`, `col: Int` (cell coordinates)
  * **Example:**
      ```kotlin
      // 4 (box index for cell at row 3, col 5 in a 9x9 Sudoku)
      val boxIndex = puzzle.getBoxIndex(3, 5)
      ```

* **`SudokuType.getBoxCoordinates(row: Int, col: Int)`**
  * **Use Case:** Retrieve the top-left and bottom-right coordinates of the box containing a cell.
  * **Params:** `row: Int`, `col: Int` (cell coordinates)
  * **Example:**
      ```kotlin
      // BoxCoordinates(topLeftCol=18, topLeftRow=18, bottomRightCol=20, bottomRightRow=20)
      val coordinates = puzzle.getBoxCoordinates(2, 7)
      ```

* **`SudokuType.getCellBoxRowIndex(cellIndex: Int)`**
  * **Use Case:** Get the box row index (0 to `boxes / boxHeight - 1`) for a given cell index.
  * **Params:** `cellIndex: Int` (index of the cell)
  * **Example:**
      ```kotlin
      // 1 (box row index for cell 42 in a 9x9 Sudoku)
      val boxRowIndex = puzzle.getCellBoxRowIndex(42)
      ```

* **`SudokuType.getCellBoxColumnIndex(cellIndex: Int)`**
  * **Use Case:** Get the box column index (0 to `boxes / boxWidth - 1`) for a given cell index.
  * **Params:** `cellIndex: Int` (index of the cell)
  * **Example:**
      ```kotlin
      // 5 (box column index for cell 99 in a 16x16 Sudoku)
      val boxColIndex = puzzle.getCellBoxColumnIndex(99)
      ```

### **Checking Cell Relationships**

* **`SudokuType.areCellsInSameRow(cellIndex1: Int, cellIndex2: Int)`**
  * **Use Case:** Determine if two cells share the same row based on their indices.
  * **Params:** `cellIndex1: Int`, `cellIndex2: Int` (indices of the cells)
  * **Example:**
      ```kotlin
      val puzzle = SudokuType.Sudoku9x9
      val cell1Index = 10 // Cell in row 1, column 1
      val cell2Index = 18 // Cell in row 2, column 2

      val areInSameRow = puzzle.areCellsInSameRow(cell1Index, cell2Index) // False

      println(areInSameRow) // Output: false
      ```

* **`SudokuType.areCellsInSameColumn(cellIndex1: Int, cellIndex2: Int)`**
  * **Use Case:** Determine if two cells share the same column based on their indices.
  * **Params:** `cellIndex1: Int`, `cellIndex2: Int` (indices of the cells)
  * **Example:**
      ```kotlin
      // True (both cells in column 2 of a 4x4 Sudoku)
      val areInSameCol = puzzle.areCellsInSameColumn(10, 15)
      ```

* **`SudokuType.areCellsInSameBox(cellIndex1: Int, cellIndex2: Int)`**
  * **Use Case:** Determine if two cells belong to the same box based on their indices.
  * **Params:** `cellIndex1: Int`, `cellIndex2: Int` (indices of the cells)
  * **Example:**
      ```kotlin
      // True (both cells in the middle box of a 9x9 Sudoku)
      val areInSameBox = puzzle.areCellsInSameBox(27, 31)
      ```

* **`SudokuType.areCellsRelated(row1: Int, col1: Int, row2: Int, col2: Int)`**
  * **Use Case:** Check if two cells are related (in the same row, column, or box) based on their
    individual row and column indices.
  * **Params:** `row1: Int`, `col1: Int`, `row2: Int`, `col2: Int` (coordinates of both cells)
  * **Example:**
      ```kotlin
      // True (cells are in the same box in a 9x9 Sudoku)
      val areRelated = puzzle.areCellsRelated(5, 3, 2, 7)
      ```

### **References**

* [Source code for `SudokuType` class](https://github.com/teogor/sudoklify/blob/main/sudoklify-common/src/main/kotlin/dev/teogor/sudoklify/common/types/SudokuType.kt){:target="_blank"}
* [API documentation for `SudokuType` class](../html/sudoklify-common/dev.teogor.sudoklify.common.types/-sudoku-type/index.html){:target="_blank"}
* [Source code for `SudokuTypeExtensions`](https://github.com/teogor/sudoklify/blob/main/sudoklify-ktx/src/main/kotlin/dev/teogor/sudoklify/ktx/SudokuTypeExtensions.kt){:target="_blank"}
* [API documentation for `SudokuTypeExtensions`](../html/sudoklify-ktx/dev.teogor.sudoklify.ktx/index.html){:target="_blank"}
* [Full `sudoklify` library documentation](../html){:target="_blank"}
