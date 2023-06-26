package com.moonlightbutterfly.rigplay.data

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import okhttp3.internal.platform.AndroidPlatform
import java.io.File
import java.io.FileInputStream
import java.util.Properties
import java.util.concurrent.TimeUnit

actual fun httpClient(config: HttpClientConfig<*>.()-> Unit) = HttpClient(OkHttp){
    config(this)
    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(5, TimeUnit.SECONDS)
        }
    }
}

actual fun apiKey(): String {
    return BuildConfig.API_KEY
}