package com.moonlightbutterfly.rigplay.data.repository

import com.moonlightbutterfly.rigplay.data.apiKey
import com.moonlightbutterfly.rigplay.data.dto.GamesDTO
import com.moonlightbutterfly.rigplay.data.dto.toGames
import com.moonlightbutterfly.rigplay.model.Game
import com.moonlightbutterfly.rigplay.repository.GamesDataSource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


class GamesDataSourceImpl(private val httpClient: HttpClient): GamesDataSource {

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