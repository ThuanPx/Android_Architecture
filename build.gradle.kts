buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://www.jitpack.io")
        mavenCentral()
    }
    dependencies {
        classpath(ClassPaths.android_gradle_plugin)
        classpath(ClassPaths.kotlin_gradle_plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://www.jitpack.io")
        maven(url = "https://oss.jfrog.org/libs-snapshot")

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
