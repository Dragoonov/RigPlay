import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"
    id("com.squareup.sqldelight")
}

sqldelight {
    database("RigPlayDatabase") {
        packageName = "com.moonlightbutterfly.rigplay"
    }
}

fun getApiKey(): String {
    val properties = Properties()
    properties.load(FileInputStream(project.rootProject.file("local.properties")))
    return properties.getProperty("apiKey")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val ktorVersion = "2.3.1"
        val coroutinesVersion = "1.5.0-native-mt"
        val serializationVersion = "1.2.2"

        val commonMain by getting {
            dependencies {
                implementation(project(":shared:domain"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("io.insert-koin:koin-core:3.4.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
            }
        }
        val androidUnitTest by getting
    }
}

android {
    namespace = "com.moonlightbutterfly.rigplay.data"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        buildConfigField("String", "API_KEY", getApiKey())
    }
}