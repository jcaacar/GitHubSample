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
    plugins.apply("com.adarshr.test-logger")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
