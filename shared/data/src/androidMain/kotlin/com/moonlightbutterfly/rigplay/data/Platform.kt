package com.moonlightbutterfly.rigplay.data

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.io.File
import java.io.FileInputStream
import java.util.Properties
import java.util.concurrent.TimeUnit

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun httpClient(config: HttpClientConfig<*>.()-> Unit) = HttpClient(OkHttp){
    config(this)
    engine{
        config{
            retryOnConnectionFailure(true)
            connectTimeout(5, TimeUnit.SECONDS)
        }
    }
}

actual fun apiKey(): String {
    return BuildConfig.API_KEY
}