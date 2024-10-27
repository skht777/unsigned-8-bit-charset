plugins {
    java
    application
}

group = "io.github.skht777"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainModule.set("io.github.skht.charset")
    mainClass.set("io.github.skht.charset.Main")
}

tasks.test {
    useJUnitPlatform()
}