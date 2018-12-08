import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
}
buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        val kotlinVersion = "1.3.10"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    apply(plugin = "kotlin")

    dependencies {
        "implementation"(kotlin("stdlib-jdk8"))
        compile(kotlin("stdlib-jdk8"))
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
