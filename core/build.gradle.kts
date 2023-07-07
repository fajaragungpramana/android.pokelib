plugins {
    id(Plugin.LIBRARY)
    id(Plugin.KOTLIN_ANDROID)
    id(Plugin.KOTLIN_KAPT)
    id(Plugin.HILT)
}

android {
    namespace = "com.github.fajaragungpramana.pokelib.core"
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

            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
        }

        debug {
            isMinifyEnabled = true
            proguardFiles(
                @Suppress("UnstableApiUsage") getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
            buildConfigField("String", "SQL_DATABASE", "\"Pokelib.db\"")
            buildConfigField("String", "SQL_KEY", "\"com.github.fajaragungpramana\"")
        }
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        buildConfig = true
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

    implementation(Dependencies.AndroidX.SQLITE_KTX)

    implementation(Dependencies.Google.HILT)
    kapt(Dependencies.Google.HILT_COMPILER)

    api(Dependencies.SquareUp.Retrofit)
    api(Dependencies.SquareUp.CONVERTER_GSON)
    implementation(Dependencies.SquareUp.LOGGING_INTERCEPTOR)

    implementation(Dependencies.AndroidX.ROOM)
    kapt(Dependencies.AndroidX.ROOM_COMPILER)

    implementation(Dependencies.Zetetic.ANDROID_DATABASE_SQLCIPHER)

}

kapt {
    correctErrorTypes = true
}