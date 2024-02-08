[//]: # (This file was automatically generated - do not edit)

# Sudoklify

Sudoklify is a versatile and intuitive Sudoku puzzle generation library written in Kotlin. It
provides a comprehensive set of tools and algorithms for generating Sudoku puzzles of various grid
sizes and difficulty levels.

---

### API Reference

* [`dev.teogor.sudoklify`](../reference)
* [`dev.teogor.sudoklify:sudoklify`](../reference/sudoklify)

### Release

|   Latest Update   | Stable Release | Beta Release | Alpha Release |
|:-----------------:|:--------------:|:------------:|:-------------:|
| February 08, 2024 |       -        |      -       | 1.0.0-alpha04 |

### Declaring dependencies

To add a dependency on Sudoklify, you must add the Maven repository to your project.
Read [Maven's repository for more information](https://repo.maven.apache.org/maven2/).

Add the dependencies for the artifacts you need in the `build.gradle` file for your app or module:

=== "Kotlin"

    ```kotlin
    dependencies {
      val sudoklify_version = "1.0.0-alpha04"

      implementation("dev.teogor.sudoklify:sudoklify:sudoklify_version")
    }
    ```

=== "Groovy"

    ```groovy
    dependencies {
      def sudoklify_version = "1.0.0-alpha04"

      implementation("dev.teogor.sudoklify:sudoklify:${sudoklify_version}")
    }
    ```

### Feedback

Your feedback helps make Sudoklify better. We want to know if you discover new issues or have ideas
for improving this library. Before creating a new issue, please take a look at
the [existing ones](https://github.com/teogor/sudoklify) in this library. You can add your vote to
an
existing issue by clicking the star button.

[Create a new issue](https://github.com/teogor/sudoklify/issues/new){ .md-button }

### Version 1.0.0

#### Version 1.0.0-alpha04

February 08, 2024

`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha04` is
released. [Version 1.0.0-alpha04 contains these commits.](https://github.com/teogor/sudoklify/compare/1.0.0-alpha03...1.0.0-alpha04)

**Enhancement**

* Introduce more descriptive Cell type and board
  serialization ([#40](https://github.com/teogor/sudoklify/pull/40))
  by [@teogor](https://github.com/teogor)
* Add `supportsDifficulty` function for GameType
  compatibility ([#39](https://github.com/teogor/sudoklify/pull/39))
  by [@teogor](https://github.com/teogor)
* Enhance Sudoku seed generation for smaller puzzle
  sizes ([#38](https://github.com/teogor/sudoklify/pull/38)) by [@teogor](https://github.com/teogor)
* Implement Sudoku puzzle parsing and decoding ([#37](https://github.com/teogor/sudoklify/pull/37))
  by [@teogor](https://github.com/teogor)
* Enhance Sudoku Solver with Thorough Test
  Suite ([#34](https://github.com/teogor/sudoklify/pull/34)) by [@teogor](https://github.com/teogor)
* Enable Sudoku puzzle parsing and conversion with
  SudokuParser ([#33](https://github.com/teogor/sudoklify/pull/33))
  by [@teogor](https://github.com/teogor)
* Enhance Sudoku Seeds with Additional Game Types and
  Difficulties ([#32](https://github.com/teogor/sudoklify/pull/32))
  by [@teogor](https://github.com/teogor)
* Enhance Difficulty Representation with Stars and Text
  Options ([#31](https://github.com/teogor/sudoklify/pull/31))
  by [@teogor](https://github.com/teogor)
* Refactor GameType enum for improved readability and
  clarity ([30](https://github.com/teogor/sudoklify/pull/30))
  by [@teogor](https://github.com/teogor)
* Improve Difficulty Enum with Percentages and
  Documentation ([#29](https://github.com/teogor/sudoklify/pull/29))
  by [@teogor](https://github.com/teogor)

**Bug Fixes**

* Enhance Sudoku seed generation for smaller puzzle
  sizes ([#38](https://github.com/teogor/sudoklify/pull/38)) by [@teogor](https://github.com/teogor)

#### Version 1.0.0-alpha03

September 21, 2023

`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha03` is
released. [Version 1.0.0-alpha03 contains these commits.](https://github.com/teogor/sudoklify/compare/1.0.0-alpha02...1.0.0-alpha03)

**Enhancement**

* Refactor GameType Enum and Add Utility
  Functions ([#26](https://github.com/teogor/sudoklify/pull/26))
  by [@teogor](https://github.com/teogor)

**Bug Fixes**

* Refactor GameType Enum and Add Utility
  Functions ([#26](https://github.com/teogor/sudoklify/pull/26))
  by [@teogor](https://github.com/teogor)
* Fix Inconsistency in Sudoku Puzzle Patterns and
  Solutions ([#24](https://github.com/teogor/sudoklify/pull/24))
  by [@teogor](https://github.com/teogor)

#### Version 1.0.0-alpha02

August 25, 2023

`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha02` is
released. [Version 1.0.0-alpha02 contains these commits.](https://github.com/teogor/sudoklify/compare/1.0.0-alpha01...1.0.0-alpha02)

**Bug Fixes**

* Corrected Data Types in "Accessing Generated
  Sudoku" ([#21](https://github.com/teogor/sudoklify/pull/21))
  by [@teogor](https://github.com/teogor)
* Update Package Name from "exntensions" to "
  extensions" ([#20](https://github.com/teogor/sudoklify/pull/20))
  by [@teogor](https://github.com/teogor)

#### Version 1.0.0-alpha01

August 24, 2023

`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha01` is
released. [Version 1.0.0-alpha01 contains these commits.](https://github.com/teogor/sudoklify/compare/253ceb722c235ba82e6650c7b82e21fd11d64c78...1.0.0-alpha01)

**Initial Release** 🎊
