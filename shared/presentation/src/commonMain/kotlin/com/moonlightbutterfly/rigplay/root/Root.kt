package com.moonlightbutterfly.rigplay.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsView
import com.moonlightbutterfly.rigplay.gamelist.view.GameListView

interface Root {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class GameList(val component: GameListView) : Child()
        data class GameDetails(val component: GameDetailsView) : Child()
    }
}
