package com.moonlightbutterfly.rigplay.gamelist.stateholder

import kotlinx.coroutines.CoroutineScope

class GameListStateHolderFactory {
    fun create(coroutineScope: CoroutineScope) = GameListStateHolder(coroutineScope)
}