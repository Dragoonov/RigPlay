package com.moonlightbutterfly.rigplay.data.shared

import com.moonlightbutterfly.rigplay.data.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
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