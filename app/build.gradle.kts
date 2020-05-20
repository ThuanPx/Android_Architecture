import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.androidApp)
    id(Plugins.detekt).version(Versions.detekt)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExt)
    kotlin(Plugins.kotlinApt)
}

buildscript {
    apply(from = "ktlint.gradle.kts")
}

android {
    compileSdkVersion(Versions.compile_sdk_version)
    buildToolsVersion(Versions.build_tools_version)

    defaultConfig {
        applicationId = "com.example.framgia.android_architecture"
        minSdkVersion(Versions.min_sdk_version)
        targetSdkVersion(Versions.target_sdk_version)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        create("release") {
            storeFile = file("../keystores/android.jks")
            storePassword = "Android123456"
            keyAlias = "android"
            keyPassword = "androidpw"
        }
    }

    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"

            resValue("string", "app_name", "Android Architecture DEV")
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }

        create("pro") {
            applicationIdSuffix = ".pro"

            resValue("string", "app_name", "Android Architecture PRO")
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro")
            )
        }
    }

    flavorDimensions("default")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

detekt {
    config = files("$rootDir/config/detekt/detekt.yml")
    input = files("src/main/java")
    reports {
        html.enabled = true // observe findings in your browser with structure and code snippets
        xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
        txt.enabled = false // similar to the console output, contains issue signature to manually edit baseline files
    }
}

androidExtensions {
    isExperimental = true
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.kotlin_stdlib)
    implementation(Dependencies.support_app_compat)
    implementation(Dependencies.support_core)
    implementation(Dependencies.support_core_ktx)
    implementation(Dependencies.support_design)
    implementation(Dependencies.constraint_layout)
    implementation(Dependencies.rx_java)
    implementation(Dependencies.rx_android)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_converter_gson)
    implementation(Dependencies.retrofit_adapter_rxjava)
    implementation(Dependencies.ok_http)
    implementation(Dependencies.ok_http_logging)
    implementation(Dependencies.koin_view_model)
    implementation(Dependencies.koin_ext)
    implementation(Dependencies.glide)
    annotationProcessor(Dependencies.glide_compiler)
    kapt(Dependencies.glide_compiler)
    debugImplementation(Dependencies.leak_canary)
    implementation(Dependencies.lottie)
    implementation(Dependencies.timber)
    implementation(Dependencies.conscrypt_android)
}
