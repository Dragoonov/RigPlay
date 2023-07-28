package com.moonlightbutterfly.rigplay.gamelist.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.moonlightbutterfly.rigplay.gamelist.model.GameListItem
import com.moonlightbutterfly.rigplay.usecase.GetGamesUseCase
import io.ktor.http.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalMviKotlinApi::class)
internal class GameListStoreFactory(
    private val storeFactory: StoreFactory,
    private val getGamesUseCase: GetGamesUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {

    private suspend fun CoroutineExecutorScope<GameListStore.State, Msg, Nothing>.dispatchOnMain(message: Msg) {
        withContext(mainContext) {
            dispatch(message)
        }
    }

    fun create(): GameListStore =
        object : GameListStore, Store<GameListStore.Intent, GameListStore.State, Nothing> by storeFactory.create<GameListStore.Intent, Unit, Msg, GameListStore.State, Nothing>(
            name = "GameListStore",
            initialState = GameListStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = coroutineExecutorFactory(ioContext) {
                onAction<Unit> {
                    launch {
                        dispatchOnMain(Msg.Loading)
                        getGamesUseCase(1)
                            .collectLatest {
                            dispatchOnMain(Msg.Loaded(GameListStore.State.Data.Games(it.map { game ->
                                GameListItem(
                                    title = game.title,
                                    imageUrl = game.imageUrl
                                )
                            })))
                        }
                    }
                }
                onIntent<GameListStore.Intent.Reload> {
                    launch {
                        dispatchOnMain(Msg.Loading)
                        getGamesUseCase(1).collectLatest {
                            dispatchOnMain(Msg.Loaded(GameListStore.State.Data.Games(it.map { game ->
                                GameListItem(
                                    title = game.title,
                                    imageUrl = game.imageUrl
                                )
                            })))
                        }
                    }
                }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.Loaded -> copy(data = msg.data, isLoading = false)
                    is Msg.Loading -> copy(isLoading = true)
                }
            },
        ) {}


    private sealed class Msg  {
        data class Loaded(val data: GameListStore.State.Data) : Msg()
        object Loading: Msg()
    }
}
