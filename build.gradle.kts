group = "com.application"
version = "0.0.1-SNAPSHOT"
description = "Schedule Application"

plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.spring") version "2.3.21"
    kotlin("plugin.jpa") version "2.3.21"

    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"

    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
    kotlin("kapt") version "2.3.21"
}

dependencies {
    val postgresql = "42.7.11"
    val hibernateValidator = "9.1.0.Final"
    val jakartaValidationApi = "3.1.1"
    val h2database = "2.4.240"

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.postgresql:postgresql:$postgresql")
    implementation("org.hibernate.validator:hibernate-validator:$hibernateValidator")
    implementation("jakarta.validation:jakarta.validation-api:$jakartaValidationApi")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("com.h2database:h2:$h2database")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(kotlin("test"))
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
