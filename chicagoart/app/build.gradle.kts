plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("kotlin-kapt")
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.deanezra.chicagoart"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.deanezra.chicagoart"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /* The chicago Art API requires all apps to provide the app name and email contact address in a custom
         * user agent.
         * To avoid leaking your email by committing it to version control,
         * I import environment variable. Eg, add the following to your .zshrc (if your on Mac)
         * eg. export CONTACT_EMAIL_FOR_USER_AGENT=your.email@example.com
         *
         * This then gets pulled in to BuildConfig in build.gradle.kts using below BuildConfigField
         */
        val contactEmail = System.getenv("CONTACT_EMAIL_FOR_USER_AGENT")
        if (contactEmail.isNullOrBlank()) {
            error("CONTACT_EMAIL_FOR_USER_AGENT environment variable is not set. Please set it before building. See README.md for more details")
        }
        buildConfigField("String", "CONTACT_EMAIL_FOR_USER_AGENT", "\"$contactEmail\"")
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
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    // For robolectric testing:
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    // App related:
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Retrofit and gson for json:
    implementation(libs.retrofit)
    implementation(libs.gson.converter)

    // Coil for image loading (AsyncImage in compose):
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Paging library for support of pagination in Web API:
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    // Compose foundation for Grid support
    implementation(libs.compose.foundation)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose) // Hilt integration for Compose navigation

    // Hilt integration with instrumentation tests
    androidTestImplementation  (libs.hilt.android.testing)
    kaptAndroidTest (libs.hilt.compiler)

    // Hilt integration with local unit tests
    testImplementation (libs.hilt.android.testing)
    kaptTest (libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // MockK and turbine to help testing:
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.robolectric)
    //testImplementation(libs.robolectric.shadows.core)
    testImplementation(libs.arch.core.testing)

}

// Optional, speeds up compilation time:
hilt {
    enableAggregatingTask = true
}

kapt {
    correctErrorTypes = true
}
