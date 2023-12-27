import Versions.KOTLIN_VERSION
import Versions.coroutine_version
import Versions.hilt_version
import Versions.okhttp_version
import Versions.retrofit_version
import Versions.room_version

// ktlint-disable filename

object Versions {
    const val KOTLIN_VERSION = "1.8.0"
    const val JVM_TARGET = "1.8"
    const val KOTLIN_COMPILER_EXTENTION = "1.4.3"
    const val okhttp_version = "4.9.0"
    const val retrofit_version = "2.9.0"
    const val coroutine_version = "1.5.2"
    const val hilt_version = "2.44"
    const val room_version = "2.5.0"
}

object Kotlin {
    const val SDK = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
}

object KTX {
    const val CORE = "androidx.core:core-ktx:1.9.0"
}

object AndroidX {
    const val MATERIAL = "com.google.android.material:material:1.9.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.5.1"
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    const val COMPOSE = "androidx.activity:activity-compose:1.7.0"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:2023.03.00"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:1.8.0"
    const val NAVIGATION = "androidx.navigation:navigation-compose:2.5.3"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:1.3.1"
}

object Coroutines {
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version"
    const val ANDROID_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version"
}


object Test {
    const val JUNIT = "junit:junit:4.13.2"
    const val EXT_JUNIT = "androidx.test.ext:junit:1.1.5"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.5.1"
    const val COMPOSE_UI = "androidx.compose.ui:ui-test-junit4"
}
object OkHttp {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:$okhttp_version"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    const val OKTTHP_URL_CONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:$okhttp_version"
}
object RETROFIT {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$retrofit_version"
}
object HILT {
    const val HILT = "com.google.dagger:hilt-android:$hilt_version"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:$hilt_version"
}
object ROOM {
    const val ROOM = "androidx.room:room-runtime:$room_version"
    const val ROOM_COMPILER = "androidx.room:room-compiler:$room_version"
    const val ROOM_KTX = "androidx.room:room-ktx:$room_version"

}