import org.zaproxy.gradle.addon.AddOnStatus
import org.zaproxy.gradle.addon.misc.ConvertMarkdownToHtml

plugins {
    `java-library`
    id("org.zaproxy.add-on") version "0.13.1"
    id("com.diffplug.spotless")
    id("org.zaproxy.common")
}

description = "ZAP add-on that triggers fragment-based URLs via external script."

zapAddOn {
    addOnId.set("fragmentaddon")
    addOnName.set("Fragment Addon")
    zapVersion.set("2.16.0")
    addOnStatus.set(AddOnStatus.ALPHA)
    description.set("Triggers #fragment URLs using external script")
    version.set("1.0.0")

    releaseLink.set("https://github.com/chaehj02/fragment-addon/compare/v@PREVIOUS_VERSION@...v@CURRENT_VERSION@")
    unreleasedLink.set("https://github.com/chaehj02/fragment-addon/compare/v@CURRENT_VERSION@...HEAD")

    manifest {
        author.set("chaehj02")
        url.set("https://github.com/chaehj02/fragment-addon")
        repo.set("https://github.com/chaehj02/fragment-addon")
        changesFile.set(tasks.named<ConvertMarkdownToHtml>("generateManifestChanges").flatMap { it.html })
        classnames.addClassname("org.zaproxy.addon.fragment.ExtensionFragmentAddon")
    }
}

java {
    val javaVersion = JavaVersion.VERSION_17
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

spotless {
    kotlinGradle {
        ktlint()
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.removeAll { it == "-Werror" }
}
