/*
 * Copyright 2023 Teogor (Teodor Grigor)
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
import com.vanniktech.maven.publish.SonatypeHost
import dev.teogor.sudoklify.buildlogic.BuildInfo

plugins {
  id("sudoklify.kotlin.library")
  id("sudoklify.maven.publish.library")
}

group = BuildInfo.group.fullName
version = BuildInfo.version.name

tasks.test {
  useJUnitPlatform()
}

dependencies {
  testImplementation("junit:junit:4.13.2")
}

@Suppress("UnstableApiUsage")
mavenPublishing {
  publishToMavenCentral(SonatypeHost.S01)

  signAllPublications()

  coordinates("dev.teogor.sudoklify", "sudoklify", "1.0.0-alpha03")

  pom {
    name.set("sudoklify")
    description.set("Sudoklify stands as a versatile and user-friendly Sudoku puzzle generation library crafted in Kotlin. Effortlessly generate, manipulate, and solve Sudoku puzzles with ease.")
    inceptionYear.set("2023")
    url.set("https://github.com/teogor/sudoklify/")
    licenses {
      license {
        name.set("The Apache License, Version 2.0")
        url.set("https://www.apache.org/licenses/LICENSE-2.0")
        distribution.set("repo")
      }
    }
    developers {
      developer {
        id.set("teogor")
        name.set("Teodor Grigor")
        url.set("https://github.com/teogor/")
      }
    }
    scm {
      url.set("https://github.com/teogor/sudoklify/")
      connection.set("scm:git:https://github.com/teogor/sudoklify.git")
      developerConnection.set("scm:git:git@github.com:teogor/sudoklify.git")
    }
  }
}
