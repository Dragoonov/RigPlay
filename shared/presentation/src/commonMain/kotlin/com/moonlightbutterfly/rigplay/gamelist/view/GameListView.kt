package com.moonlightbutterfly.rigplay.gamelist.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.moonlightbutterfly.rigplay.gamelist.model.GameListItem

interface GameListView: MviView<GameListView.Model, GameListView.Event> {

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val games: List<GameListItem>
    )

    sealed class Event {
        object RefreshTriggered : Event()
    }
}