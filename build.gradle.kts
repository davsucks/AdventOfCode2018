import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        val ktlintVersion = "6.3.1"
        classpath("org.jlleitschuh.gradle:ktlint-gradle:$ktlintVersion")
    }
}

plugins {
    kotlin("jvm") version "1.3.11"
    id("org.jlleitschuh.gradle.ktlint") version("6.3.1")
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    apply(plugin = "kotlin")

    dependencies {
        val junitVersion = "5.3.2"
        "implementation"(kotlin("stdlib-jdk8"))
        "testImplementation"("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        "testImplementation"("org.junit.jupiter:junit-jupiter-params:$junitVersion")
        "testImplementation"("org.assertj:assertj-core:3.11.1")
        testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    }

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
}

repositories {
    mavenCentral()
}
