object Versions {
    const val min_sdk = 26
    const val target_sdk = 32
    const val compile_sdk = 32

    const val kotlin = "1.7.10"
    const val android_gradle_plugin = "7.2.0"
    const val buildToolsVersion = "30.0.3"

    const val kotlinxSerializationJson = "1.3.3"
    const val kotlinxDateTime = "0.4.0"
    const val coroutines = "1.6.0-native-mt"
    const val koin = "3.0.2"
    const val ktor = "2.0.3"
    const val sqlDelight = "1.5.0"
    const val napier = "2.1.0"

    object AndroidX {
        const val appcompat = "1.2.0"
        const val core = "1.6.0"
        const val lifecycle = "2.4.0"
        const val test = "1.3.0"
        const val test_ext = "1.1.2"
    }

    object Compose {
        const val compose = "1.2.1"
        const val activity = "1.5.1"
        const val accompanist = "0.25.1"
    }
}

object Deps {
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    val napier = "io.github.aakira:napier:${Versions.napier}"

    object Kotlin {
        val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationJson}"
        val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
    }

    object Koin {
        val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
        val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    }

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        val core_ktx = "androidx.core:core-ktx:${Versions.AndroidX.core}"

        val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.AndroidX.lifecycle}"
        val lifecycleViewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
    }

    object AndroidXTest {
        val core = "androidx.test:core:${Versions.AndroidX.test}"
        val junit = "androidx.test.ext:junit:${Versions.AndroidX.test_ext}"
        val runner = "androidx.test:runner:${Versions.AndroidX.test}"
        val rules = "androidx.test:rules:${Versions.AndroidX.test}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.compose}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.Compose.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.Compose.compose}"
        const val material = "androidx.compose.material:material:${Versions.Compose.compose}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.Compose.activity}"
        const val util = "androidx.compose.ui:ui-util:${Versions.Compose.compose}"

        object Accompanist {
            const val insets = "com.google.accompanist:accompanist-insets:${Versions.Compose.accompanist}"
            const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.Compose.accompanist}"
            const val pager = "com.google.accompanist:accompanist-pager:${Versions.Compose.accompanist}"
        }
    }

    object KotlinTest {
        val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }

    object Coroutines {
        val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Ktor {
        val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    }

    object SqlDelight {
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        val coroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
        val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }
}