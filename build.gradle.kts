buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(Depends.BuildPlugins.googleServicesPlugin)
        classpath(Depends.BuildPlugins.androidPlugin)
        classpath(Depends.BuildPlugins.kotlinPlugin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven { url = uri("https://dl.bintray.com/sorinirimies/greenlibs") }
        maven { url = uri("https://maven.google.com") }
        maven { url = uri("https://jitpack.io/") }
    }
}
