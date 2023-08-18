package com.moonlightbutterfly.rigplay.gamedetails.view

import com.arkivanov.decompose.value.Value
import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem

interface GameDetailsView {

    val models: Value<Model>

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val gameDetails: GameDetailsItem
    )
}
