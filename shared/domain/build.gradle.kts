plugins {
    kotlin("multiplatform")
    id("com.android.library")
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
        val coroutinesVersion = "1.5.0-native-mt"
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:3.4.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
    }
}

android {
    namespace = "com.moonlightbutterfly.rigplay.domain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}