package com.moonlightbutterfly.rigplay.gamedetails.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.moonlightbutterfly.rigplay.gamedetails.stateToModel
import com.moonlightbutterfly.rigplay.gamedetails.store.GameDetailsStoreFactory
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsView
import com.moonlightbutterfly.rigplay.usecase.GetGameDetailsUseCase
import com.moonlightbutterfly.rigplay.utils.asValue
import kotlin.coroutines.CoroutineContext

class GameDetailsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    itemId: Int,
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) : GameDetailsView, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            GameDetailsStoreFactory(
                storeFactory = storeFactory,
                getGameDetailsUseCase = getGameDetailsUseCase,
                mainContext = mainContext,
                ioContext = ioContext
            ).create(itemId)
        }

    override val models: Value<GameDetailsView.Model> = store.asValue().map(stateToModel)
}
