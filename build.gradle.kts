@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

plugins {
    kotlin("multiplatform") version "2.4.10"
    kotlin("plugin.serialization") version "2.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

}

kotlin {
    jvm() {

    }
    mingwX64()
    linuxX64()
    linuxArm64()
    macosArm64() {
        binaries {
            executable(listOf(NativeBuildType.DEBUG, NativeBuildType.RELEASE)) {
                entryPoint = "main"
            }
        }
    }
    wasmWasi() {
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
    }
}



tasks.register<JavaExec>("runJvm") {
    group = "application"
    description = "Runs the Kotlin/JVM application"

    mainClass.set("MainKt")

    val jvmTarget = kotlin.targets.getByName("jvm") as KotlinJvmTarget
    val compilation = jvmTarget.compilations.getByName("main")

    classpath = compilation.output.allOutputs + compilation.runtimeDependencyFiles
}