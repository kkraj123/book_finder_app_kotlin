plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")



}

android {
    namespace = "com.example.bookfinder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bookfinder"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    dataBinding {
        enable = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.core)

    //retorfit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    //kodein
    implementation ("org.kodein.di:kodein-di-generic-jvm:6.2.1")
    implementation ("org.kodein.di:kodein-di-framework-android-x:6.2.1")

    //viewModel
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3")
    implementation( "androidx.lifecycle:lifecycle-common-java8:2.8.3")
    implementation( "androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

     //Kotlin Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //room database
    implementation (libs.androidx.room.runtime.v261 ) // Update to the latest stable version
    kapt (libs.androidx.room.compiler.v261)
    implementation (libs.androidx.room.ktx)
    androidTestImplementation (libs.androidx.room.testing)

    //dagger
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.dagger:hilt-android:2.37")
    kapt ("com.google.dagger:hilt-android-compiler:2.37")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")

    //Glide
    implementation (libs.com.github.bumptech.glide.glide2)
}