package extensions

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
    testImplementation(TestDependencies.COROUTINE)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(TestDependencies.MOCKK)

    androidTestImplementation(TestAndroidDependencies.COMPOSE)
    androidTestImplementation(TestAndroidDependencies.ESPRESSO)

    androidTestImplementation(TestAndroidDependencies.HILT)
    kaptAndroidTest(TestAndroidDependencies.HILT_COMPILER)
}
