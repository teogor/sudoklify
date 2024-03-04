# Sudoklify

Learn more: **[User Guide](../user-guide.md)** and **[Code Samples](../code-samples.md)**

Sudoklify stands as a versatile and user-friendly Sudoku puzzle generation
library crafted in Kotlin. Effortlessly generate, manipulate, and solve Sudoku puzzles
with ease.


[//]: # (REGION-API-REFERENCE)

API Reference  
[`dev.teogor.sudoklify:sudoklify-*`](../html/)  
[`dev.teogor.sudoklify:sudoklify`](../html/demo)  
[`dev.teogor.sudoklify:sudoklify-common`](../html/sudoklify-common)  
[`dev.teogor.sudoklify:sudoklify-core`](../html/sudoklify-core)  
[`dev.teogor.sudoklify:sudoklify-ktx`](../html/sudoklify-ktx)  
[`dev.teogor.sudoklify:sudoklify-seeds`](../html/sudoklify-seeds)

[//]: # (REGION-API-REFERENCE)

[//]: # (REGION-RELEASE-TABLE)

| Latest Update       |  Stable Release  |  Release Candidate  |  Beta Release  |  Alpha Release  |
|:--------------------|:----------------:|:-------------------:|:--------------:|:---------------:|
| February 21, 2024   |        -         |          -          |  1.0.0-beta01  |        -        |

[//]: # (REGION-RELEASE-TABLE)

[//]: # (REGION-DEPENDENCIES)

## Declaring dependencies

To use Sudoklify in your app, add the following dependencies to your app's `build.gradle` file:

=== "Groovy"

    ```groovy title="build.gradle"
    dependencies {
        def teogorSudoklify = "1.0.0-beta01"
        
        implementation "dev.teogor.sudoklify:sudoklify-common:$teogorSudoklify"
        implementation "dev.teogor.sudoklify:sudoklify-core:$teogorSudoklify"
        implementation "dev.teogor.sudoklify:sudoklify-ktx:$teogorSudoklify"
        implementation "dev.teogor.sudoklify:sudoklify-seeds:$teogorSudoklify"
    }
    ```

=== "Kotlin"

    ```kotlin title="build.gradle.kts"
    dependencies {
        val teogorSudoklify = "1.0.0-beta01"
        
        implementation("dev.teogor.sudoklify:sudoklify-common:$teogorSudoklify")
        implementation("dev.teogor.sudoklify:sudoklify-core:$teogorSudoklify")
        implementation("dev.teogor.sudoklify:sudoklify-ktx:$teogorSudoklify")
        implementation("dev.teogor.sudoklify:sudoklify-seeds:$teogorSudoklify")
    }
    ```

For comprehensive instructions on adding these dependencies, refer to the [Sudoklify documentation](../index.md#getting-started-with-sudoklify).

[//]: # (REGION-DEPENDENCIES)

[//]: # (REGION-FEEDBACK)

## Feedback

Your feedback helps make Sudoklify better. Let us know if you discover new issues or have
ideas for improving this library. Please take a look at the [existing issues on GitHub](https://github.com/teogor/sudoklify/issues)
for this library before you create a new one.

[Create a new issue](https://github.com/teogor/sudoklify/issues/new){ .md-button }

[//]: # (REGION-FEEDBACK)

[//]: # (REGION-VERSION-CHANGELOG)

### Version 1.0.0

#### Version 1.0.0-beta01

February 21, 2024

[`dev.teogor.sudoklify:sudoklify-*:1.0.0-beta01`](https://github.com/teogor/sudoklify/releases/1.0.0-beta01) is released. [Version 1.0.0-beta01 contains these commits](https://github.com/teogor/sudoklify/compare/1.0.0-alpha04...1.0.0-beta01)

**Enhancement**

* Enhance Sudoku generation with improved combinedSeeds and seed-based tests ([#55](https://github.com/teogor/sudoklify/issues/55)) by [@teogor](https://github.com/teogor)
* Enable easier string conversion with default mappers in Sudoku board functions ([#53](https://github.com/teogor/sudoklify/issues/53)) by [@teogor](https://github.com/teogor)
* Enable encoding and decoding Sudoku boards to/from strings ([#52](https://github.com/teogor/sudoklify/issues/52)) by [@teogor](https://github.com/teogor)
* Introduce `SudokuType` sealed class ([#51](https://github.com/teogor/sudoklify/issues/51)) by [@teogor](https://github.com/teogor)
* Refactor `Seed` type to `sealed class` for improved safety and organization ([#49](https://github.com/teogor/sudoklify/issues/49)) by [@teogor](https://github.com/teogor)
* Enhance Tokenizer with sealed class structure and improved token handling ([#48](https://github.com/teogor/sudoklify/issues/48)) by [@teogor](https://github.com/teogor)
* Enhance `SudokuParams.createPuzzle()` by introducing `SudokuPuzzle` data class ([#46](https://github.com/teogor/sudoklify/issues/46)) by [@teogor](https://github.com/teogor)

**Bug Fixes**

* Improved accuracy and reliability of BoardCell conversions with comprehensive testing ([#54](https://github.com/teogor/sudoklify/issues/54)) by [@teogor](https://github.com/teogor)

**Documentation**

* Future-Proofing Sudoku: Deprecate Legacy Flow Types, Migrate to `SudokuPuzzle` ([#57](https://github.com/teogor/sudoklify/issues/57)) by [@teogor](https://github.com/teogor)
* Updated Docs ([#50](https://github.com/teogor/sudoklify/issues/50)) by [@teogor](https://github.com/teogor)
* Modularize Project Structure: Core, Common, Ktx, Seeds Modules ([#47](https://github.com/teogor/sudoklify/issues/47)) by [@teogor](https://github.com/teogor)

**Others**

* Upgrade JVM Target Compatibility to Java 17 (Kotlin) ([#56](https://github.com/teogor/sudoklify/issues/56)) by [@teogor](https://github.com/teogor)
* Missing project URL in pom causing Sonatype service stop failure ([#43](https://github.com/teogor/sudoklify/issues/43)) by [@teogor](https://github.com/teogor)
* Ensure successful `publishAllPublicationsToMavenCentral` execution ([#42](https://github.com/teogor/sudoklify/issues/42)) by [@teogor](https://github.com/teogor)

#### Version 1.0.0-alpha04

February 08, 2024

[`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha04`](https://github.com/teogor/sudoklify/releases/1.0.0-alpha04) is released. [Version 1.0.0-alpha04 contains these commits](https://github.com/teogor/sudoklify/compare/1.0.0-alpha03...1.0.0-alpha04)

**Enhancement**

* Introduce more descriptive Cell type and board serialization ([#40](https://github.com/teogor/sudoklify/issues/40)) by [@teogor](https://github.com/teogor)
* Add `supportsDifficulty` function for GameType compatibility ([#39](https://github.com/teogor/sudoklify/issues/39)) by [@teogor](https://github.com/teogor)
* Enhance Sudoku seed generation for smaller puzzle sizes ([#38](https://github.com/teogor/sudoklify/issues/38)) by [@teogor](https://github.com/teogor)
* Implement Sudoku puzzle parsing and decoding ([#37](https://github.com/teogor/sudoklify/issues/37)) by [@teogor](https://github.com/teogor)
* Enhance Sudoku Solver with Thorough Test Suite ([#34](https://github.com/teogor/sudoklify/issues/34)) by [@teogor](https://github.com/teogor)
* Enable Sudoku puzzle parsing and conversion with SudokuParser ([#33](https://github.com/teogor/sudoklify/issues/33)) by [@teogor](https://github.com/teogor)
* Enhance Sudoku Seeds with Additional Game Types and Difficulties ([#32](https://github.com/teogor/sudoklify/issues/32)) by [@teogor](https://github.com/teogor)
* Enhance Difficulty Representation with Stars and Text Options ([#31](https://github.com/teogor/sudoklify/issues/31)) by [@teogor](https://github.com/teogor)
* Refactor GameType enum for improved readability and clarity ([#30](https://github.com/teogor/sudoklify/issues/30)) by [@teogor](https://github.com/teogor)
* Improve Difficulty Enum with Percentages and Documentation ([#29](https://github.com/teogor/sudoklify/issues/29)) by [@teogor](https://github.com/teogor)

**Bug Fixes**

* Enhance Sudoku seed generation for smaller puzzle sizes ([#38](https://github.com/teogor/sudoklify/issues/38)) by [@teogor](https://github.com/teogor)

**Maintenance**

* Enhance Sudoku Solver with Thorough Test Suite ([#34](https://github.com/teogor/sudoklify/issues/34)) by [@teogor](https://github.com/teogor)

**Documentation**

* Introduce MkDocs for documentation generation ([#36](https://github.com/teogor/sudoklify/issues/36)) by [@teogor](https://github.com/teogor)
* Enhance Code Quality and Maintainability with Dokka, Spotless, and API Validator ([#35](https://github.com/teogor/sudoklify/issues/35)) by [@teogor](https://github.com/teogor)

#### Version 1.0.0-alpha03

September 21, 2023

[`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha03`](https://github.com/teogor/sudoklify/releases/1.0.0-alpha03) is released. [Version 1.0.0-alpha03 contains these commits](https://github.com/teogor/sudoklify/compare/1.0.0-alpha02...1.0.0-alpha03)

**Enhancement**

* Refactor GameType Enum and Add Utility Functions ([#26](https://github.com/teogor/sudoklify/issues/26)) by [@teogor](https://github.com/teogor)

**Bug Fixes**

* Refactor GameType Enum and Add Utility Functions ([#26](https://github.com/teogor/sudoklify/issues/26)) by [@teogor](https://github.com/teogor)
* Fix Inconsistency in Sudoku Puzzle Patterns and Solutions ([#24](https://github.com/teogor/sudoklify/issues/24)) by [@teogor](https://github.com/teogor)

**Maintenance**

* Update Dependency Versions and Build Configuration ([#27](https://github.com/teogor/sudoklify/issues/27)) by [@teogor](https://github.com/teogor)
* Update Maven Publishing Configuration ([#25](https://github.com/teogor/sudoklify/issues/25)) by [@teogor](https://github.com/teogor)

**Documentation**

* Update Dependency Versions and Build Configuration ([#27](https://github.com/teogor/sudoklify/issues/27)) by [@teogor](https://github.com/teogor)
* Refactor GameType Enum and Add Utility Functions ([#26](https://github.com/teogor/sudoklify/issues/26)) by [@teogor](https://github.com/teogor)
* Update Maven Publishing Configuration ([#25](https://github.com/teogor/sudoklify/issues/25)) by [@teogor](https://github.com/teogor)

#### Version 1.0.0-alpha02

August 25, 2023

[`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha02`](https://github.com/teogor/sudoklify/releases/1.0.0-alpha02) is released. [Version 1.0.0-alpha02 contains these commits](https://github.com/teogor/sudoklify/compare/1.0.0-alpha01...1.0.0-alpha02)

**Bug Fixes**

* Corrected Data Types in Accessing Generated Sudoku ([#21](https://github.com/teogor/sudoklify/issues/21)) by [@teogor](https://github.com/teogor)
* Update Package Name from `exntensions` to `extensions` ([#20](https://github.com/teogor/sudoklify/issues/20)) by [@teogor](https://github.com/teogor)

**Maintenance**

* Bump Maven Publish Version to 1.0.0-alpha02 ([#22](https://github.com/teogor/sudoklify/issues/22)) by [@teogor](https://github.com/teogor)
* Adding Code of Conduct for a Respectful Community ([#19](https://github.com/teogor/sudoklify/issues/19)) by [@teogor](https://github.com/teogor)
* Redefining Puzzle Generation: Sudoklify's Innovative Approach ([#18](https://github.com/teogor/sudoklify/issues/18)) by [@teogor](https://github.com/teogor)

**Documentation**

* Corrected Data Types in Accessing Generated Sudoku ([#21](https://github.com/teogor/sudoklify/issues/21)) by [@teogor](https://github.com/teogor)
* Adding Code of Conduct for a Respectful Community ([#19](https://github.com/teogor/sudoklify/issues/19)) by [@teogor](https://github.com/teogor)
* Redefining Puzzle Generation: Sudoklify's Innovative Approach ([#18](https://github.com/teogor/sudoklify/issues/18)) by [@teogor](https://github.com/teogor)

#### Version 1.0.0-alpha01

August 24, 2023

[`dev.teogor.sudoklify:sudoklify-*:1.0.0-alpha01`](https://github.com/teogor/sudoklify/releases/1.0.0-alpha01) is released. [Version 1.0.0-alpha01 contains these commits](https://github.com/teogor/sudoklify/commits/1.0.0-alpha01)

# 🎉 Introducing Sudoklify v1.0.0-alpha01 🧩

Sudoklify, the Sudoku puzzle generation library, is here with its exciting release! Crafted with love in Kotlin, Sudoklify empowers you to effortlessly generate, manipulate, and solve Sudoku puzzles of varying grid sizes and difficulty levels. Dive into the world of puzzles and challenge your logic with Sudoklify. Let the Sudoku adventures begin! 🚀🧠

[//]: # (REGION-VERSION-CHANGELOG)

