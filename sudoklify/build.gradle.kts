import dev.teogor.sudoklify.buildlogic.BuildInfo

plugins {
    id("sudoklify.kotlin.library")
}


group = "dev.teogor"
version = BuildInfo.name

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
