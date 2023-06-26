package com.moonlightbutterfly.rigplay.data.dto

import com.moonlightbutterfly.rigplay.model.Game
import kotlinx.serialization.Serializable

@Serializable
data class GamesDTO(
    val results: List<GameDTO>
)

fun GamesDTO.toGames(): List<Game> = results.map { it.toGame() }