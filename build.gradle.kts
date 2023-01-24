import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.hibernate.orm:hibernate-core:6.1.6.Final")
    implementation("com.h2database:h2:2.1.214")
    implementation("org.mindrot:jbcrypt:0.4")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}