package com.moonlightbutterfly.rigplay.gamelist.view

import com.arkivanov.decompose.value.Value
import com.moonlightbutterfly.rigplay.gamelist.model.GameListItem

interface GameListView {

    val models: Value<Model>

    fun onRefreshClicked()

    fun onGameSelected(id: Int)

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val games: List<GameListItem>
    )

    sealed class Output {
        data class GameSelected(val id: Int): Output()
    }
}
