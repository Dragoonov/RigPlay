package com.moonlightbutterfly.rigplay.repository

import com.moonlightbutterfly.rigplay.model.Game
import kotlinx.coroutines.flow.Flow

interface GamesDataSource {
    fun getGames(page: Int): Flow<List<Game>>
    fun getGameDetails(id: Int): Flow<Game>
}