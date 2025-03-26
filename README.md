1 App Features
1 Save Favorite Books
2 View Book Details
3 Easy Navigation with Bottom Navigation
4 Theme switching (light and dark themes)

using light theme 
   <resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Base.Theme.BookFinder" parent="Theme.Material3.Light.NoActionBar">
        <!-- Customize your light theme here. -->
         <item name="colorPrimary">@color/colorPrimary</item>
    </style>

    <!--light theme-->
    <style name="Theme.BookFinder" parent="Base.Theme.BookFinder">
<!--        <item name="android:background">@color/colorPrimary</item>-->
    </style>

    <style name="textColor" parent="android:TextAppearance">
        <item name="android:textColor">@color/colorPrimary</item>
        <item name="android:fontFamily">@font/poppins_medium</item>
        <item name="fontFamily">@font/poppins_medium</item>
    </style>

    <style name="layoutBackgroundColor">
        <item name="android:background">@color/colorWhite</item>
    </style>
    <style name="bottomBarBgColor">
        <item name="android:background">@color/colorPrimary</item>
    </style>

    <style name="backgroundColor">
        <item name="android:background">@color/colorPrimary</item>
    </style>

    <!--end light theme-->





     <!-- fonts style-->
    <style name="regular_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_regular</item>
        <item name="fontFamily">@font/poppins_regular</item>
                 <item name="android:textStyle">normal</item>
    </style>

    <style name="medium_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_medium</item>
        <item name="fontFamily">@font/poppins_medium</item>
                 <item name="android:textStyle">bold</item>
    </style>

    <style name="semi_medium_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_semi_bold</item>
        <item name="fontFamily">@font/poppins_semi_bold</item>
                 <item name="android:textStyle">bold</item>
    </style>

    <style name="bold_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_bold</item>
        <item name="fontFamily">@font/poppins_bold</item>
    </style>
    <!--End Font style-->
</resources>

Dark theme
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Base.Theme.BookFinder" parent="Theme.Material3.Dark.NoActionBar">
        <!-- Customize your dark theme here. -->
         <item name="colorPrimary">@color/primaryDark</item>
    </style>

    <!--dark theme-->
    <style name="Theme.BookFinder" parent="Base.Theme.BookFinder">
<!--        <item name="android:background">@color/primaryDark</item>-->
    </style>
    <style name="textColor" parent="android:TextAppearance">
        <item name="android:textColor">@color/colorWhite</item>
        <item name="android:fontFamily">@font/poppins_medium</item>
        <item name="fontFamily">@font/poppins_medium</item>
    </style>

    <style name="layoutBackgroundColor">
        <item name="android:background">@color/colorGray</item>
    </style>
    <style name="backgroundColor">
        <item name="android:background">@color/colorPrimary</item>
    </style>
    <style name="bottomBarBgColor">
        <item name="android:background">@color/primaryDark</item>
    </style>
  <!--dark theme-->





    <!--fonts style-->
    <style name="regular_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_regular</item>
        <item name="fontFamily">@font/poppins_regular</item>
                 <item name="android:textStyle">normal</item>
    </style>

    <style name="medium_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_medium</item>
        <item name="fontFamily">@font/poppins_medium</item>
                 <item name="android:textStyle">bold</item>
    </style>

    <style name="semi_medium_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_semi_bold</item>
        <item name="fontFamily">@font/poppins_semi_bold</item>
                 <item name="android:textStyle">bold</item>
    </style>

    <style name="bold_font" parent="android:TextAppearance">
        <item name="android:fontFamily">@font/poppins_bold</item>
        <item name="fontFamily">@font/poppins_bold</item>
    </style>
    <!--End Font style-->
</resources>

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

    
