// app/build.gradle.kts
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Add if you're using Kotlin Serialization (ensure it's in libs.plugins.toml if used)
    // alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.magat_ponce_finals"
    compileSdk = 35 // Updated to 35

    defaultConfig {
        applicationId = "com.example.magat_ponce_finals"
        minSdk = 24 // Updated to 24
        targetSdk = 35 // Updated to 35
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
        sourceCompatibility = JavaVersion.VERSION_11 // Updated to 11
        targetCompatibility = JavaVersion.VERSION_11 // Updated to 11
    }
    kotlinOptions {
        jvmTarget = "11" // Updated to 11
    }
    buildFeatures {
        viewBinding = true // Recommended for easier UI interaction
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Networking: Retrofit and GSON converter (assuming these are defined in libs.versions.toml)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // Coroutines for asynchronous operations (assuming this is defined in libs.versions.toml)
    implementation(libs.kotlinx.coroutines.android)

    // Lifecycle KTX for ViewModelScope (assuming these are defined in libs.versions.toml)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}