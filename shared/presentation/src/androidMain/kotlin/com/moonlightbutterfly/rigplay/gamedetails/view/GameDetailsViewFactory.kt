package com.moonlightbutterfly.rigplay.gamedetails.view

import com.moonlightbutterfly.rigplay.gamedetails.stateholder.GameDetailsStateHolder
import kotlinx.coroutines.CoroutineScope

class GameDetailsViewFactory {
    fun create(gameDetailsStateHolder: GameDetailsStateHolder, coroutineScope: CoroutineScope): GameDetailsView = GameDetailsViewImpl(gameDetailsStateHolder, coroutineScope)
}