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

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.jetbrains.kotlin.jvm)
  alias(libs.plugins.winds)
}

tasks.test {
  useJUnitPlatform()
}

dependencies {
  testImplementation(libs.junit.jupiter)
}

val javaVersion = JavaVersion.VERSION_11
java {
  sourceCompatibility = javaVersion
  targetCompatibility = javaVersion
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = javaVersion.toString()
}

winds {
  mavenPublish {
    displayName = "Core"
    name = "core"
  }
}

dependencies {
  api(project(":sudoklify-common"))
  api(project(":sudoklify-ktx"))
}
