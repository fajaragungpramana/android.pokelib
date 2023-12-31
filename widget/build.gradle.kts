plugins {
    id(Plugin.LIBRARY)
    id(Plugin.KOTLIN_ANDROID)
    id(Plugin.KOTLIN_KAPT)
}

android {
    namespace = "com.github.fajaragungpramana.pokelib.widget"
    compileSdk = Version.SDK

    defaultConfig {
        minSdk = Version.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    api(Dependencies.AndroidX.ACTIVITY_COMPOSE)

    api(platform(Dependencies.AndroidX.COMPOSE_BOM))
    api(Dependencies.AndroidX.COMPOSE_UI)
    api(Dependencies.AndroidX.COMPOSE_UI_GRAPHIC)
    api(Dependencies.AndroidX.COMPOSE_UI_TOOLING_PREVIEW)
    api(Dependencies.AndroidX.COMPOSE_MATERIAL)

    api(Dependencies.Widget.COIL_COMPOSE)

}

kapt {
    correctErrorTypes = true
}