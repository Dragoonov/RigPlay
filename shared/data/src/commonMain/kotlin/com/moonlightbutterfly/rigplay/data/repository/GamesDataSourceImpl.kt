package com.moonlightbutterfly.rigplay.data.repository

import com.moonlightbutterfly.rigplay.data.apiKey
import com.moonlightbutterfly.rigplay.data.httpClient
import com.moonlightbutterfly.rigplay.data.dto.GamesDTO
import com.moonlightbutterfly.rigplay.data.dto.toGames
import com.moonlightbutterfly.rigplay.model.Game
import com.moonlightbutterfly.rigplay.repository.GamesDataSource
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class GamesDataSourceImpl: GamesDataSource {
    private val httpClient = httpClient {
        install(Logging)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getGames(): List<Game> {
        val response: GamesDTO = httpClient.get("$API_URL/games") {
            parameter("key", apiKey())
            parameter("page", 1)
            parameter("page_size", 10)
        }.body()
        return response.toGames()
    }

    private companion object {
        private const val API_URL = "https://api.rawg.io/api"
    }
}