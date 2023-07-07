package com.moonlightbutterfly.rigplay.home

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
