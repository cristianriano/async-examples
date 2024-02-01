# Kotlin Template

This basic template for working in a Kotlin project includes:

1. Version configuration for Kotlin 1.9 and JVM 17
2. Tests setup
3. GHA Pipeline with Spotless and Tests
4. Catalog Versions (`gradle/libs.versions.toml`)

In order to use just replace the pkg name and config the name of the pkg in `settings.gradle.kts`

## Gradle Plugins

Plugins appear in Maven repository ending in `plugin-gradle`. There are multiple ways to add a plugin here the ones I
know how from simpler to more complicated:

1. Directly in the module. In the `build.gradle.kts` file add it in the plugin block with the format
`id("com.pkg.foo") version "x.x"`
2. Using **Catalog Versions**. Add the plugin in the toml file `foo-gradle = { id = "com.pkg.foo", verison.ref = "foo" }`
and in the module include it with `alias(libs.plugins.foo.gradle)`
3. Finally, if it has a lot of config you can extract it in a file inside
`buildSrc/src/main/kotlin/foo-convention.kts` as it was from the step 1 **BUT WITHOUT SPECIFYING A VERSION**. Define
the version in `buildSrc/build.gradle.kts` as implementation. If you are using Catalog Versions need to add it as a
library (looks like adding "plugin-gradle" as it appears in Maven does the trick). And import it as `id("foo-conventions")` 
