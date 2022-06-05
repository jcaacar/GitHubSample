package dependencies

object TestAndroidDependencies {
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"
    const val COMPOSE = "androidx.compose.ui:ui-test-junit4:${BuildDependenciesVersions.COMPOSE}"
    const val HILT = "com.google.dagger:hilt-android-testing:${BuildDependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${BuildDependenciesVersions.HILT}"
}
