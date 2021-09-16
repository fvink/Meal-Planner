object Versions {
    val min_sdk = 26
    val target_sdk = 30
    val compile_sdk = 30

    val kotlin = "1.5.30"
    val android_gradle_plugin = "7.0.2"
    val buildToolsVersion = "30.0.3"

    val coroutines = "1.5.0-native-mt"
    val koin = "3.0.2"

    object AndroidX {
        val appcompat = "1.2.0"
        val core = "1.6.0"
        val lifecycle = "2.4.0-alpha02"
        val test = "1.3.0"
        val test_ext = "1.1.2"
    }

    object Compose {
        const val compose = "1.0.2"
        const val activity = "1.3.0"
        const val accompanist = "0.13.0"
    }
}

object Deps {
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    val koinTest = "io.insert-koin:koin-test:${Versions.koin}"

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        val core_ktx = "androidx.core:core-ktx:${Versions.AndroidX.core}"

        val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.AndroidX.lifecycle}"
        val lifecycle_viewmodel_extensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
    }

    object AndroidXTest {
        val core = "androidx.test:core:${Versions.AndroidX.test}"
        val junit = "androidx.test.ext:junit:${Versions.AndroidX.test_ext}"
        val runner = "androidx.test:runner:${Versions.AndroidX.test}"
        val rules = "androidx.test:rules:${Versions.AndroidX.test}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.Compose.compose}"
        const val material = "androidx.compose.material:material:${Versions.Compose.compose}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.Compose.activity}"
        object Accompanist {
            const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.Compose.accompanist}"
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
}