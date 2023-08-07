package com.moonlightbutterfly.rigplay.gamedetails.controller

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.moonlightbutterfly.rigplay.dispatchers.Dispatchers
import com.moonlightbutterfly.rigplay.gamedetails.store.GameDetailsStoreFactory

internal class GameDetailsControllerFactory(private val storeFactory: GameDetailsStoreFactory, private val dispatchers: Dispatchers) {

    fun create(lifecycle: Lifecycle, gameId: Int) = GameDetailsController(
        lifecycle,
        storeFactory.create(gameId),
        dispatchers
    )

}