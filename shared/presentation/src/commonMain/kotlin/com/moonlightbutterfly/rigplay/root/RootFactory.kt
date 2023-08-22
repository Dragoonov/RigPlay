package com.moonlightbutterfly.rigplay.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.moonlightbutterfly.rigplay.usecase.GetGameDetailsUseCase
import com.moonlightbutterfly.rigplay.usecase.GetGamesUseCase
import com.moonlightbutterfly.rigplay.usecase.IsGameLikedUseCase
import com.moonlightbutterfly.rigplay.usecase.LikeGameUseCase
import kotlin.coroutines.CoroutineContext

class RootFactory(
    private val storeFactory: StoreFactory,
    private val getGamesUseCase: GetGamesUseCase,
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val isGameLikedUseCase: IsGameLikedUseCase,
    private val likeGameUseCase: LikeGameUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {
    fun create(componentContext: ComponentContext): Root = RootComponent(
        componentContext = componentContext,
        storeFactory = storeFactory,
        getGamesUseCase = getGamesUseCase,
        getGameDetailsUseCase = getGameDetailsUseCase,
        isGameLikedUseCase = isGameLikedUseCase,
        likeGameUseCase = likeGameUseCase,
        mainContext = mainContext,
        ioContext = ioContext
    )
}