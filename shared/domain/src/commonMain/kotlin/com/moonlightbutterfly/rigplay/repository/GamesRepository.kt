package com.moonlightbutterfly.rigplay.repository

import com.moonlightbutterfly.rigplay.model.Game
import kotlinx.coroutines.flow.Flow

class GamesRepository(private val gamesDataSource: GamesDataSource, private val userPreferencesDataSource: UserPreferencesDataSource) {
    fun getGames(page: Int): Flow<List<Game>> = gamesDataSource.getGames(page)

    fun getGameDetails(id: Int): Flow<Game> = gamesDataSource.getGameDetails(id)

    suspend fun isGameLiked(id: Int) = userPreferencesDataSource.isGameLiked(id)

    suspend fun changeGameLike(liked: Boolean) = userPreferencesDataSource.changeGameLike(liked)
}