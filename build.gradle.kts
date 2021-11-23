import org.gradle.api.tasks.testing.logging.TestLogEvent.*;
import org.gradle.api.tasks.testing.logging.TestExceptionFormat;

plugins {
    `java`
}

subprojects {

    apply(plugin = "java")

    repositories {
        mavenLocal()
        mavenCentral()
    }

    group = "br.com.selat.template"
    version = "1.0"

    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17


    dependencies {
        implementation("net.logstash.logback:logstash-logback-encoder:6.4")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    tasks.withType<Jar> {
        archiveBaseName.set("${rootProject.name}-${project.name}")
        archiveClassifier.set("")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            testLogging {
                events(PASSED, SKIPPED, FAILED)
                exceptionFormat = TestExceptionFormat.FULL
                showExceptions = true
                showCauses = true
                showStackTraces = true
            }
        }
    }

}