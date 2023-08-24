buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("dev.icerock.moko:resources-generator:0.23.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}
plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}
