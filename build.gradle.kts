// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io")}
        maven { url =uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        val kotlinVersion = "1.5.31"
        val hiltVersion = "2.38.1"
        classpath ("com.android.tools.build:gradle:7.0.3")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io")}
        maven { url =uri("https://plugins.gradle.org/m2/") }
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}