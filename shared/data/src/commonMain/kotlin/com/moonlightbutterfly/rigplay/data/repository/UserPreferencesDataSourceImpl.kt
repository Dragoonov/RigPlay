package com.moonlightbutterfly.rigplay.data.repository

import com.moonlightbutterfly.rigplay.RigPlayDatabase
import com.moonlightbutterfly.rigplay.repository.UserPreferencesDataSource

class UserPreferencesDataSourceImpl(
    private val rigPlayDatabase: RigPlayDatabase
): UserPreferencesDataSource {

    override suspend fun isGameLiked(id: Int): Boolean {
        return rigPlayDatabase.rigPlayDatabaseQueries.select(id.toLong()).executeAsOneOrNull() != null
    }

    override suspend fun changeGameLike(id: Int, liked: Boolean): Boolean {
        if (liked) {
            rigPlayDatabase.rigPlayDatabaseQueries.add(id.toLong())
        } else {
            rigPlayDatabase.rigPlayDatabaseQueries.delete(id.toLong())
        }
        return true
    }

}