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

[//]: # (REGION-DEPENDENCIES)

## Getting Started with Sudoklify

**Adding Dependencies:**

* **Manual Setup:**  This section guides you through adding Sudoklify dependencies directly to your project's `build.gradle` files. ([Link to Manual Dependency Setup Section](#adding-sudoklify-dependencies-manually))
* **Version Catalog (Recommended):** For a more streamlined approach, consider integrating a version catalog. This allows for centralized version management and easier updates. ([Link to Version Catalog Section](#managing-sudoklify-versions-with-version-catalog-recommended))

**Note:** If you prefer manual dependency setup, follow the instructions in the "Manual Setup" section. Otherwise, jump to the "Version Catalog" section for centralized management.

For information on using the KAPT plugin, see the [KAPT documentation](https://kotlinlang.org/docs/kapt.html).  
For information on using the KSP plugin, see the [KSP quick-start documentation](https://kotlinlang.org/docs/ksp-quickstart.html).  
For more information about dependencies, see [Add Build Dependencies](https://developer.android.com/studio/build/dependencies).  

### Adding Sudoklify Dependencies Manually

To use Sudoklify in your app, add the following dependencies to your app's `build.gradle` file:

=== "Groovy"

    ```groovy title="build.gradle"
    dependencies {
        def teogorSudoklify = "1.0.0-beta02"
        
        implementation "dev.teogor.sudoklify:sudoklify-common:$teogorSudoklify"
        implementation "dev.teogor.sudoklify:sudoklify-core:$teogorSudoklify"
        implementation "dev.teogor.sudoklify:sudoklify-ktx:$teogorSudoklify"
        implementation "dev.teogor.sudoklify:sudoklify-seeds:$teogorSudoklify"
    }
    ```

=== "Kotlin"

    ```kotlin title="build.gradle.kts"
    dependencies {
        val teogorSudoklify = "1.0.0-beta02"
        
        implementation("dev.teogor.sudoklify:sudoklify-common:$teogorSudoklify")
        implementation("dev.teogor.sudoklify:sudoklify-core:$teogorSudoklify")
        implementation("dev.teogor.sudoklify:sudoklify-ktx:$teogorSudoklify")
        implementation("dev.teogor.sudoklify:sudoklify-seeds:$teogorSudoklify")
    }
    ```

### Managing Sudoklify Versions with Version Catalog (Recommended)

This section guides you through utilizing a version catalog for centralized management of Sudoklify dependencies in your project. This approach simplifies updates and ensures consistency.

First, define the dependencies in the `libs.versions.toml` file:

- **Group-Name Based:** This approach is used for declaring libraries referenced by group and artifact name.
- **Module Based:** This approach is used for declaring libraries referenced by their module.

=== "Group-Name Based"

    ```toml title="gradle/libs.versions.toml"
    [versions]
    teogor-sudoklify = "1.0.0-beta02"
    
    [libraries]
    teogor-sudoklify-common = { group = "dev.teogor.sudoklify", name = "sudoklify-common", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-core = { group = "dev.teogor.sudoklify", name = "sudoklify-core", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-ktx = { group = "dev.teogor.sudoklify", name = "sudoklify-ktx", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-seeds = { group = "dev.teogor.sudoklify", name = "sudoklify-seeds", version.ref = "teogor-sudoklify" }
    ```

=== "Module Based"

    ```toml title="gradle/libs.versions.toml"
    [versions]
    teogor-sudoklify = "1.0.0-beta02"
    
    [libraries]
    teogor-sudoklify-common = { module = "dev.teogor.sudoklify:sudoklify-common", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-core = { module = "dev.teogor.sudoklify:sudoklify-core", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-ktx = { module = "dev.teogor.sudoklify:sudoklify-ktx", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-seeds = { module = "dev.teogor.sudoklify:sudoklify-seeds", version.ref = "teogor-sudoklify" }
    ```

Then, add these dependencies in your app's `build.gradle` file:

=== "Groovy"

    ```groovy title="build.gradle"
    dependencies {
        implementation libs.teogor.sudoklify.common
        implementation libs.teogor.sudoklify.core
        implementation libs.teogor.sudoklify.ktx
        implementation libs.teogor.sudoklify.seeds
    }
    ```

=== "Kotlin"

    ```kotlin title="build.gradle.kts"
    dependencies {
        implementation(libs.teogor.sudoklify.common)
        implementation(libs.teogor.sudoklify.core)
        implementation(libs.teogor.sudoklify.ktx)
        implementation(libs.teogor.sudoklify.seeds)
    }
    ```

[//]: # (REGION-DEPENDENCIES)

## Find this repository useful? 🩷

* Support it by joining __[stargazers](https://github.com/teogor/sudoklify/stargazers)__ for this
  repository. 📁
* Get notified about my new projects by __[following me](https://github.com/teogor)__ on GitHub. 💻
* Interested in sponsoring me? [Support me](sponsor.md) on GitHub! 🤝

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
