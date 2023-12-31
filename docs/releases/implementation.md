[//]: # (This file was automatically generated - do not edit)

## Implementation

### Latest Version

The latest release is [`1.0.0-alpha03`](../releases.md)

### Releases

Here's a summary of the latest versions:

|    Version    |               Release Notes                | Release Date |
|:-------------:|:------------------------------------------:|:------------:|
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
    sudoklify = "1.0.0-alpha03"

    [libraries]
    sudoklify = { group = "dev.teogor.sudoklify", name = "sudoklify", version.ref = "sudoklify" }
    ```

#### Dependencies Implementation

=== "Kotlin"

    ```kotlin title="build.gradle.kts"
    dependencies {
      // Sudoklify Library
      implementation(libs.sudoklify)
    }
    ```

=== "Groovy"

    ```groovy title="build.gradle"
    dependencies {
      // Sudoklify Library
      implementation(libs.sudoklify)
    }
    ```
