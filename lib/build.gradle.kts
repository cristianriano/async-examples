import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  // Need to specify version unless it comes from buildSrc lib
  kotlin("jvm") version "1.9.0"

  `java-library`
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Kotlin doesn't support JVM 21 yet
tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "17"
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}
