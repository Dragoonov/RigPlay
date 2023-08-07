package com.moonlightbutterfly.rigplay.gamedetails.controller

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.moonlightbutterfly.rigplay.dispatchers.Dispatchers
import com.moonlightbutterfly.rigplay.gamedetails.eventToIntent
import com.moonlightbutterfly.rigplay.gamedetails.stateToModel
import com.moonlightbutterfly.rigplay.gamedetails.store.GameDetailsStore
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsView
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

internal class GameDetailsController(
    lifecycle: Lifecycle,
    private val store: GameDetailsStore,
    private val dispatchers: Dispatchers,
) {

    init {
        lifecycle.doOnDestroy(store::dispose)
    }

    fun onViewCreated(view: GameDetailsView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP, dispatchers.unconfined) {
            view.events.mapNotNull(eventToIntent) bindTo store
            store.states.map(stateToModel) bindTo view
        }
    }
}