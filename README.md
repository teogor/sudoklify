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

### Generating Sudoku Puzzles

To generate Sudoku puzzles using Sudoklify, follow these steps:

1. Add Sudoklify as a dependency in your project.

2. Create a `ParamsBuilder` to configure the puzzle generation parameters.

```kotlin
import dev.teogor.sudoklify.sudokuParamsBuilder
import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType

// Configure puzzle generation parameters
val sudokuParams = sudokuParamsBuilder {
  difficulty { Difficulty.MEDIUM }
  seed { createSeed(2024) }
  type { GameType.ThreeByThree }
}
```

3. Generate the Sudoku puzzle using the `generateSudoku` extension function.

```kotlin
import dev.teogor.sudoklify.extensions.generateSudoku

val sudoku = sudokuParams.generateSudoku()
```

### Accessing Generated Sudoku

The `generatedSudoku` instance contains the puzzle and solution strings, difficulty level, and grid
type. You can access these properties as follows:

```kotlin
val puzzleBoard = sudoku.puzzle
val solutionBoard = sudoku.solution
val difficulty = sudoku.difficulty
val gameType = sudoku.gameType

// Print the properties
println("Puzzle Board: $puzzleBoard")
println("Solution Board: $solutionBoard")
println("Difficulty: $difficulty")
println("Grid Type: $gameType")
```

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
