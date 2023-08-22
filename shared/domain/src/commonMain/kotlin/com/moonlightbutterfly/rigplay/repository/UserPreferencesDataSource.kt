package com.moonlightbutterfly.rigplay.repository


interface UserPreferencesDataSource {
    suspend fun isGameLiked(id: Int): Boolean
    suspend fun changeGameLike(liked: Boolean): Boolean
}