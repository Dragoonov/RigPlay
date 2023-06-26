package com.moonlightbutterfly.rigplay.data

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

expect fun apiKey(): String