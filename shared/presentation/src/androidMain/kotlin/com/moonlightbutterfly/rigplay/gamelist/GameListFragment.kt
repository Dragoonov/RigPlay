package com.moonlightbutterfly.rigplay.gamelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.moonlightbutterfly.rigplay.gamelist.controller.GameListController
import com.moonlightbutterfly.rigplay.gamelist.controller.GameListControllerFactory
import com.moonlightbutterfly.rigplay.gamelist.stateholder.GameListStateHolder
import com.moonlightbutterfly.rigplay.gamelist.stateholder.GameListStateHolderFactory
import com.moonlightbutterfly.rigplay.gamelist.view.GameListViewFactory
import com.moonlightbutterfly.rigplay.gamelist.view.MainLayout
import org.koin.android.ext.android.inject

class GameListFragment : Fragment() {

    private lateinit var controller: GameListController
    private lateinit var stateHolder: GameListStateHolder

    private val stateHolderFactory: GameListStateHolderFactory by inject()
    private val gameListControllerFactory: GameListControllerFactory by inject()
    private val gameListViewFactory: GameListViewFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = gameListControllerFactory.create(lifecycle.asEssentyLifecycle())
        stateHolder = stateHolderFactory.create(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val games by stateHolder.games.collectAsState()
                val isLoading by stateHolder.isLoading.collectAsState()
                MainLayout(stateHolder::onRefreshClicked, games, isLoading)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameListView = gameListViewFactory.create(stateHolder, lifecycleScope)
        controller.onViewCreated(gameListView, lifecycle.asEssentyLifecycle())
    }
}