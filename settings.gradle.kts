pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Sudoklify"

include("demo")
include(":sudoklify-common")
include(":sudoklify-core")
include(":sudoklify-ktx")
include(":sudoklify-seeds")
