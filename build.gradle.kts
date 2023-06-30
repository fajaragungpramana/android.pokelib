plugins {
    id(Plugin.APPLICATION) version(Version.GRADLE) apply(false)
    id(Plugin.LIBRARY) version(Version.GRADLE) apply(false)
    id(Plugin.KOTLIN_ANDROID) version(Version.KOTLIN) apply(false)
    id(Plugin.DYNAMIC_FEATURE) version(Version.GRADLE) apply(false)
    id(Plugin.HILT) version(Version.HILT) apply(false)
}

buildscript {
    dependencies {
        classpath(Classpath.HILT)
    }
}