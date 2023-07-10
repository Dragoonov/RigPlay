package com.moonlightbutterfly.rigplay.home

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.moonlightbutterfly.rigplay.Dispatchers

internal class GameListControllerFactory(private val store: GameListStore, private val dispatchers: Dispatchers) {

    fun create(lifecycle: Lifecycle) = GameListController(
        lifecycle,
        store,
        dispatchers
    )

}