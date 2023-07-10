package com.moonlightbutterfly.rigplay.gamelist.controller

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.moonlightbutterfly.rigplay.dispatchers.Dispatchers
import com.moonlightbutterfly.rigplay.gamelist.store.GameListStore

internal class GameListControllerFactory(private val store: GameListStore, private val dispatchers: Dispatchers) {

    fun create(lifecycle: Lifecycle) = GameListController(
        lifecycle,
        store,
        dispatchers
    )

}