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

package dev.teogor.sudoklify.buildlogic

object BuildInfo {
  val group: Group = Group("dev.teogor", "sudoklify")
  val version: Version = Version(1, 0, 0, 1, Flags.ALPHA)

  enum class Flags {
    NO,
    ALPHA,
    BETA,
  }

  class Group(val domain: String, val name: String) {
    val fullName: String = "$domain.$name"
  }

  class Version(
    val majorVersion: Int,
    val minorVersion: Int,
    val patchVersion: Int,
    val preReleaseVersion: Int,
    val flags: Flags,
  ) {
    val code: Int = 1

    val name: String = VersionImplementation.versionNameImpl(
      majorVersion,
      minorVersion,
      patchVersion,
      preReleaseVersion,
      flags,
    )
    val snapshotName: String = VersionImplementation.snapshotVersionNameImpl(
      majorVersion,
      minorVersion,
      patchVersion,
      preReleaseVersion,
      flags,
    )

    internal object VersionImplementation {
      fun versionNameImpl(
        majorVersion: Int,
        minorVersion: Int,
        patchVersion: Int,
        preReleaseVersion: Int,
        flags: Flags,
      ): String {
        val baseVersionName = "$majorVersion.$minorVersion.$patchVersion"
        return when (flags) {
          Flags.NO -> baseVersionName
          Flags.ALPHA -> "$baseVersionName-alpha${preReleaseVersionImpl(preReleaseVersion)}"
          Flags.BETA -> "$baseVersionName-beta${preReleaseVersionImpl(preReleaseVersion)}"
        }
      }

      fun snapshotVersionNameImpl(
        majorVersion: Int,
        minorVersion: Int,
        patchVersion: Int,
        preReleaseVersion: Int,
        flags: Flags,
      ): String {
        val baseVersionName = "$majorVersion.$minorVersion.${patchVersion + 1}-SNAPSHOT"
        return when (flags) {
          Flags.NO -> baseVersionName
          Flags.ALPHA -> "$baseVersionName-alpha${preReleaseVersionImpl(preReleaseVersion)}"
          Flags.BETA -> "$baseVersionName-beta${preReleaseVersionImpl(preReleaseVersion)}"
        }
      }

      private fun preReleaseVersionImpl(preReleaseVersion: Int): String {
        return preReleaseVersion.toString().padStart(3, '0')
      }
    }
  }
}
