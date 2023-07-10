package com.moonlightbutterfly.rigplay.di

import com.moonlightbutterfly.rigplay.gamelist.stateholder.GameListStateHolderFactory
import com.moonlightbutterfly.rigplay.gamelist.view.GameListViewFactory
import org.koin.dsl.module

val presentationAndroidModule = module {
    single { GameListStateHolderFactory() }
    single { GameListViewFactory() }
}