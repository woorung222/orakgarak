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
        maven {
            url = uri("https://repository.map.naver.com/archive/maven")
        }
        maven {
            url = uri("https://naver.jfrog.io/artifactory/maven/")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://repository.map.naver.com/archive/maven")
        }
        maven {
            url = uri("https://naver.jfrog.io/artifactory/maven/")
        }
        maven { url = uri("https://maven.map.naver.com/maven/") }
    }

}

rootProject.name = "orakgarak"
include(":app")
 