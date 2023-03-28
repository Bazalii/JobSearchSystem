import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.quarkus")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-resteasy-jsonb")
    implementation("io.quarkus:quarkus-security-jpa")
    implementation("io.quarkus:quarkus-jackson")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:quarkus-camel-bom:${quarkusPlatformVersion}"))
    implementation("org.apache.camel.quarkus:camel-quarkus-json-validator")
    implementation("io.quarkus:quarkus-smallrye-jwt")
}