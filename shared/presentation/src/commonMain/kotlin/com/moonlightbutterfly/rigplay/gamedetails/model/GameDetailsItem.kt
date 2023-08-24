package com.moonlightbutterfly.rigplay.gamedetails.model

import com.moonlightbutterfly.rigplay.model.Developer
import com.moonlightbutterfly.rigplay.model.Genre
import com.moonlightbutterfly.rigplay.model.Platform

data class GameDetailsItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val website: String,
    val developers: List<Developer>,
    val platforms: List<Platform>,
    val genres: List<Genre>,
    val isLiked: Boolean
) {
    companion object {
        fun empty() = GameDetailsItem(0, "", "", "", "", emptyList(), emptyList(), emptyList(), false)
    }
}