plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    implementation("org.hibernate.orm:hibernate-community-dialects:6.4.4.Final")
    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.slf4j:slf4j-simple:2.0.12")
}

tasks.test {
    useJUnitPlatform()
}