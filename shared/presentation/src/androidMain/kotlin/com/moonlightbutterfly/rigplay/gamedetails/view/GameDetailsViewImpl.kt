package com.moonlightbutterfly.rigplay.gamedetails.view

import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.moonlightbutterfly.rigplay.gamedetails.stateholder.GameDetailsStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GameDetailsViewImpl(
    private val stateHolder: GameDetailsStateHolder,
    coroutineScope: CoroutineScope
) : BaseMviView<GameDetailsView.Model, GameDetailsView.Event>(), GameDetailsView {

    override val renderer: ViewRenderer<GameDetailsView.Model> =
        diff {
            diff(GameDetailsView.Model::gameDetails) {
                coroutineScope.launch {
                    stateHolder.game.emit(it)
                }
            }
            diff(get = GameDetailsView.Model::isLoading, set = {
                coroutineScope.launch {
                    stateHolder.isLoading.value = it
                }
            })
        }
}