package com.moonlightbutterfly.rigplay.data.repository

import com.moonlightbutterfly.rigplay.data.apiKey
import com.moonlightbutterfly.rigplay.data.dto.GameDetailedDTO
import com.moonlightbutterfly.rigplay.data.dto.GamesDTO
import com.moonlightbutterfly.rigplay.data.dto.toGame
import com.moonlightbutterfly.rigplay.data.dto.toGames
import com.moonlightbutterfly.rigplay.model.Game
import com.moonlightbutterfly.rigplay.repository.GamesDataSource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GamesDataSourceImpl(private val httpClient: HttpClient): GamesDataSource {

    override fun getGames(page: Int): Flow<List<Game>> {
        return flow {
            val response: GamesDTO = httpClient.get("$API_URL/games") {
                parameter("key", apiKey())
                parameter("page", page)
                parameter("page_size", 10)
            }.body()
            emit(response.toGames())
        }
    }

    override fun getGameDetails(id: Int): Flow<Game> {
        return flow {
            val response: GameDetailedDTO = httpClient.get("$API_URL/games/$id") {
                parameter("key", apiKey())
            }.body()
            emit(response.toGame())
        }
    }

    private companion object {
        private const val API_URL = "https://api.rawg.io/api"
    }
}