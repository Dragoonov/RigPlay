plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared:presentation"))
                implementation(project(":shared:data"))
                implementation(project(":shared:domain"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
                implementation("androidx.compose.ui:ui-viewbinding:1.4.3")
                implementation ("androidx.fragment:fragment-ktx:1.6.0")
                implementation("io.insert-koin:koin-core:3.4.2")
                implementation("io.insert-koin:koin-android:3.4.2")
                implementation("com.arkivanov.decompose:decompose:2.0.1")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.moonlightbutterfly.rigplay"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.moonlightbutterfly.RigPlay"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
    kotlin {
        jvmToolchain(11)
    }
}
