import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Ktor to get data from network (api)
            implementation(libs.ktor.client.android)
            implementation(libs.ktor.client.okhttp)

            // -----> Start a lifecycle <----- //
            // ViewModel
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            // Lifecycles only (without ViewModel or LiveData)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            // ViewModel utilities for Compose
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            // Lifecycle utilities for Compose
            implementation(libs.androidx.lifecycle.runtime.compose)
            // -----> End a lifecycle <----- //

            // Koin to dependency injection
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)


//            implementation("io.coil-kt:coil-compose:2.2.2")

//            implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0") // Only available on Android/JVM.
//            implementation("io.coil-kt.coil3:coil-network-ktor2:3.1.0")
//            implementation("io.coil-kt.coil3:coil-network-ktor3:3.1.0")
//            implementation("io.coil-kt.coil3:coil-gif:3.1.0")

//            implementation("androidx.paging:paging-runtime:3.3.6")

//            implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.13-rc")

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Coroutines & Json serialization
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)

            // Ktor to get data from network (api)
            implementation(libs.ktor.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // ViewModel
            implementation(libs.mvvm.core)

            // Image-Loader to loading image from api (from network)
            api(libs.image.loader)

            // Feather Icons
            implementation(libs.father.icons)

            // Voyager to navigate between pages or screens
            implementation(libs.voyager.navigator)

            // Koin to dependency injection
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel.koin)


            // Napier (Log)
            implementation("io.github.aakira:napier:1.4.1")


//            implementation("media.kamel:kamel-image-default:1.0.0")
//
//            implementation("io.github.panpf.sketch4:sketch-compose:1.3.0")
//            implementation("io.github.panpf.sketch4:sketch-animated-gif:1.3.0")

//            implementation("androidx.paging:paging-compose:3.3.6")

//            implementation ("dev.materii.pullrefresh:pullrefresh:1.0.1")

//            implementation("io.github.zj565061763.kmp:compose-refresh:1.0.0")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            implementation(libs.ktor.client.okhttp)

//            implementation("org.jetbrains.skiko:skiko:0.7.59")
        }
    }
}

android {
    namespace = "com.dragons.ball"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.dragons.ball"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

}

compose.desktop {
    application {
        mainClass = "com.dragons.ball.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.dragons.ball"
            packageVersion = "1.0.0"
        }
    }
}
