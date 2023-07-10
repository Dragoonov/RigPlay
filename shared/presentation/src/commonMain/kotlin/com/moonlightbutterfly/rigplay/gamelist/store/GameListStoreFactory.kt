package com.moonlightbutterfly.rigplay.gamelist.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.moonlightbutterfly.rigplay.gamelist.model.GameListItem
import com.moonlightbutterfly.rigplay.repository.GamesRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalMviKotlinApi::class)
internal class GameListStoreFactory(
    private val storeFactory: StoreFactory,
    private val gamesDataSource: GamesRepository,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {

    fun create(): GameListStore =
        object : GameListStore, Store<GameListStore.Intent, GameListStore.State, Nothing> by storeFactory.create<GameListStore.Intent, Unit, Msg, GameListStore.State, Nothing>(
            name = "GameListStore",
            initialState = GameListStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = coroutineExecutorFactory(mainContext) {
                onAction<Unit> {
                    launch {
                        val games = withContext(ioContext) { gamesDataSource.getGames() }
                        dispatch(Msg.Loaded(GameListStore.State.Data.Games(games.map {
                            GameListItem(
                                title = it.title,
                                imageUrl = it.imageUrl
                            )
                        })))
                    }
                }

                onIntent<GameListStore.Intent.Reload> {
                    dispatch(Msg.Loading)
                    launch {
                        val games = withContext(ioContext) { gamesDataSource.getGames() }
                        dispatch(Msg.Loaded(GameListStore.State.Data.Games(games.map {
                            GameListItem(
                                title = it.title,
                                imageUrl = it.imageUrl
                            )
                        })))
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
