# 🧩 Sudoklify 🧩

## Overview

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/dev.teogor.sudoklify/sudoklify-core.svg?label=Maven%20Central)](https://central.sonatype.com/search?q=g%3Adev.teogor.sudoklify+a%3Asudoklify-core&smo=true)
[![Profile](https://source.teogor.dev/badges/teogor-github.svg)](https://github.com/teogor)
[![Open-Source Directory](https://source.teogor.dev/badges/teogor-dev.svg)](https://source.teogor.dev)

## A Powerful Sudoku Puzzle Generation Library

🧩 Sudoklify is a versatile and intuitive Sudoku puzzle generation library written in Kotlin. It
provides a comprehensive set of tools and algorithms for generating Sudoku puzzles of various grid
sizes and difficulty levels.

## Key Features

- Efficient and customizable Sudoku puzzle generation algorithms ✨
- Support for different grid sizes, including 4x4, 9x9, 16x16, and more 📐
- Multiple difficulty levels - Easy, Medium, Hard, and Expert 🌟
- Flexible token mapping system for personalized puzzle representation 🎨
- Intuitive API for puzzle generation, population, and solution retrieval 🧩
- Randomization techniques for puzzle layout and digit placement 🔀
- Kotlin Multiplatform Support: Now available for JVM, JS(IR), WASM, iOS, macOS, Linux, TVOS, and WatchOS
  platforms 🌍

## Documentation

- [Solver Documentation](https://teogor.dev/sudoklify/solver) - Detailed guide on using the `solver` package, including use cases and examples.

## Sudoklify: Redefining Puzzle Generation

Sudoklify reimagines the way Sudoku puzzles are generated, delivering a lightning-fast experience
without compromising quality. Here's a glimpse into what makes Sudoklify stand out:

### Innovative Puzzle Creation

Traditional Sudoku generators rely on gradually removing numbers from a solved grid, often requiring
extensive time and backend resources. Sudoklify, however, operates differently. It starts with a
solvable "seed" puzzle and applies a series of ingenious transformations to craft new puzzles. This
approach ensures rapid puzzle creation without the need for a complex backend infrastructure.

### Unparalleled Puzzle Diversity

Every seed used by Sudoklify unlocks a staggering number of unique puzzles—over 2.4 trillion! To put
this into perspective, if you were to solve a puzzle every 3 minutes, it would take more than 13
million lifetimes to exhaust a single seed. 🎉

### Transformative Algorithms at Play

Sudoklify leverages a diverse set of transformations to create distinctive puzzles:

- **Board Rotation**: Incorporates four permutations (0°, 90°, 180°, 270°).
- **Column Stacks Shuffle**: Features six permutations (3!).
- **Row Bands Shuffle**: Offers another six permutations (3!).
- **Column Shuffle**: Provides a whopping 216 permutations (3! x 3! x 3!).
- **Row Shuffle**: Adds another 216 permutations (3! x 3! x 3!).
- **Number Swapping**: Introduces an incredible 362,880 permutations (9!).

Combining these transformations results in a staggering number of permutations per
seed—2,437,996,216,320. This extensive variety guarantees that each puzzle is a uniquely captivating
challenge. 🧠🧩🚀

## Usage

To generate Sudoku puzzles using Sudoklify, follow these steps:

1. **Initialize the Architect**: Start by creating a `SudoklifyArchitect` instance. This allows you
   to load preset schemas and optionally add your own custom schemas.

```kotlin
val architect = SudoklifyArchitect {
    SudokuSchemas(loadPresetSchemas()) {
        // TODO Optional: Add own schemas using add(*), addAll(*)
    }
}
```

2. **Define the Sudoku Specification**: Create a `SudokuSpec` to specify the puzzle's seed, grid
   dimension, and difficulty level.

```kotlin
val sudokuSpec = SudokuSpec {
  seed = 2024L.toSeed()
  type = Dimension.NineByNine
  difficulty = Difficulty.EASY
}
```

3. **Generate Sudoku Puzzles**: Use the `SudoklifyArchitect` to construct Sudoku puzzles based on
   the defined specifications.

```kotlin
val sudokuPuzzle1 = architect.constructSudoku(sudokuSpec)
val sudokuPuzzle2 = architect.constructSudoku {
  seed = 2025L.toSeed()
}
```

4. **Process and Print the Puzzles**: Iterate through the generated puzzles and print their grids.

```kotlin
val puzzles = listOf(sudokuPuzzle1, sudokuPuzzle2)
puzzles.forEach { puzzle ->
  println(puzzle.generateGridWithGivens().mapToSudokuString())
  println(puzzle.generateGridWithGivens().mapToSudokuString().mapToSudokuBoard(puzzle.type))
}
```

## Kotlin Multiplatform Support

Sudoklify is now available as a Kotlin Multiplatform project, supporting the following platforms:

- **JVM**: Full support for JVM with Kotlin toolchain version 11.
- **JavaScript (JS)**: Supports both browser and Node.js environments using Kotlin/JS IR backend.
- **WASM**: WebAssembly support for browser and Node.js environments.
- **iOS**: Native support for iOS devices, including x64, Arm64, and Simulator Arm64.
- **macOS**: Native support for macOS devices, including x64 and Arm64.
- **Linux**: Native support for Linux devices, including x64 and Arm64.
- **TVOS**: Native support for tvOS devices, including x64, Arm64, and Simulator Arm64.
- **WatchOS**: Native support for watchOS devices, including x64, Arm32, Arm64, Device Arm64, and
  Simulator Arm64.

## Contributing

Contributions to Sudoklify are welcome! If you have any ideas, bug reports, or feature requests,
please open an issue or submit a pull request. For more information, please refer to
our [Contributing Guidelines](CONTRIBUTING.md).

## Find this repository useful? :heart:

Support it by joining __[stargazers](https://github.com/teogor/sudoklify/stargazers)__ for this
repository. :star: <br>
Also, __[follow me](https://github.com/teogor)__ on GitHub for my next creations! 🤩

## License

```xml
  Designed and developed by 2023 teogor (Teodor Grigor)

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
```
