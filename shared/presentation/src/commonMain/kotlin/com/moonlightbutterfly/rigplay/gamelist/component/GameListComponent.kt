package com.moonlightbutterfly.rigplay.gamelist.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.moonlightbutterfly.rigplay.gamelist.stateToModel
import com.moonlightbutterfly.rigplay.gamelist.store.GameListStore
import com.moonlightbutterfly.rigplay.gamelist.store.GameListStoreFactory
import com.moonlightbutterfly.rigplay.gamelist.view.GameListView
import com.moonlightbutterfly.rigplay.usecase.GetGamesUseCase
import com.moonlightbutterfly.rigplay.utils.asValue
import kotlin.coroutines.CoroutineContext

class GameListComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: Consumer<GameListView.Output>,
    private val getGamesUseCase: GetGamesUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext
) : GameListView, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            GameListStoreFactory(
                storeFactory = storeFactory,
                getGamesUseCase = getGamesUseCase,
                mainContext =  mainContext,
                ioContext = ioContext
            ).create()
        }

    override val models: Value<GameListView.Model> = store.asValue().map(stateToModel)

    override fun onRefreshClicked() {
        store.accept(GameListStore.Intent.Reload)
    }

    override fun onGameSelected(id: Int) {
        output(GameListView.Output.GameSelected(id))
    }
}
