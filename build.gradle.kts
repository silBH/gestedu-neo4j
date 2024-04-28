plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "grafo"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-neo4j")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.modelmapper:modelmapper:3.2.0")
	implementation("org.projectlombok:lombok")

	developmentOnly ("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("org.mockito:mockito-core:5.11.0")
	testImplementation ("spring-boot-starter-test: '3.2.5'")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
