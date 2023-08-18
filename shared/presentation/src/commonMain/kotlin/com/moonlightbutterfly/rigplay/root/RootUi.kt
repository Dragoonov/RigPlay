@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.moonlightbutterfly.rigplay.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsMainView
import com.moonlightbutterfly.rigplay.gamelist.view.GameListMainView

@Composable
fun RootContent(component: Root) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is Root.Child.GameList -> GameListMainView(child.component)
            is Root.Child.GameDetails -> GameDetailsMainView(child.component)
        }
    }
}
