package com.moonlightbutterfly.rigplay.repository

import com.moonlightbutterfly.rigplay.model.Game

interface GamesDataSource {
    suspend fun getGames(): List<Game>
}