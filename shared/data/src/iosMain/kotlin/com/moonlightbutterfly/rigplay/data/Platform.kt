package com.moonlightbutterfly.rigplay.data

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import platform.UIKit.UIDevice

actual fun httpClient(config: HttpClientConfig<*>.()-> Unit)= HttpClient(Darwin) {
    config(this)
    engine{
        configureRequest{
            setAllowsCellularAccess(true)
        }
    }
}