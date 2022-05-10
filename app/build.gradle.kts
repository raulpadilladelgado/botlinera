plugins {
    kotlin("jvm") version "1.6.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.litote.kmongo:kmongo:4.5.1")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("com.google.code.gson:gson:2.9.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.mockito:mockito-core:4.4.0")
    testImplementation("org.testcontainers:mongodb:1.16.3")
}

application {
    mainClass.set("botlinera.infrastructure.AppKt")
}
