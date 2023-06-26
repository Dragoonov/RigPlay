package com.moonlightbutterfly.rigplay.data.dto

import com.moonlightbutterfly.rigplay.model.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDTO(
    val name: String,
    @SerialName("background_image") val backgroundImage: String
)

fun GameDTO.toGame(): Game = Game(this.name, this.backgroundImage)