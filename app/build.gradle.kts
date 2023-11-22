plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.gms.google-services")
    id ("kotlin-android")
//    kotlin("android") version "1.6.10"

}

android {
    namespace = "com.example.cricbuzz"

    compileSdk=33
    dataBinding{
        isEnabled=true
    }
    lintOptions {
        // Turns off checks for the issue IDs you specify.
        disable ("TypographyFractions","TypographyQuotes")
        // Turns on checks for the issue IDs you specify. These checks are in
        // addition to the default lint checks.
        enable ("RtlHardcoded","RtlCompat", "RtlEnabled")
        // To enable checks for only a subset of issue IDs and ignore all others,
        // list the issue IDs with the 'check' property instead. This property overrides
        // any issue IDs you enable or disable using the properties above.
        checkOnly ("NewApi", "InlinedApi")
        // If set to true, turns off analysis progress reporting by lint.
        isQuiet =true
        // if set to true (default), stops the build if errors are found.
        isAbortOnError =false
        // if true, only report errors.
        isIgnoreWarnings =true
    }

    defaultConfig {
        applicationId = "com.example.cricbuzz"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled= true
        resConfigs ("en")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding= true
    }

}

dependencies {
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.android.gms:play-services-location:21.0.0")
    implementation ("com.google.firebase:firebase-database-ktx:20.2.2")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //kotlin code
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0")
    implementation ("de.hdodenhof:circleimageview:3.0.1")
    implementation ("com.google.android.gms:play-services-places:16.0.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.2.2")
    implementation ("com.squareup.okhttp3:okhttp:4.2.2")
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation ("com.squareup.retrofit2:retrofit:2.6.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.2")
    implementation ("com.squareup.retrofit2:converter-scalars:2.6.2")

    implementation ("com.github.ismaeldivita:chip-navigation-bar:1.4.0")


    // firebase

    implementation ("com.google.firebase:firebase-auth:20.0.0")
    implementation ("com.google.android.gms:play-services-auth:18.1.0")




    // Picasso
    implementation ("com.squareup.picasso:picasso:2.8")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.9.0")

//    implementation ("com.kingfisherphuoc:easy-recyclerview-indicator:1.3")


//        implementation ("com.github.tsurkis:timdicator:1.0.3+")


        implementation ("com.github.martinstamenkovski:ARIndicatorView:2.0.0")



}