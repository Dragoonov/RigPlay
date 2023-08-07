package com.moonlightbutterfly.rigplay.gamedetails.view

import com.arkivanov.mvikotlin.core.view.MviView
import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem

interface GameDetailsView: MviView<GameDetailsView.Model, GameDetailsView.Event> {

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val gameDetails: GameDetailsItem
    )

    sealed class Event {
        //Nothing
    }
}