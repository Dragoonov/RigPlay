package com.moonlightbutterfly.rigplay.repository

import com.moonlightbutterfly.rigplay.model.Game
import kotlinx.coroutines.flow.Flow

class GamesRepository(private val gamesDataSource: GamesDataSource) {
    fun getGames(page: Int): Flow<List<Game>> = gamesDataSource.getGames(page)

    fun getGameDetails(id: Int): Flow<Game> = gamesDataSource.getGameDetails(id)
}