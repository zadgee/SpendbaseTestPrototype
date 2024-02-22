import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val androidCore = "androidx.core:core-ktx:${Versions.kotlinCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationFragment}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidTestJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomWithPagingIntegration = "androidx.room:room-paging:${Versions.room}"

    // Paging
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    // Lifecycle
    const val lifecycleRuntime="androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // Glide
    const val glide="com.github.bumptech.glide:glide:${Versions.glide}"

    // Plugins
    const val gmsServices="com.google.gms:google-services:${Versions.gmsServices}"
    const val kspPlugin="com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.ksp}"
    const val hiltPlugin ="com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    // Modules
    const val core = ":core"
    const val db = ":db"
    const val networkModule = ":network"
    const val homeScreen = ":home_screen"
    const val cardDetails = ":card_details_screen"
}

fun DependencyHandler.androidLibrary(){
    implementation(Dependencies.androidCore)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
}

fun DependencyHandler.glide(){
    implementation(Dependencies.glide)
}

fun DependencyHandler.testing(){
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.androidTestJUnit)
}

fun DependencyHandler.navigation(){
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
}

fun DependencyHandler.homeScreen(){
    implementation(project(Dependencies.homeScreen))
}

fun DependencyHandler.room(){
    ksp(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomWithPagingIntegration)
}

fun DependencyHandler.lifecycle(){
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleViewModel)
}

fun DependencyHandler.paging(){
    implementation(Dependencies.paging)
}

fun DependencyHandler.retrofit(){
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.loggingInterceptor)
    ksp(Dependencies.retrofit)
}

fun DependencyHandler.hilt(){
    implementation(Dependencies.hilt)
    ksp(Dependencies.hiltCompiler)
}

fun DependencyHandler.networkModule(){
    implementation(project(Dependencies.networkModule))
}

fun DependencyHandler.core(){
    implementation(project(Dependencies.core))
}

fun DependencyHandler.cardDetails(){
    implementation(project(Dependencies.cardDetails))
}

fun DependencyHandler.db(){
    implementation(project(Dependencies.db))
}