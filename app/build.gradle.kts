plugins {
    id(Plugin.APPLICATION)
    id(Plugin.KOTLIN_ANDROID)
    id(Plugin.KOTLIN_KAPT)
    id(Plugin.HILT)
}

android {
    namespace = "com.github.fajaragungpramana.pokelib"
    compileSdk = Version.SDK

    defaultConfig {
        applicationId = "com.github.fajaragungpramana.pokelib"
        minSdk = Version.MIN_SDK
        targetSdk = Version.SDK
        versionCode = Version.APP_CODE
        versionName = Version.APP_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                @Suppress("UnstableApiUsage") getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(
                @Suppress("UnstableApiUsage") getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dynamicFeatures.addAll(
        setOf(
            Module.DYNAMIC_FEATURE_FAVORITE
        )
    )
}

dependencies {

    api(project(Module.LIBRARY_CORE))
    api(project(Module.LIBRARY_WIDGET))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.COMPOSE_HILT)
    api(Dependencies.AndroidX.NAVIGATION_COMPOSE)

    implementation(Dependencies.Google.HILT)
    kapt(Dependencies.Google.HILT_COMPILER)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(platform(Dependencies.AndroidX.COMPOSE_BOM))
    androidTestImplementation(Dependencies.AndroidTest.COMPOSE_UI_TEST_JUNIT)
    debugImplementation(Dependencies.Debug.COMPOSE_UI_TOOLING)
    debugImplementation(Dependencies.Debug.COMPOSE_UI_TEST_MANIFEST)
    debugImplementation(Dependencies.Debug.LEAKCANARY_ANDROID)

}

kapt {
    correctErrorTypes = true
}