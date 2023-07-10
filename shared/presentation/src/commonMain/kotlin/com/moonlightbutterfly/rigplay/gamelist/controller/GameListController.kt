package com.moonlightbutterfly.rigplay.gamelist.controller

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.moonlightbutterfly.rigplay.dispatchers.Dispatchers
import com.moonlightbutterfly.rigplay.gamelist.store.GameListStore
import com.moonlightbutterfly.rigplay.gamelist.view.GameListView
import com.moonlightbutterfly.rigplay.gamelist.eventToIntent
import com.moonlightbutterfly.rigplay.gamelist.stateToModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

internal class GameListController(
    lifecycle: Lifecycle,
    private val store: GameListStore,
    private val dispatchers: Dispatchers,
) {

    init {
        lifecycle.doOnDestroy(store::dispose)
    }

    fun onViewCreated(view: GameListView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP, dispatchers.unconfined) {
            view.events.mapNotNull(eventToIntent) bindTo store
            store.states.map(stateToModel) bindTo view
        }
    }
}