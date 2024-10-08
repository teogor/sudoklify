/*
 * Copyright 2024 Teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import dev.teogor.winds.api.ArtifactIdFormat
import dev.teogor.winds.api.License
import dev.teogor.winds.api.NameFormat
import dev.teogor.winds.api.Person
import dev.teogor.winds.api.Scm.GitHub
import dev.teogor.winds.api.SonatypeHost
import dev.teogor.winds.api.TicketSystem
import dev.teogor.winds.ktx.createVersion
import dev.teogor.winds.ktx.person
import dev.teogor.winds.ktx.scm
import dev.teogor.winds.ktx.ticketSystem
import org.gradle.api.internal.catalog.DelegatingProjectDependency
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.jetbrains.compose) apply false
  alias(libs.plugins.jetbrains.compose.compiler) apply false
  alias(libs.plugins.jetbrains.kotlin.jvm)
  alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
  alias(libs.plugins.android.application) apply false

  alias(libs.plugins.teogor.winds)
  alias(libs.plugins.vanniktech.maven)
  alias(libs.plugins.jetbrains.dokka)
  alias(libs.plugins.diffplug.spotless)
  alias(libs.plugins.jetbrains.kotlinx.binary.compatibility)
}

apply(from = "githooks.gradle.kts")

tasks.withType<KotlinCompile>().configureEach {
  dependsOn("updateGitHooks")
}

allprojects {
  if (extensions.findByType<JavaPluginExtension>() != null) {
    java {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }
    java {
      toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
      }
    }
  }
  if (extensions.findByType<KotlinJvmProjectExtension>() != null) {
    kotlin {
      jvmToolchain(17)
    }
  }
}

winds {
  features {
    mavenPublishing = true
    docsGenerator = true
    workflowSynthesizer = true
  }

  moduleMetadata {
    name = "Sudoklify"
    description = """
    |Sudoklify stands as a versatile and user-friendly Sudoku puzzle generation library crafted in Kotlin. Effortlessly generate, manipulate, and solve Sudoku puzzles with ease.
    |""".trimMargin()

    yearCreated = 2023
    websiteUrl = "https://source.teogor.dev/sudoklify/"
    apiDocsUrl = "https://source.teogor.dev/sudoklify/html/"

    artifactDescriptor {
      group = "dev.teogor.sudoklify"
      name = "sudoklify"
      version = createVersion(1, 0, 0) {
        betaRelease(4)
      }
      nameFormat = NameFormat.FULL
      artifactIdFormat = ArtifactIdFormat.MODULE_NAME_ONLY
    }

    // Providing SCM (Source Control Management)
    scm<GitHub> {
      owner = "teogor"
      repository = "sudoklify"
    }

    // Providing Ticket System
    ticketSystem<TicketSystem.GitHub> {
      owner = "teogor"
      repository = "sudoklify"
    }

    // Providing Licenses
    licensedUnder(License.Apache2())

    // Providing Persons
    person<Person.DeveloperContributor> {
      id = "teogor"
      name = "Teodor Grigor"
      email = "open-source@teogor.dev"
      url = "https://teogor.dev"
      roles = listOf("Code Owner", "Developer", "Designer", "Maintainer")
      timezone = "UTC+2"
      organization = "Teogor"
      organizationUrl = "https://github.com/teogor"
    }
  }

  publishing {
    enabled = false
    enablePublicationSigning = true
    optInForVanniktechPlugin = true
    cascade = true
    sonatypeHost = SonatypeHost.S01
  }

  documentationBuilder {
    htmlPath = "html/"
  }
}

subprojects {
  apply<SpotlessPlugin>()
  configure<SpotlessExtension> {
    kotlin {
      target("**/*.kt")
      targetExclude(
        "**/build/**/*.kt",
        "**/seeds/**",
        "**/dev/teogor/sudoklify/presets/**",
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

val excludedProjects = listOf<DelegatingProjectDependency>(
  projects.sudoklify,
  projects.sudoklify.demo,
)

apiValidation {
  /**
   * Subprojects that are excluded from API validation
   */
  ignoredProjects.addAll(excludedProjects.map { it.name })

  /**
   * Non-public markers that are excluded from API validation
   */
  nonPublicMarkers.addAll(listOf(
    "dev.teogor.sudoklify.ExperimentalSudoklifyApi",
    "dev.teogor.sudoklify.InternalSudoklifyApi"
  ))
}

subprojects {
  val paths = excludedProjects.map { it.identityPath.path }
  if (!paths.contains(this.path)) {
    apply<DokkaPlugin>()
  }
}
