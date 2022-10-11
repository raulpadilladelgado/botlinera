plugins {
    kotlin("jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.github.ben-manes.versions") version "0.25.0"

    application
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.litote.kmongo:kmongo:4.5.1")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.7")
    implementation("org.mongodb:mongodb-driver-core:4.7.0")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.testcontainers:mongodb:1.17.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.assertj:assertj-core:3.21.0")
    testImplementation("org.mockito:mockito-core:4.5.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

application {
    mainClass.set("botlinera.infrastructure.AppKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}