import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.kafka:spring-kafka")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	// https://mvnrepository.com/artifact/io.github.microutils/kotlin-logging
	implementation("io.github.microutils:kotlin-logging:3.0.5")
// https://mvnrepository.com/artifact/io.opentelemetry/opentelemetry-api
	implementation("io.opentelemetry:opentelemetry-api:1.35.0")
// https://mvnrepository.com/artifact/io.opentelemetry.instrumentation/opentelemetry-instrumentation-api
	implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-api:2.1.0")
// https://mvnrepository.com/artifact/io.opentelemetry/opentelemetry-sdk
	implementation("io.opentelemetry:opentelemetry-sdk:1.35.0")
// https://mvnrepository.com/artifact/io.opentelemetry.instrumentation/opentelemetry-spring-kafka-2.7
	runtimeOnly("io.opentelemetry.instrumentation:opentelemetry-spring-kafka-2.7:1.32.1-alpha")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
