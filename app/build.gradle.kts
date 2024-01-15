import AndroidX.COMPOSE_MATERIAL
import AndroidX.NAVIGATION
import Versions.JVM_TARGET
import Versions.KOTLIN_COMPILER_EXTENTION
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.jdm.movieapp"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.jdm.movieapp"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = KOTLIN_COMPILER_EXTENTION
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(project(mapOf("path" to ":core:designsystem")))
    implementation(project(mapOf("path" to ":feature:search")))
    implementation(project(mapOf("path" to ":feature:favorite")))


    implementation(KTX.CORE)
    implementation(AndroidX.LIFECYCLE)
    implementation(AndroidX.COMPOSE)
    implementation(platform(AndroidX.COMPOSE_BOM))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(NAVIGATION)
    testImplementation(Test.JUNIT)
    androidTestImplementation(Test.EXT_JUNIT)
    androidTestImplementation(Test.ESPRESSO_CORE)
    androidTestImplementation(platform(AndroidX.COMPOSE_BOM))
    androidTestImplementation(Test.COMPOSE_UI)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(HILT.HILT)
    kapt(HILT.HILT_COMPILER)
}
fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}