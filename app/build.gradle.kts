plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("dev.reformator.stacktracedecoroutinator") version "2.4.0-SNAPSHOT"
    /*
11:12:34.409 coroutines didnt crash
11:12:34.410 java.lang.Exception: coroutines stack trace
             	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck3(MainActivity.kt:63)
             	at dk.dinero.decoroutinatorapplication.MainActivity.access$coroutineCheck3(MainActivity.kt:21)
             	at dk.dinero.decoroutinatorapplication.MainActivity$coroutineCheck3$1.invokeSuspend(Unknown Source:14)
             	at kotlin.coroutines.jvm.internal.JavaUtilsImpl$1.apply(JavaUtilsImpl.java:52)
             	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck2(MainActivity.kt:55)
             	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck1(MainActivity.kt:48)
             	at dk.dinero.decoroutinatorapplication.MainActivity$onResume$1$1.invokeSuspend(MainActivity.kt:41)
             	at dev.reformator.stacktracedecoroutinator.runtime.AwakenerKt.awake(awakener.kt:93)
             	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(basecontinuation.kt:20)
             	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
             	at kotlinx.coroutines.internal.LimitedDispatcher$Worker.run(LimitedDispatcher.kt:115)
             	at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:103)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
     */
}

android {
    namespace = "dk.dinero.decoroutinatorapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "dk.dinero.decoroutinatorapplication"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // implementation("dev.reformator.stacktracedecoroutinator:stacktrace-decoroutinator-android:2.3.9")
    /*
11:11:13.522 coroutines didnt crash
11:11:13.523 java.lang.Exception: coroutines stack trace
             	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck3(MainActivity.kt:63)
             	at dk.dinero.decoroutinatorapplication.MainActivity.access$coroutineCheck3(MainActivity.kt:21)
             	at dk.dinero.decoroutinatorapplication.MainActivity$coroutineCheck3$1.invokeSuspend(Unknown Source:14)
             	at dev.reformator.stacktracedecoroutinator.stdlib.StdlibKt.decoroutinatorResumeWith$lambda$1(stdlib.kt:38)
             	at dev.reformator.stacktracedecoroutinator.stdlib.StdlibKt.$r8$lambda$Fy3lawHb76gebo-pxlkCIn-s7Ng(Unknown Source:0)
             	at dev.reformator.stacktracedecoroutinator.stdlib.StdlibKt$$ExternalSyntheticLambda0.apply(Unknown Source:12)
             	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck2(MainActivity.kt:55)
             	at dk.dinero.decoroutinatorapplication.MainActivity.coroutineCheck1(MainActivity.kt:48)
             	at dk.dinero.decoroutinatorapplication.MainActivity$onResume$1$1.invokeSuspend(MainActivity.kt:41)
             	at dev.reformator.stacktracedecoroutinator.stdlib.StdlibKt.decoroutinatorResumeWith(stdlib.kt:114)
             	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(continuation-stdlib.kt:31)
             	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
             	at kotlinx.coroutines.internal.LimitedDispatcher$Worker.run(LimitedDispatcher.kt:115)
             	at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:103)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
             	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
     */
}