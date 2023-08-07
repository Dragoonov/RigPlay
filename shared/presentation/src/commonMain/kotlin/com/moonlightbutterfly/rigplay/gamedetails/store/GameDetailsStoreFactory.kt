package com.moonlightbutterfly.rigplay.gamedetails.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem
import com.moonlightbutterfly.rigplay.usecase.GetGameDetailsUseCase
import io.ktor.http.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalMviKotlinApi::class)
internal class GameDetailsStoreFactory(
    private val storeFactory: StoreFactory,
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {

    private suspend fun CoroutineExecutorScope<GameDetailsStore.State, Msg, Nothing>.dispatchOnMain(message: Msg) {
        withContext(mainContext) {
            dispatch(message)
        }
    }

    fun create(id: Int): GameDetailsStore =
        object : GameDetailsStore, Store<GameDetailsStore.Intent, GameDetailsStore.State, Nothing> by storeFactory.create<GameDetailsStore.Intent, Unit, Msg, GameDetailsStore.State, Nothing>(
            name = "GameDetailsStore",
            initialState = GameDetailsStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = coroutineExecutorFactory(ioContext) {
                onAction<Unit> {
                    launch {
                        dispatchOnMain(Msg.Loading)
                        getGameDetailsUseCase(id)
                            .collectLatest {
                            dispatchOnMain(Msg.Loaded(GameDetailsStore.State.Data.GameDetails(
                                GameDetailsItem(
                                    title = it.title,
                                    imageUrl = it.imageUrl,
                                    description = it.description,
                                    website = it.website,
                                    developers = it.developers,
                                    platforms = it.platforms,
                                    genres = it.genres
                                )
                            )))
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
        data class Loaded(val data: GameDetailsStore.State.Data) : Msg()
        object Loading: Msg()
    }
}
