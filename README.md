# 🧩 Sudoklify 🧩
## A Powerful Sudoku Puzzle Generation Library

🧩 Sudoklify is a versatile and intuitive Sudoku puzzle generation library written in Kotlin. It provides a comprehensive set of tools and algorithms for generating Sudoku puzzles of various grid sizes and difficulty levels.

## Key Features

- Efficient and customizable Sudoku puzzle generation algorithms ✨
- Support for different grid sizes, including 4x4, 9x9, 16x16, and more 📐
- Multiple difficulty levels - Easy, Medium, Hard, and Expert 🌟
- Flexible token mapping system for personalized puzzle representation 🎨
- Intuitive API for puzzle generation, population, and solution retrieval 🧩
- Randomization techniques for puzzle layout and digit placement 🔀

### Usage

Here's a simple example demonstrating how to generate a Sudoku puzzle using Sudoklify:

```kotlin
import dev.teogor.sudoklify.Difficulty
import dev.teogor.sudoklify.Sudoku
import dev.teogor.sudoklify.SudokuGenerator
import dev.teogor.sudoklify.Type

fun main() {
    val seed = 12345L
    val type = Type.THREE_BY_THREE
    val difficulty = Difficulty.MEDIUM

    val sudoku = SudokuGenerator.getSudoku(
        difficulty = difficulty,
        seed = seed,
        type = type,
    )
    println("Generated Sudoku Puzzle:")
    println(sudoku.puzzle)
}
```

## Contributing

Contributions to Sudoklify are welcome! If you have any ideas, bug reports, or feature requests, please open an issue or submit a pull request. For more information, please refer to our [Contributing Guidelines](CONTRIBUTING.md).

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/teogor/sudoklify/stargazers)__ for this repository. :star: <br>
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