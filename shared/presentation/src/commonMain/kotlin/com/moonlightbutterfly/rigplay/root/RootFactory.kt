package com.moonlightbutterfly.rigplay.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.moonlightbutterfly.rigplay.usecase.GetGameDetailsUseCase
import com.moonlightbutterfly.rigplay.usecase.GetGamesUseCase
import kotlin.coroutines.CoroutineContext

class RootFactory(
    private val storeFactory: StoreFactory,
    private val getGamesUseCase: GetGamesUseCase,
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {
    fun create(componentContext: ComponentContext): Root = RootComponent(
        componentContext,
        storeFactory,
        getGamesUseCase,
        getGameDetailsUseCase,
        mainContext,
        ioContext
    )
}