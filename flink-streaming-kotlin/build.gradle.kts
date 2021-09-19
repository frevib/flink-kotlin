plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.30"
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
}

group = "com.eventloopsoftware"
version = "1.0-SNAPSHOT"


val flinkVersion: String by project

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // not needed for public API so use "implementation"
    implementation("org.apache.flink:flink-streaming-java_2.12:$flinkVersion")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
//        kotlinOptions.freeCompilerArgs += "-Xsam-conversions=class"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.eventloopsoftware"
            artifactId = "flink-streaming-kotlin"
            version = "0.1"

            from(components["java"])
        }
    }
}