package com.moonlightbutterfly.rigplay.gamelist.store

import com.arkivanov.mvikotlin.core.store.Store
import com.moonlightbutterfly.rigplay.gamelist.model.GameListItem

internal interface GameListStore: Store<GameListStore.Intent, GameListStore.State, Nothing> {

    sealed class Intent {
        object Reload : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val data: Data = Data.Games()
    ) {
        sealed class Data {
            data class Games(val games: List<GameListItem> = emptyList()) : Data()
            object Error : Data()
        }
    }
}