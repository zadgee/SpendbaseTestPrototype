// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
        classpath(Dependencies.kspPlugin)
        classpath(Dependencies.gmsServices)
        classpath(Dependencies.hiltPlugin)
    }
}