package com.moonlightbutterfly.rigplay.gamedetails

import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem
import com.moonlightbutterfly.rigplay.gamedetails.store.GameDetailsStore
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsView

internal val stateToModel: (GameDetailsStore.State) -> GameDetailsView.Model =
    { state ->
        GameDetailsView.Model(
            isLoading = state.isLoading,
            isError = state.data is GameDetailsStore.State.Data.Error,
            gameDetails = (state.data as? GameDetailsStore.State.Data.GameDetails)?.details ?: GameDetailsItem.empty()
        )
    }