pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

val repositoriesTomtomComUsername: String? by extra
val repositoriesTomtomComPassword: String? by extra

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven {
            // Artifactory credentials are only needed if you use the extended flavor of the SDK
            if (repositoriesTomtomComUsername?.isNotEmpty() == true) {
                credentials {
                    username = repositoriesTomtomComUsername
                    password = repositoriesTomtomComPassword
                }
            }
            url = uri("https://repositories.tomtom.com/artifactory/maven")
        }
    }
}

rootProject.name = "WeFashion"
include(":app")
