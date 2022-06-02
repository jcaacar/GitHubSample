buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    plugins.apply(BuildPlugins.DETEKT)
    plugins.apply(BuildPlugins.KTLINT)
    plugins.apply(BuildPlugins.UPDATE_DEPS)
    plugins.apply(BuildPlugins.TEST_LOGGER)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
