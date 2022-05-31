sealed class BuildType {
    object Debug : BuildType() {
        override val name = "debug"
        override val isMinifyEnabled = false
        override val isTestCoverageEnabled = true

        override val applicationIdSuffix = ".debug"
        override val versionNameSuffix = "-DEBUG"
    }

    object Release : BuildType() {
        override val name = "release"
        override val isMinifyEnabled = true
        override val isTestCoverageEnabled = false
    }

    abstract val name: String
    abstract val isMinifyEnabled: Boolean
    abstract val isTestCoverageEnabled: Boolean

    open val applicationIdSuffix = ""
    open val versionNameSuffix = ""
}
