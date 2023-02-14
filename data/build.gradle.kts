plugins {
    kotlin("jvm") version "1.8.10"
    id("io.quarkus")
    id("org.flywaydb.flyway") version "9.0.4"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.8.10"

}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project


dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-hibernate-orm-panache-kotlin")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-security-jpa")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-flyway:9.14.1")

    implementation(project(":core"))
}