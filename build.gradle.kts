// Top-level build file where you can add configuration options common to all sub-projects/modules.

// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {


    repositories {
        google()
        mavenCentral()
    }


    dependencies {
        classpath ("com.google.gms:google-services:4.4.2")
        classpath ("com.android.tools.build:gradle:8.0.0")
        classpath ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")

    }


}
plugins {
    id("com.google.gms.google-services") version "4.3.15" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}