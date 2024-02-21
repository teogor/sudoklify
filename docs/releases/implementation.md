[//]: # (This file was automatically generated - do not edit)

## Implementation

### Latest Version

The latest release is [`1.0.0-beta01`](../releases.md)

### Releases

Here's a summary of the latest versions:

|    Version    |               Release Notes                | Release Date |
|:-------------:|:------------------------------------------:|:------------:|
| 1.0.0-beta01  | [changelog 🔗](changelog/1.0.0-beta01.md)  | 21 Feb 2024  |
| 1.0.0-alpha04 | [changelog 🔗](changelog/1.0.0-alpha04.md) | 08 Feb 2024  |
| 1.0.0-alpha03 | [changelog 🔗](changelog/1.0.0-alpha03.md) | 21 Sep 2023  |
| 1.0.0-alpha02 | [changelog 🔗](changelog/1.0.0-alpha02.md) | 25 Aug 2023  |
| 1.0.0-alpha01 | [changelog 🔗](changelog/1.0.0-alpha01.md) | 24 Aug 2023  |

### Using Version Catalog

#### Declare Components

This catalog provides the implementation details of Sudoklify libraries, including and individual
libraries, in TOML format.

=== "Default"

    ```toml title="gradle/libs.versions.toml"
    [versions]
    teogor-sudoklify = "1.0.0-beta01"

    [libraries]
    teogor-sudoklify-common = { module = "dev.teogor.sudoklify:sudoklify-common", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-core = { module = "dev.teogor.sudoklify:sudoklify-core", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-ktx = { module = "dev.teogor.sudoklify:sudoklify-ktx", version.ref = "teogor-sudoklify" }
    teogor-sudoklify-seeds = { module = "dev.teogor.sudoklify:sudoklify-seeds", version.ref = "teogor-sudoklify" }
    ```

#### Dependencies Implementation

=== "Kotlin"

    ```kotlin title="build.gradle.kts"
    dependencies {
      // Sudoklify Library
      implementation(libs.teogor.sudoklify.common)
      implementation(libs.teogor.sudoklify.core)
      implementation(libs.teogor.sudoklify.ktx)
      implementation(libs.teogor.sudoklify.seeds)
    }
    ```

=== "Groovy"

    ```groovy title="build.gradle"
    dependencies {
      // Sudoklify Library
      implementation libs.teogor.sudoklify.common
      implementation libs.teogor.sudoklify.core
      implementation libs.teogor.sudoklify.ktx
      implementation libs.teogor.sudoklify.seeds
    }
    ```
