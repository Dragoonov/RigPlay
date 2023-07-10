package com.moonlightbutterfly.rigplay

import kotlinx.coroutines.CoroutineScope

class GameListStateHolderFactory {
    fun create(coroutineScope: CoroutineScope) = GameListStateHolder(coroutineScope)
}