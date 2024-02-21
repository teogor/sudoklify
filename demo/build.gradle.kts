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

plugins {
  alias(libs.plugins.jetbrains.kotlin.jvm)
  id("application")
}

application {
  mainClass.set("MainKt")
}

dependencies {
  implementation(project(mapOf("path" to ":sudoklify-common")))
  implementation(project(mapOf("path" to ":sudoklify-core")))
  implementation(project(mapOf("path" to ":sudoklify-ktx")))
  implementation(project(mapOf("path" to ":sudoklify-seeds")))

  implementation(libs.kotlin.stdlib)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.gson)

  testImplementation(libs.junit.jupiter)
}

tasks.test {
  useJUnitPlatform()
}

