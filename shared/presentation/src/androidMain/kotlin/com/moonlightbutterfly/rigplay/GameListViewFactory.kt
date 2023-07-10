package com.moonlightbutterfly.rigplay

import com.moonlightbutterfly.rigplay.home.GameListView
import kotlinx.coroutines.CoroutineScope

class GameListViewFactory {
    fun create(gameListStateHolder: GameListStateHolder, coroutineScope: CoroutineScope): GameListView = GameListViewImpl(gameListStateHolder, coroutineScope)
}