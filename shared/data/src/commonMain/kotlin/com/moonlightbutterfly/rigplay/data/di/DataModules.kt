package com.moonlightbutterfly.rigplay.data.di

import com.moonlightbutterfly.rigplay.data.shared.httpClient
import com.moonlightbutterfly.rigplay.data.repository.GamesDataSourceImpl
import com.moonlightbutterfly.rigplay.data.repository.UserPreferencesDataSourceImpl
import com.moonlightbutterfly.rigplay.data.shared.DriverFactory
import com.moonlightbutterfly.rigplay.data.shared.createDatabase
import com.moonlightbutterfly.rigplay.repository.GamesDataSource
import com.moonlightbutterfly.rigplay.repository.UserPreferencesDataSource
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single<GamesDataSource> { GamesDataSourceImpl(get()) }
    single<UserPreferencesDataSource> { UserPreferencesDataSourceImpl(get()) }
    single { createDatabase(DriverFactory(get())) }
    single {
        httpClient {
            install(Logging)
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

}