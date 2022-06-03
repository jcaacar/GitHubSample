package extensions

import dependencies.Dependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.TestAndroidDependencies
import dependencies.TestDependencies

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.kaptAndroidTest(dependencyNotation: Any): Dependency? =
    add("kaptAndroidTest", dependencyNotation)

fun DependencyHandler.addTestsDependencies() {
    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.JUNIT_EXT)
    testImplementation(TestDependencies.ARCH_CORE)
    testImplementation(TestDependencies.COROUTINE)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(TestDependencies.MOCKK)

    androidTestImplementation(Dependencies.GSON)
    androidTestImplementation(TestAndroidDependencies.COMPOSE)
    androidTestImplementation(TestAndroidDependencies.JUNIT_EXT)
    androidTestImplementation(TestAndroidDependencies.CORE_KTX)

    androidTestImplementation(TestAndroidDependencies.CORE_KTX)
    androidTestImplementation(TestAndroidDependencies.ESPRESSO)
    androidTestImplementation(TestAndroidDependencies.TRUTH_EXT)
    androidTestImplementation(TestAndroidDependencies.MOCK_WEB_SERVER)

    androidTestImplementation(TestAndroidDependencies.HILT)
    kaptAndroidTest(TestAndroidDependencies.HILT_COMPILER)
}
