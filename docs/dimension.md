# Sudoku Dimension Documentation

This document provides an in-depth overview of the `Dimension` class within the
`dev.teogor.sudoklify.components` package. This class is essential for representing various Sudoku
puzzle sizes and their associated properties.

## **Key Features**

- **Encapsulated Grid Size:** The `Dimension` class encapsulates grid size information through its
  `gridSize` property, including the grid's `width` and `height`. This encapsulation ensures clarity
  and consistency when representing different Sudoku variants.

- **Derived Properties:** The class provides convenient access to several derived properties based
  on the grid size, such as:
  - `uniqueDigitsCount`: Total number of distinct digits.
  - `totalCells`: Total number of cells in the grid.
  - `isSquare`: Indicates if the grid is square.
  - `width` and `height`: Dimensions of the grid.

- **Human-Readable Name:** The `name` property provides a user-friendly representation like "4x4"
  for easy identification of the Sudoku grid dimensions.

- **Dedicated Objects:** Predefined objects are available for common Sudoku sizes, including
  `FourByFour`, `SixBySix`, `NineByNine`, and more, enabling quick access to specific grid
  dimensions.

- **`Unspecified` Object:** An `Unspecified` object exists to handle cases where the Sudoku size is
  unknown or unspecified.

## **Advantages of Using `Dimension`**

### **Dimension Safety**

The `Dimension` class enforces correct grid size representation, preventing errors or unexpected
behavior in Sudoku applications.

### **Clarity and Maintainability**

This class improves code readability and simplifies maintenance by providing well-defined types and
properties.

### **Flexibility**

The class supports various Sudoku sizes while ensuring consistency and ease of use, accommodating
custom and standard grid dimensions.

## **Using the `Dimension` Class**

### **Accessing Predefined Objects**

Utilize predefined objects like `FourByFour` or `NineByNine` for standard Sudoku grid sizes:

```kotlin
val puzzle4x4: Dimension = Dimension.FourByFour
val puzzle9x9: Dimension = Dimension.NineByNine
```

### **Creating Custom Dimensions**

For unsupported sizes, create a new instance using the `GridSize` constructor:

```kotlin
val customSize: Dimension = Dimension(GridSize(5, 8))
println("Custom Sudoku size: ${customSize}")
```

### **Accessing `Dimension` Properties**

The `Dimension` class provides a range of properties to access grid-related information.

#### **1. `Dimension.width` and `Dimension.height`**

These properties represent the grid's dimensions:

- **Example:**

```kotlin
val puzzleSize = Dimension.SixBySix
println("Grid size: ${puzzleSize.width}x${puzzleSize.height}") // Output: Grid size: 6x6
```

#### **2. `Dimension.uniqueDigitsCount`**

This property provides the total number of unique digits in the Sudoku grid:

- **Example:**

```kotlin
val puzzleDigits = Dimension.NineByNine.uniqueDigitsCount
println("Total unique digits: $puzzleDigits") // Output: Total unique digits: 81
```

#### **3. `Dimension.totalCells`**

This property returns the total number of cells in the grid:

- **Example:**

```kotlin
val puzzle = Dimension.NineByNine

for (cellIndex in 0..<puzzle.totalCells) {
  println("Processing cell: $cellIndex")
}
```

#### **4. `Dimension.isSquare`**

This boolean property indicates whether the grid is square:

- **Example:**

```kotlin
val isSquareSudoku = Dimension.FourByFour.isSquare
println("Is square: $isSquareSudoku") // Output: Is square: true
```

#### **5. `Dimension.boxWidth` and `Dimension.boxHeight`**

These properties represent the dimensions of a single box within the grid:

- **Example:**

```kotlin
val puzzle = Dimension.NineByNine
val boxWidth = puzzle.boxWidth
val boxHeight = puzzle.boxHeight
println("Box size: ${boxWidth}x${boxHeight}") // Output: Box size: 3x3
```

#### **6. `Dimension.name`**

This property provides a human-readable name for the Sudoku dimension:

- **Example:**

```kotlin
val sudokuName = Dimension.SixteenBySixteen.name
println("Sudoku name: $sudokuName") // Output: Sudoku name: 16x16
```

## **Working with Sudoku Types**

### **Grid Analysis Extensions**

The `Dimension` class provides several methods to analyze the grid structure.

- **`getAllDigits()`**: Returns the valid range of digits (1 to `uniqueDigitsCount`).
- **`isDigitValid(digit: Int)`**: Checks if a given digit is valid within the Sudoku's range.

### **Accessing Cell Information**

- **`getCellRowIndex(cellIndex: Int)`**: Returns the row index for a given cell.
- **`getCellColumnIndex(cellIndex: Int)`**: Returns the column index for a given cell.

### **Working with Boxes**

- **`getBoxIndex(row: Int, col: Int)`**: Returns the box index for a cell at a specific row and
  column.
- **`getBoxCoordinates(row: Int, col: Int)`**: Returns the coordinates of the top-left and
  bottom-right corners of the box containing a cell.

### **Cell Relationships**

- **`areCellsInSameRow(cellIndex1: Int, cellIndex2: Int)`**: Checks if two cells share the same row.
- **`areCellsInSameColumn(cellIndex1: Int, cellIndex2: Int)`**: Checks if two cells share the same
  column.
- **`areCellsInSameBox(cellIndex1: Int, cellIndex2: Int)`**: Checks if two cells are in the same
  box.
- **`areCellsRelated(row1: Int, col1: Int, row2: Int, col2: Int)`**: Checks if two cells are
  related (in the same row, column, or box).

## **References**

* [Source code for `Dimension` class](https://github.com/teogor/sudoklify/blob/main/sudoklify-common/src/main/kotlin/dev/teogor/sudoklify/components/Dimension.kt){:target="_blank"}
* [API documentation for `Dimension` class](../html/sudoklify-common/dev.teogor.sudoklify.components/-dimension/index.html){:target="_blank"}
* [Full `sudoklify` library documentation](../html){:target="_blank"}
