plugins {
    id("java")
}

group = "com.spacetravel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.hibernate:hibernate-core:7.0.4.Final")
    runtimeOnly("com.h2database:h2:2.4.240")
    implementation("org.flywaydb:flyway-core:12.5.0")

    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")
}

tasks.test {
    useJUnitPlatform()
}