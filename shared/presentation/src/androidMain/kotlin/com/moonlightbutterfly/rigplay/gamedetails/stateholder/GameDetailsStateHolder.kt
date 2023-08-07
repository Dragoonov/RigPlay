package com.moonlightbutterfly.rigplay.gamedetails.stateholder

import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem
import kotlinx.coroutines.flow.MutableStateFlow

class GameDetailsStateHolder {
    val game = MutableStateFlow(GameDetailsItem.empty())
    val isLoading = MutableStateFlow(true)
}