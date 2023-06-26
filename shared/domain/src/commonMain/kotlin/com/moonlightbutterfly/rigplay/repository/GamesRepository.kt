package com.moonlightbutterfly.rigplay.repository

import com.moonlightbutterfly.rigplay.model.Game
import com.moonlightbutterfly.rigplay.repository.GamesDataSource

class GamesRepository(private val gamesDataSource: GamesDataSource) {

    suspend fun getGames(): List<Game> = gamesDataSource.getGames()
}