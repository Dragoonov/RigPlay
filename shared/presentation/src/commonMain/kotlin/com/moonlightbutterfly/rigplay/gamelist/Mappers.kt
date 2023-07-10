package com.moonlightbutterfly.rigplay.gamelist

import com.moonlightbutterfly.rigplay.gamelist.store.GameListStore
import com.moonlightbutterfly.rigplay.gamelist.view.GameListView

internal val stateToModel: (GameListStore.State) -> GameListView.Model =
    { state ->
        GameListView.Model(
            isLoading = state.isLoading,
            isError = state.data is GameListStore.State.Data.Error,
            games = (state.data as? GameListStore.State.Data.Games)?.games ?: emptyList()
        )
    }

internal val eventToIntent: (GameListView.Event) -> GameListStore.Intent? =
    { event ->
        when (event) {
            is GameListView.Event.RefreshTriggered -> GameListStore.Intent.Reload
        }
    }
