import dev.teogor.sudoklify.buildlogic.BuildInfo

plugins {
  id("sudoklify.kotlin.application")
}

group = BuildInfo.group.fullName
version = BuildInfo.version.name

dependencies {
  implementation(libs.gson)

  implementation(project(mapOf("path" to ":sudoklify")))
}

tasks.test {
  useJUnitPlatform()
}

application {
  mainClass.set("MainKt")
}
