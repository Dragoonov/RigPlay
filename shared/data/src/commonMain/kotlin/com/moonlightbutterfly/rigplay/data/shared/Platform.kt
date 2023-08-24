package com.moonlightbutterfly.rigplay.data.shared

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

expect fun apiKey(): String