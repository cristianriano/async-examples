plugins {
  id("com.diffplug.spotless")
}

spotless {
  java {
    apply {
      target("src/**/*.java")
    }

    endWithNewline()
    indentWithSpaces()
    googleJavaFormat()
    removeUnusedImports()
    toggleOffOn()
    trimTrailingWhitespace()
  }

  kotlin {
    apply {
      target("src/**/*.kt")
      ktfmt().googleStyle()
    }
  }
}