import AndroidX.COMPOSE_RUNTIME
import AndroidX.NAVIGATION
import Versions.KOTLIN_COMPILER_EXTENTION

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.jdm.search"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = KOTLIN_COMPILER_EXTENTION
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    api(project(mapOf("path" to ":core:designsystem")))
    implementation(project(mapOf("path" to ":core:data")))


    implementation(KTX.CORE)
    implementation(AndroidX.LIFECYCLE)
    implementation(AndroidX.COMPOSE)
    implementation(platform(AndroidX.COMPOSE_BOM))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.runtime:runtime:1.3.3")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation(COMPOSE_RUNTIME)
    implementation(COIL.COIL)
    implementation(NAVIGATION)
    implementation(HILT.HILT)
    kapt(HILT.HILT_COMPILER)
    androidTestImplementation(platform(AndroidX.COMPOSE_BOM))
    debugImplementation("androidx.compose.ui:ui-tooling")


}