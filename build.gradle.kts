plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.jspecify)
    api(libs.jackson)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testRuntimeOnly(libs.junit.launcher)
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven("https://maven.miles.sh/snapshots") {
            credentials {
                username = System.getenv("REPO_USERNAME")
                password = System.getenv("REPO_PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group as String
            from(components["java"])
        }
    }
}
