plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-platform-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk =31

    defaultConfig {
        applicationId ="com.example.submission_test"
        minSdk =23
        targetSdk =31
        versionCode= 1
        versionName ="1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", "\"8b904530a7aced766995fa063ed27355\"")
    }

    buildTypes {
        release {
            isMinifyEnabled= false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding= true
    }
}

dependencies {

    val lifecycle_extensions_version = "1.1.1"
    val core_ktx_version = "1.6.0"
    val app_compat_version = "1.3.1"
    val material_version = "1.4.0"
    val constraint_version = "2.1.1"
    val junit_version = "4.13.2"
    val junit_test_version = "1.1.3"
    val espresso_test_version = "3.4.0"
    val ui_ktx_version = "2.3.5"
    val spin_kit_version = "1.4.0"
    val picasso_version = "2.71828"
    val retrofit_version = "2.9.0"
    val okHttp3_version = "4.9.0"
    val lifecycle_version = "2.4.1"
    val arch_version = "2.1.0"
    val activity_version = "1.3.1"
    val fragment_version = "1.3.6"
    val hilt_viewmodels = "1.0.0-alpha03"
    val kotlin_coroutines_version = "1.5.0"
    val glide_version = "4.11.0"
    val androidyoutubeplayer_version = "10.0.5"
    val circleimageview_version = "3.1.0"
    val hilt_version = "2.38.1"

    implementation("androidx.core:core-ktx:$core_ktx_version")
    implementation("androidx.appcompat:appcompat:$app_compat_version")
    implementation("com.google.android.material:material:$material_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraint_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    testImplementation("junit:junit:$junit_version")
    androidTestImplementation("androidx.test.ext:junit:$junit_test_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espresso_test_version")

    // UI
    implementation("com.google.android.material:material:$material_version")
    implementation("com.squareup.picasso:picasso:$picasso_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$ui_ktx_version")
    implementation("androidx.navigation:navigation-ui-ktx:$ui_ktx_version")

    // REMOTE
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttp3_version")

    // Coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlin_coroutines_version")

    // Loading Progress - SpinKit
    implementation("com.github.ybq:Android-SpinKit:$spin_kit_version")

    // Glide
    implementation("com.github.bumptech.glide:glide:$glide_version")

    // Core KTX
    implementation("androidx.core:core-ktx:$core_ktx_version")
    // Lifecycle KTX
    implementation("android.arch.lifecycle:extensions:$lifecycle_extensions_version")
    // Activity & Fragment KTX
    implementation("androidx.activity:activity-ktx:$activity_version")
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    // DI - Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt ("com.google.dagger:hilt-compiler:$hilt_version")

    // ViewModel with Hilt
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:$hilt_viewmodels")

    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:$androidyoutubeplayer_version")
    implementation("de.hdodenhof:circleimageview:$circleimageview_version")
}

repositories {
    mavenCentral()
}