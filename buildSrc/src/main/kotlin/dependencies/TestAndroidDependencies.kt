package dependencies

object TestAndroidDependencies {
    const val CORE_KTX = "androidx.test:core-ktx:${BuildDependenciesVersions.TEST_CORE}"
    const val JUNIT_EXT =  "androidx.test.ext:junit-ktx:${BuildDependenciesVersions.JUNIT_EXT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"
    const val COMPOSE = "androidx.compose.ui:ui-test-junit4:${BuildDependenciesVersions.COMPOSE}"
    const val HILT = "com.google.dagger:hilt-android-testing:${BuildDependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${BuildDependenciesVersions.HILT}"
    const val TRUTH_EXT = "androidx.test.ext:truth:${BuildDependenciesVersions.TRUTH_EXT}"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${BuildDependenciesVersions.MOCK_WEB_SERVER}"
}
