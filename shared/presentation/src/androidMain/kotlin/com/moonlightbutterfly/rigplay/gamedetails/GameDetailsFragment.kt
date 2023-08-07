package com.moonlightbutterfly.rigplay.gamedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsViewFactory
import com.moonlightbutterfly.rigplay.gamedetails.controller.GameDetailsController
import com.moonlightbutterfly.rigplay.gamedetails.controller.GameDetailsControllerFactory
import com.moonlightbutterfly.rigplay.gamedetails.stateholder.GameDetailsStateHolder
import com.moonlightbutterfly.rigplay.gamedetails.stateholder.GameDetailsStateHolderFactory
import com.moonlightbutterfly.rigplay.gamedetails.view.MainLayout
import org.koin.android.ext.android.inject

class GameDetailsFragment : Fragment() {

    private lateinit var controller: GameDetailsController
    private lateinit var stateHolder: GameDetailsStateHolder

    private val stateHolderFactory: GameDetailsStateHolderFactory by inject()
    private val gameDetailsControllerFactory: GameDetailsControllerFactory by inject()
    private val gameDetailsViewFactory: GameDetailsViewFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameId = arguments?.getInt(GAME_ID_KEY, 0) ?: 0
        controller = gameDetailsControllerFactory.create(lifecycle.asEssentyLifecycle(), gameId)
        stateHolder = stateHolderFactory.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val games by stateHolder.game.collectAsState()
                val isLoading by stateHolder.isLoading.collectAsState()
                MainLayout(games, isLoading)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameDetailsView = gameDetailsViewFactory.create(stateHolder, lifecycleScope)
        controller.onViewCreated(gameDetailsView, lifecycle.asEssentyLifecycle())
    }

    companion object {
        fun create(gameId: Int) = GameDetailsFragment().apply { arguments = bundleOf(GAME_ID_KEY to gameId) }
        private const val GAME_ID_KEY = "gameId"
    }
}