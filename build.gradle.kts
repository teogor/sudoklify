plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "dev.teogor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

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