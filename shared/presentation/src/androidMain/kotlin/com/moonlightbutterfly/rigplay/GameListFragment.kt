package com.moonlightbutterfly.rigplay

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
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.moonlightbutterfly.rigplay.data.repository.GamesDataSourceImpl
import com.moonlightbutterfly.rigplay.home.DispatchersImpl
import com.moonlightbutterfly.rigplay.home.GameListController
import com.moonlightbutterfly.rigplay.home.MainLayout
import com.moonlightbutterfly.rigplay.repository.GamesRepository

class GameListFragment : Fragment() {

    private lateinit var controller: GameListController
    private val stateHolder = GameListStateHolder(lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = GameListController(
            DefaultStoreFactory(),
            lifecycle.asEssentyLifecycle(),
            GamesRepository(GamesDataSourceImpl()),
            DispatchersImpl()
        )
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
        controller.onViewCreated(GameListViewImpl(stateHolder, lifecycleScope), lifecycle.asEssentyLifecycle())
    }
}