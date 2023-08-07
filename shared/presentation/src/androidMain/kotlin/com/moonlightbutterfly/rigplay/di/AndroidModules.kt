package com.moonlightbutterfly.rigplay.di

import com.moonlightbutterfly.rigplay.gamedetails.stateholder.GameDetailsStateHolderFactory
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsViewFactory
import com.moonlightbutterfly.rigplay.gamelist.stateholder.GameListStateHolderFactory
import com.moonlightbutterfly.rigplay.gamelist.view.GameListViewFactory
import org.koin.dsl.module

val presentationAndroidModule = module {
    // GameList
    single { GameListStateHolderFactory() }
    single { GameListViewFactory() }

    //GameDetails
    single { GameDetailsStateHolderFactory() }
    single { GameDetailsViewFactory() }
}