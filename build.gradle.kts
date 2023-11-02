import ProjectVersions.unethicaliteVersion

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    `java-library`
    checkstyle
    kotlin("jvm") version "1.6.21"
}

project.extra["GithubUrl"] = "https://github.com/kecleon/KecleonPlugins"
project.extra["GithubUserName"] = "kecleon"
project.extra["GithubRepoName"] = "KecleonPlugins"

apply<BootstrapPlugin>()

allprojects {
    group = "net.unethicalite"

    project.extra["PluginProvider"] = "kecleon"
    project.extra["ProjectSupportUrl"] = ""
    project.extra["PluginLicense"] = "3-Clause BSD License"

    apply<JavaPlugin>()
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "checkstyle")

    repositories {
        mavenCentral()
        mavenLocal()
		flatDir {
			dirs("libs")
		}
    }

    dependencies {
        annotationProcessor(Libraries.lombok)
        annotationProcessor(Libraries.pf4j)
		
		//implementation(fileTree("libs") { include("*.jar") })

		//implementation(files("../libs/http-api-1.0.20-STABLE.jar"))
		//implementation(files("../libs/runelite-api-1.0.20-STABLE.jar"))
		//implementation(files("../libs/runelite-client-1.0.20-STABLE.jar"))
		//implementation(files("../libs/runelite-client-1.0.20-STABLE.jar"))
		compileOnly(files("../libs/http-api-1.0.20-STABLE.jar"))
		compileOnly(files("../libs/runelite-api-1.0.20-STABLE.jar"))
		compileOnly(files("../libs/runelite-client-1.0.20-STABLE.jar"))
		compileOnly(files("../libs/runescape-api-1.0.20-STABLE.jar"))
        //compileOnly("net.unethicalite:http-api:$unethicaliteVersion")
        //compileOnly("net.unethicalite:runelite-api:$unethicaliteVersion")
        //compileOnly("net.unethicalite:runelite-client:$unethicaliteVersion")
        //compileOnly("net.unethicalite.rs:runescape-api:$unethicaliteVersion")

        compileOnly(Libraries.guice)
        compileOnly(Libraries.javax)
        compileOnly(Libraries.lombok)
        compileOnly(Libraries.pf4j)
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<AbstractArchiveTask> {
            isPreserveFileTimestamps = false
            isReproducibleFileOrder = true
            dirMode = 493
            fileMode = 420
        }

        compileKotlin {
            kotlinOptions.jvmTarget = "11"
        }
    }
}
