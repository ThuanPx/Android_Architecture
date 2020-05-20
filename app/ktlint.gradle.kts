repositories {
    jcenter()
}

val ktlint by configurations.creating
val ktlintCheck by tasks.creating(JavaExec::class) {
    group = "ktlin"
    description = "Check Kotlin code style."
    classpath = configurations.getByName("ktlint")
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    group = "ktlin"
    description = "Fix Kotlin code style deviations."
    classpath = configurations.getByName("ktlint")
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}

dependencies {
    ktlint(Dependencies.ktlint)
}
