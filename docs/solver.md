# Sudoklify Solver - Documentation

## Overview

**Sudoklify** is a powerful Kotlin library designed to assist in creating and solving Sudoku puzzles. The `solver` package provides a robust set of tools to help developers implement mistake-checking, suggest potential moves, and solve puzzles efficiently. This document outlines the key use cases of the solver package, providing practical examples to help you integrate it into your project.

## Table of Contents

- [Getting Started](#getting-started)
- [Core Components](#core-components)
  - [MistakeCheckingMode](#mistakecheckingmode)
  - [SudoklifyMoveAdvisor](#sudoklifymoveadvisor)
  - [SudoklifySolverEngine](#sudoklifysolverengine)
  - [SudokuGridProcessor](#sudokugridprocessor)
  - [SudokuMove](#sudokumove)
- [Use Cases](#use-cases)
  - [Checking for Mistakes](#checking-for-mistakes)
  - [Suggesting the Next Move](#suggesting-the-next-move)
  - [Solving a Sudoku Puzzle](#solving-a-sudoku-puzzle)
- [Examples](#examples)
  - [Basic Mistake Checking](#basic-mistake-checking)
  - [Move Suggestion](#move-suggestion)
  - [Custom Grid Processor](#custom-grid-processor)
- [License](#license)

## Getting Started

To start using Sudoklify in your project, include the dependency in your build file:

```kotlin
dependencies {
    implementation("dev.teogor.sudoklify:sudoklify-solver:$version")
}
```

## Core Components

### MistakeCheckingMode

`MistakeCheckingMode` is an enum that defines the modes for mistake checking:

- `NoChecking`: Disables mistake checking.
- `CheckViolations`: Checks for rule violations without comparing to the final solution.
- `CheckAgainstSolution`: Compares input directly against the final solution.

### SudoklifyMoveAdvisor

`SudoklifyMoveAdvisor` helps in suggesting the next possible move by analyzing the grid and identifying cells where only one value is possible.

### SudoklifySolverEngine

`SudoklifySolverEngine` is the core engine for solving Sudoku puzzles. It combines mistake checking, move advising, and other utilities to provide a comprehensive solution for Sudoku gameplay.

### SudokuGridProcessor

`SudokuGridProcessor` is an interface for processing grids. It allows conversion between different cell types, checking for mistakes, and updating grids.

### SudokuMove

`SudokuMove` represents a move suggestion in the puzzle, indicating the row, column, and value to be placed.

## Use Cases

### Checking for Mistakes

Using `SudoklifySolverEngine`, you can check for mistakes in the player's input. The engine will identify errors based on the selected `MistakeCheckingMode`.

### Suggesting the Next Move

The `SudoklifyMoveAdvisor` can suggest the next optimal move for the player, especially when they are stuck. This feature is useful for providing hints during gameplay.

### Solving a Sudoku Puzzle

The `SudoklifySolverEngine` can solve the entire puzzle based on the given grid and rules. This is particularly useful in automated testing or hint systems.

## Examples

### Basic Mistake Checking

```kotlin
val gridProcessor = createSudokuGridProcessor(
    getValue = { cell -> cell.value },
    isLocked = { cell -> cell.isLocked },
    getSolution = { cell -> cell.solution },
    isError = { cell -> cell.isError },
    updateCell = { row, col, state, cell -> cell.copy(isError = state.isError) }
)

val puzzle: SudokuPuzzle = // Initialize with puzzle data

val solverEngine = SudoklifySolverEngine(gridProcessor, puzzle)

val checkedGrid = solverEngine.processGridMistakes(currentGrid)
```

### Move Suggestion

```kotlin
val advisor = SudoklifyMoveAdvisor(dimension = puzzle.dimension)
val nextMove = advisor.suggestNextMove(currentGrid)

if (nextMove != null) {
    println("Suggested move: Place ${nextMove.value} at row ${nextMove.row}, column ${nextMove.col}")
} else {
    println("No move suggestion available.")
}
```
