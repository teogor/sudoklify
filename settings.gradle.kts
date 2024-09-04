@file:Suppress("UnstableApiUsage")

rootProject.name = "Sudoklify"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    google {
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositories {
    google {
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
    mavenLocal()
  }
}

include(":demo:composeApp")

include(":sudoklify-common")
include(":sudoklify-core")
include(":sudoklify-io")
include(":sudoklify-presets")
include(":sudoklify-solver")
include(":sudoklify-tokenizer")
