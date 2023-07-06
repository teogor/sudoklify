plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "dev.teogor"

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation(project(mapOf("path" to ":sudoklify")))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}