import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  // Need to specify version unless it comes from buildSrc lib
  alias(libs.plugins.kotlin.gradle)

  id("spotless-conventions")

  `java-library`
}

repositories {
  mavenCentral()
}

dependencies {
  api(platform(libs.kotlin.bom))

  testImplementation(platform(libs.junit.bom))
  testImplementation(libs.assertj)
  testImplementation("org.junit.jupiter:junit-jupiter-engine")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
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
