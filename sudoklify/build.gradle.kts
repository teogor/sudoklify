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
plugins {
  id("sudoklify.kotlin.library")
  alias(libs.plugins.winds)
}

tasks.test {
  useJUnitPlatform()
}

dependencies {
  testImplementation("junit:junit:4.13.2")
}

winds {
  mavenPublish {
    displayName = "Sudoklify"
    name = "sudoklify"
    description = ""
  }
}
