plugins {
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version "2.6.0"
}

dependencies {
    implementation("io.projectreactor:reactor-core")
    implementation("org.springframework:spring-web")
    testImplementation("io.projectreactor:reactor-test")
}

java {
}

