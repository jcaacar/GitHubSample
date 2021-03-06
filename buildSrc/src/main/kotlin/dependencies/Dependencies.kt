package dependencies

object Dependencies {
    const val HILT = "com.google.dagger:hilt-android:${BuildDependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${BuildDependenciesVersions.HILT}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT_GSON_CONVERTER}"

    const val HTTP_LOGGER = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.HTTP_LOGGER}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"

    const val COMPOSE_UI = "androidx.compose.ui:ui:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${BuildDependenciesVersions.ACTIVITY_COMPOSE}"
    const val COMPOSE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:${BuildDependenciesVersions.LIFECYCLE}"
    const val COMPOSE_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_PAGING = "androidx.paging:paging-compose:${BuildDependenciesVersions.PAGING_COMPOSE}"
    const val COMPOSE_COIL = "io.coil-kt:coil-compose:${BuildDependenciesVersions.COIL_COMPOSE}"
}
