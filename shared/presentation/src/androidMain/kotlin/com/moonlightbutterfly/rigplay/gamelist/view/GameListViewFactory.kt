package com.moonlightbutterfly.rigplay.gamelist.view

import com.moonlightbutterfly.rigplay.gamelist.stateholder.GameListStateHolder
import kotlinx.coroutines.CoroutineScope

class GameListViewFactory {
    fun create(gameListStateHolder: GameListStateHolder, coroutineScope: CoroutineScope): GameListView = GameListViewImpl(gameListStateHolder, coroutineScope)
}