import AndroidX.COMPOSE_ACTIVITY
import AndroidX.NAVIGATION

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    /*
    implementation(KTX.CORE)
    implementation(AndroidX.LIFECYCLE)
    implementation(AndroidX.COMPOSE)
    implementation(platform(AndroidX.COMPOSE_BOM))
    implementation(COMPOSE_ACTIVITY)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(NAVIGATION)
    androidTestImplementation(platform(AndroidX.COMPOSE_BOM))
    debugImplementation("androidx.compose.ui:ui-tooling")

     */
}