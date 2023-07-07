package com.moonlightbutterfly.rigplay.home

import com.arkivanov.mvikotlin.core.view.MviView

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