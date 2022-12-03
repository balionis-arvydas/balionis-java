plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.11")
}

application {
    mainClassName = "com.balionis.java7.MyApp"
}
