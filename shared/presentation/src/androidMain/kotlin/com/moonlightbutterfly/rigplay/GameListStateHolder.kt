package com.moonlightbutterfly.rigplay

import com.moonlightbutterfly.rigplay.home.GameListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GameListStateHolder(private val scope: CoroutineScope) {
    val games = MutableStateFlow(emptyList<GameListItem>())
    val isLoading = MutableStateFlow(true)
    val refreshListener = MutableSharedFlow<Unit>()

    fun onRefreshClicked() {
        scope.launch {
            refreshListener.emit(Unit)
        }
    }
}