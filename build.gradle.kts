import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
	kotlin("kapt") version "1.8.22"
}

group = "com.payhere.recruit"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

// querydsl version
val querydslVersion = "5.0.0"

// jjwt version
val jjwtVersion = "0.11.5"

// kotest version
val kotestVersion = "5.4.2"
val kotestSpringExtensionVersion = "1.1.2"

// mockk version
val mockkVersion = "1.12.0"

// fixture version
val fixtureVersion = "1.2.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.jsonwebtoken:jjwt-api:${jjwtVersion}")
	implementation ("com.mysql:mysql-connector-j")
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")

	// QueryDSL
	implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
	kapt("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")


	runtimeOnly("com.h2database:h2")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:${jjwtVersion}")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:${jjwtVersion}")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	// Kotest
	testImplementation("io.kotest:kotest-runner-junit5:${kotestVersion}")
	testImplementation("io.kotest:kotest-assertions-core:${kotestVersion}")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:${kotestSpringExtensionVersion}")

	// Mockk
	testImplementation("io.mockk:mockk:${mockkVersion}")

	// Kotlin Test Fixture
	testImplementation ("com.appmattus.fixture:fixture:${fixtureVersion}")

	annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
}

kapt {
	javacOptions {
		option("querydsl.entityAccessors", true)
	}
	arguments {
		arg("plugin", "com.querydsl.apt.jpa.JPAAnnotationProcessor")
	}
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
