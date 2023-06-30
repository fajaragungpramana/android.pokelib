plugins {
    id(Plugin.DYNAMIC_FEATURE)
    id(Plugin.KOTLIN_ANDROID)
}
android {
    namespace = "com.github.fajaragungpramana.pokelib.favorite"
    compileSdk = Version.SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
}

dependencies {

    implementation(project(Module.APP))

}