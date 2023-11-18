import com.vanniktech.maven.publish.SonatypeHost
import dev.teogor.winds.api.MavenPublish
import dev.teogor.winds.api.getValue
import dev.teogor.winds.api.model.Developer
import dev.teogor.winds.api.model.LicenseType
import dev.teogor.winds.api.model.Version
import dev.teogor.winds.api.provider.Scm
import dev.teogor.winds.gradle.utils.afterWindsPluginConfiguration
import dev.teogor.winds.gradle.utils.attachTo
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.winds)
  alias(libs.plugins.vanniktech.maven)
}

apply(from = "githooks.gradle.kts")

tasks.withType<KotlinCompile>().configureEach {
  dependsOn("updateGitHooks")
}

winds {
  buildFeatures {
    mavenPublish = true
  }

  mavenPublish {
    displayName = "Sudoklify"
    name = "sudoklify"

    canBePublished = false

    description = "Sudoklify stands as a versatile and user-friendly Sudoku puzzle generation library crafted in Kotlin. Effortlessly generate, manipulate, and solve Sudoku puzzles with ease."

    groupId = "dev.teogor.sudoklify"

    artifactIdElements = 1

    inceptionYear = 2023

    sourceControlManagement(
      Scm.Git(
        owner = "teogor",
        repo = "sudoklify",
      ),
    )

    version = Version(
      major = 1,
      minor = 0,
      patch = 0,
    ).setAlphaRelease(4)

    addLicense(LicenseType.APACHE_2_0)

    addDeveloper(TeogorDeveloper())
  }
}

afterWindsPluginConfiguration { winds ->
  val mavenPublish: MavenPublish by winds
  if (mavenPublish.canBePublished) {
    mavenPublishing {
      publishToMavenCentral(SonatypeHost.S01)
      signAllPublications()

      @Suppress("UnstableApiUsage")
      pom {
        coordinates(
          groupId = mavenPublish.groupId!!,
          artifactId = mavenPublish.artifactId!!,
          version = mavenPublish.version!!.toString(),
        )
        mavenPublish attachTo this
      }
    }
  }
}

data class TeogorDeveloper(
  override val id: String = "teogor",
  override val name: String = "Teodor Grigor",
  override val email: String = "open-source@teogor.dev",
  override val url: String = "https://teogor.dev",
  override val roles: List<String> = listOf("Code Owner", "Developer", "Designer", "Maintainer"),
  override val timezone: String = "UTC+2",
  override val organization: String = "Teogor",
  override val organizationUrl: String = "https://github.com/teogor",
) : Developer
