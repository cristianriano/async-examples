plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
}

dependencies {
  // If we define it directly on the project can use the plugin otherwise we need the implementation
  implementation(libs.spotless)
}