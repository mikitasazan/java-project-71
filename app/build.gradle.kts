plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    jacoco
    id("io.github.ben-manes.versions") version "0.61.0"
    id("com.diffplug.spotless") version "8.9.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.22.2")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "hexlet.code.App"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

spotless {
    java {
        importOrder()
        googleJavaFormat().aosp()
        removeUnusedImports()
        leadingTabsToSpaces(4)
        endWithNewline()
    }
}

// Точка входа из-под покрытия исключена: у класса с main jacoco считает ещё
// и неявный конструктор, который никто не вызывает, и на маленьком проекте
// это одно тянет покрытие вниз.
val coverageExcludes = listOf("hexlet/code/App.class")

fun JacocoReportBase.excludeEntryPoint() {
    classDirectories.setFrom(
        files(classDirectories.files.map { fileTree(it) { exclude(coverageExcludes) } }),
    )
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    excludeEntryPoint()
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

// Порог покрытия: ниже него ./gradlew build падает, и вместе с ним краснеет CI.
tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
    excludeEntryPoint()
    violationRules {
        rule {
            limit {
                counter = "INSTRUCTION"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}
