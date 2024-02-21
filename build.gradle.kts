import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.vanniktech.maven.publish.SonatypeHost
import dev.teogor.winds.api.MavenPublish
import dev.teogor.winds.api.getValue
import dev.teogor.winds.api.model.Contributor
import dev.teogor.winds.api.model.DependencyType
import dev.teogor.winds.api.model.Developer
import dev.teogor.winds.api.model.IssueManagement
import dev.teogor.winds.api.model.LicenseType
import dev.teogor.winds.api.model.createVersion
import dev.teogor.winds.api.provider.Scm
import dev.teogor.winds.gradle.tasks.impl.subprojectChildrens
import dev.teogor.winds.gradle.utils.afterWindsPluginConfiguration
import dev.teogor.winds.gradle.utils.attachTo
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.jetbrains.kotlin.jvm)
  alias(libs.plugins.teogor.winds)
  alias(libs.plugins.vanniktech.maven)
  alias(libs.plugins.jetbrains.dokka)
  alias(libs.plugins.spotless)
  alias(libs.plugins.api.validator)
}

apply(from = "githooks.gradle.kts")

tasks.withType<KotlinCompile>().configureEach {
  dependsOn("updateGitHooks")
}

val excludedProjects = listOf(
  project.name,
  "demo",
)

subprojectChildrens {
  val javaVersion = JavaVersion.VERSION_17
  java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
  }

  val compileKotlin: KotlinCompile by tasks
  compileKotlin.kotlinOptions {
    jvmTarget = javaVersion.toString()
  }

  val compileTestKotlin: KotlinCompile by tasks
  compileTestKotlin.kotlinOptions {
    jvmTarget = javaVersion.toString()
  }
}

winds {
  buildFeatures {
    mavenPublish = true
    docsGenerator = true
  }

  mavenPublish {
    displayName = "Sudoklify"
    name = "sudoklify"

    canBePublished = false

    description =
      "Sudoklify stands as a versatile and user-friendly Sudoku puzzle generation library crafted in Kotlin. Effortlessly generate, manipulate, and solve Sudoku puzzles with ease."

    groupId = "dev.teogor.sudoklify"
    url = "https://source.teogor.dev/sudoklify"
    inceptionYear = 2023

    sourceControlManagement(
      Scm.Git(
        owner = "teogor",
        repo = "sudoklify",
      ),
    )

    issueManagement(
      IssueManagement.Git(
        owner = "teogor",
        repo = "sudoklify",
      ),
    )

    version = createVersion(1, 0, 0) {
      betaRelease(1)
    }

    project.version = version!!.toString()

    addLicense(LicenseType.APACHE_2_0)

    addDeveloper(TeogorDeveloper())

    addContributor(TeogorContributor())
  }

  docsGenerator {
    name = "Sudoklify"
    dependencyGatheringType = DependencyType.NONE
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

data class TeogorContributor(
  override val name: String = "Teodor Grigor",
  override val email: String = "open-source@teogor.dev",
  override val url: String = "https://teogor.dev",
  override val roles: List<String> = listOf("Code Owner", "Developer", "Designer", "Maintainer"),
  override val timezone: String = "UTC+2",
  override val organization: String = "Teogor",
  override val organizationUrl: String = "https://github.com/teogor",
  override val properties: Map<String, String> = emptyMap(),
) : Contributor

subprojects {
  apply<SpotlessPlugin>()
  configure<SpotlessExtension> {
    kotlin {
      target("**/*.kt")
      targetExclude(
        "**/build/**/*.kt",
        "**/test/**",
        "**/seeds/**",
      )

      ktlint()
        .editorConfigOverride(
          mapOf(
            "ktlint_code_style" to "ktlint_official",
            "ij_kotlin_allow_trailing_comma" to "true",
            "disabled_rules" to
              "filename," +
              "annotation,annotation-spacing," +
              "argument-list-wrapping," +
              "double-colon-spacing," +
              "enum-entry-name-case," +
              "multiline-if-else," +
              "no-empty-first-line-in-method-block," +
              "package-name," +
              "trailing-comma," +
              "spacing-around-angle-brackets," +
              "spacing-between-declarations-with-annotations," +
              "spacing-between-declarations-with-comments," +
              "unary-op-spacing," +
              "no-trailing-spaces," +
              "no-wildcard-imports," +
              "max-line-length",
          ),
        )
      licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
      trimTrailingWhitespace()
      endWithNewline()
    }
    format("kts") {
      target("**/*.kts")
      targetExclude("**/build/**/*.kts")
      // Look for the first line that doesn't have a block comment (assumed to be the license)
      licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
    }
    format("xml") {
      target("**/*.xml")
      targetExclude("**/build/**/*.xml")
      // Look for the first XML tag that isn't a comment (<!--) or the xml declaration (<?xml)
      licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
    }
  }
}

apiValidation {
  /**
   * Subprojects that are excluded from API validation
   */
  ignoredProjects.addAll(excludedProjects)
}


subprojects {
  if (!excludedProjects.contains(this.name)) {
    apply<DokkaPlugin>()
  }
}
