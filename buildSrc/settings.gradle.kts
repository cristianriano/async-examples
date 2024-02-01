// Inside buildSrc we need to configure the path as it's not in the default location (gradle/libs.versions.toml)
dependencyResolutionManagement {
  versionCatalogs {
    create("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }
}