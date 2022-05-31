plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

object PluginsVersions {
    const val GRADLE_ANDROID = "7.1.1"
    const val GRADLE_VERSIONS = "0.42.0"
    const val KOTLIN = "1.6.10"
    const val KTLINT = "10.3.0"
    const val DETEKT = "1.20.0"
    const val HILT = "2.38.1"
    const val TEST_LOGGER = "3.2.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:${PluginsVersions.KTLINT}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}")
    implementation("com.adarshr:gradle-test-logger-plugin:${PluginsVersions.TEST_LOGGER}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
