package plugins

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

apply<DetektPlugin>()

configure<DetektExtension> {
    source = project.files("src/main/kotlin")
    config = files("$rootDir/.detekt/config.yml")
    buildUponDefaultConfig = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        html {
            outputLocation.set(file("${rootDir}/build/reports/detekt/report.html"))
            required.set(true)
        }
    }
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}

tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}
