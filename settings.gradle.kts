pluginManagement {
  includeBuild("build-logic") {
    // Change the name to 'buildlogic'
    name = "buildlogic"
  }
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Sudoklify"

// demo module
include("demo")

// library module
include("sudoklify")

// library module
include("build-logic")
