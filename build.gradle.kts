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
}

repositories {
    mavenCentral()
}

dependencies {
    "implementation"(kotlin("stdlib-jdk8"))
}
