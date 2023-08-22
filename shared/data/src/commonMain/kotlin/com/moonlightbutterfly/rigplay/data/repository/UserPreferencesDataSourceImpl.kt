package com.moonlightbutterfly.rigplay.data.repository

import com.moonlightbutterfly.rigplay.repository.UserPreferencesDataSource

class UserPreferencesDataSourceImpl: UserPreferencesDataSource {

    override suspend fun isGameLiked(id: Int): Boolean {
        return true
    }

    override suspend fun changeGameLike(liked: Boolean): Boolean {
        return true
    }

}