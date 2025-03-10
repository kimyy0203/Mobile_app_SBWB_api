plugins {
    java
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") // Spring Boot Web (REST API 개발)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA (데이터베이스 연동)
    implementation("org.springframework.boot:spring-boot-starter-security") // Spring Security (JWT 인증을 위해 필요)
    implementation("io.jsonwebtoken:jjwt-api:0.11.5") // JWT 라이브러리 (io.jsonwebtoken)
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5") // JWT 라이브러리 (io.jsonwebtoken)
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5") // JWT 라이브러리 (io.jsonwebtoken), JSON 파싱을 위해 필요
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
