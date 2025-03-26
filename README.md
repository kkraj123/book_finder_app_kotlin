1 App Features
1 Save Favorite Books
2 View Book Details
3 Easy Navigation with Bottom Navigation

2. Open the Project in Android Studio
Open Android Studio.
Click on File > Open and navigate to the folder where you cloned the project. Select the project folder and click OK.

3 Dependencies
 retorfit for network call
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    kodein
    implementation ("org.kodein.di:kodein-di-generic-jvm:6.2.1")
    implementation ("org.kodein.di:kodein-di-framework-android-x:6.2.1")

    viewModel using for live data and statemanagment
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3")
    implementation( "androidx.lifecycle:lifecycle-common-java8:2.8.3")
    implementation( "androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

     Kotlin Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    room database
    implementation (libs.androidx.room.runtime.v261 ) // Update to the latest stable version
    kapt (libs.androidx.room.compiler.v261)
    implementation (libs.androidx.room.ktx)
    androidTestImplementation (libs.androidx.room.testing)

    dagger
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.dagger:hilt-android:2.37")
    kapt ("com.google.dagger:hilt-android-compiler:2.37")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")

    Glide using for image loading
    implementation (libs.com.github.bumptech.glide.glide2)
