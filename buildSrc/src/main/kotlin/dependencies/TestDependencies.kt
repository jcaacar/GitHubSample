package dependencies

object TestDependencies {
    const val JUNIT = "junit:junit:${BuildDependenciesVersions.JUNIT}"
    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.COROUTINES_TEST}"
    const val TRUTH = "com.google.truth:truth:${BuildDependenciesVersions.TRUTH}"
    const val MOCKK = "io.mockk:mockk:${BuildDependenciesVersions.MOCKK}"
}
