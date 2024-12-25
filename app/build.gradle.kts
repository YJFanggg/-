plugins {
    alias(libs.plugins.android.application) // 使用 alias 管理 Android 插件
    alias(libs.plugins.kotlin.android)     // Kotlin 插件
    alias(libs.plugins.kotlin.compose)     // Jetpack Compose 插件
    kotlin("kapt")                         // Room 等需要的 KAPT 支援
    id("kotlin-kapt")                      // 加載 kapt 插件
}

android {
    namespace = "com.example.mycalender"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mycalender"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11 // 設置為 Java 11
        targetCompatibility = JavaVersion.VERSION_11 // 設置為 Java 11
    }

    kotlinOptions {
        jvmTarget = "11" // 確保 Kotlin 編譯目標與 Java 一致
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")

    implementation("com.kizitonwose.calendar:core:2.0.0")
    implementation("com.kizitonwose.calendar:compose:2.0.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room dependencies
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")

    // ViewModel dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
}
