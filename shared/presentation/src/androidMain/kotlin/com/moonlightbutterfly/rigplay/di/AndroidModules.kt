package com.moonlightbutterfly.rigplay.di

import com.moonlightbutterfly.rigplay.GameListStateHolderFactory
import com.moonlightbutterfly.rigplay.GameListViewFactory
import org.koin.dsl.module

val presentationAndroidModule = module {
    single { GameListStateHolderFactory() }
    single { GameListViewFactory() }
}