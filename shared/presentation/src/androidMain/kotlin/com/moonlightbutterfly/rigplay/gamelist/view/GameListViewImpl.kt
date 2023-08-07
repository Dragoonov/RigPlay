package com.moonlightbutterfly.rigplay.gamelist.view

import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.moonlightbutterfly.rigplay.gamelist.stateholder.GameListStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GameListViewImpl(
    private val stateHolder: GameListStateHolder,
    coroutineScope: CoroutineScope
) : BaseMviView<GameListView.Model, GameListView.Event>(), GameListView {

    override val renderer: ViewRenderer<GameListView.Model> =
        diff {
            diff(GameListView.Model::games) {
                coroutineScope.launch {
                    stateHolder.games.emit(it)
                }
            }
            diff(get = GameListView.Model::isLoading, set = {
                coroutineScope.launch {
                    stateHolder.isLoading.value = it
                }
            })
        }

    init {
        coroutineScope.launch {
            stateHolder.refreshListener.collect {
                dispatch(GameListView.Event.RefreshTriggered)
            }
        }
    }
}