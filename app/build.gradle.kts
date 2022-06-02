import BuildType.Debug
import BuildType.Release
import dependencies.DebugDependencies
import dependencies.Dependencies
import extensions.addTestsDependencies

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.HILT)
    id(BuildPlugins.KAPT)
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        }
    }
    buildTypes {
        getByName(Debug.name) {
            applicationIdSuffix = Debug.applicationIdSuffix
            versionNameSuffix = Debug.versionNameSuffix
            isMinifyEnabled = Debug.isMinifyEnabled
        }
        getByName(Release.name) {
            isMinifyEnabled = Release.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        this.forEach {
            it.buildConfigField("String", "GITHUB_API_BASE_URL", "\"https://api.github.com/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE
    }
    lint {
        baseline = rootProject.file(".lint/lint-baseline.xml")
        lintConfig = rootProject.file(".lint/config.xml")
        checkAllWarnings = true
        warningsAsErrors = true
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)

    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_X_COMPILER)

    implementation(Dependencies.LIFECYCLE)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)

    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.HTTP_LOGGER)

    implementation(Dependencies.PAGING)

    implementation(Dependencies.COROUTINES)

    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_ACTIVITY)
    implementation(Dependencies.COMPOSE_VIEWMODEL)
    implementation(Dependencies.COMPOSE_PREVIEW)
    implementation(Dependencies.COMPOSE_PAGING)
    implementation(Dependencies.COMPOSE_COIL)

    addTestsDependencies()

    debugImplementation(DebugDependencies.COMPOSE_UI_TOOL)
    debugImplementation(DebugDependencies.COMPOSE_UI_TEST_MANIFEST)
}

kapt {
    correctErrorTypes = true
}
