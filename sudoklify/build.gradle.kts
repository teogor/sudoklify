import dev.teogor.sudoklify.buildlogic.BuildInfo

plugins {
  id("sudoklify.kotlin.library")
}

group = BuildInfo.group.fullName
version = BuildInfo.version.name

tasks.test {
  useJUnitPlatform()
}
