group = "com.application"
version = "0.0.1-SNAPSHOT"
description = "Schedule Application"

plugins {
    kotlin("jvm") version "2.4.10"
    kotlin("kapt") version "2.4.10"
    kotlin("plugin.spring") version "2.4.10"

    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"

    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("software.amazon.awssdk:cognitoidentityprovider:2.45.1")
    implementation("io.awspring.cloud:spring-cloud-aws-starter-secrets-manager:4.1.1")
    implementation("org.hibernate.validator:hibernate-validator:9.1.3.Final")
    implementation("jakarta.validation:jakarta.validation-api:4.0.0-M1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    kapt("org.springframework.boot:spring-boot-configuration-processor")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring4x:4.33.0")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
