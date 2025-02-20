import com.google.protobuf.gradle.id

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.protobuf") version "0.9.4"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "1.9.25"
}

group = "com.br"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

extra["springGrpcVersion"] = "0.3.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.grpc:grpc-services")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")
	implementation("net.devh:grpc-spring-boot-starter:2.13.1.RELEASE")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.grpc:spring-grpc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")
	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
	implementation("org.springdoc:springdoc-openapi-maven-plugin:1.4")
	implementation("org.springdoc:springdoc-openapi-starter-common:2.8.4")
	implementation ("io.springfox:springfox-boot-starter:3.0.0")
	implementation ("io.springfox:springfox-swagger-ui:3.0.0")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java"
		}
	}
	generateProtoTasks {
		all().forEach {
			it.plugins {
				id("grpc") {
					option("jakarta_omit")
					option("@generated=omit")
				}
			}
		}
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
