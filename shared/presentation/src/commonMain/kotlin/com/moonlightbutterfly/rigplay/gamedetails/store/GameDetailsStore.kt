package com.moonlightbutterfly.rigplay.gamedetails.store

import com.arkivanov.mvikotlin.core.store.Store
import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem

internal interface GameDetailsStore: Store<GameDetailsStore.Intent, GameDetailsStore.State, Nothing> {

    sealed class Intent {
        data class ChangeLike(val liked: Boolean) : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val data: Data = Data.GameDetails()
    ) {
        sealed class Data {
            data class GameDetails(val details: GameDetailsItem = GameDetailsItem.empty()) : Data()
            object Error : Data()
        }
    }
}