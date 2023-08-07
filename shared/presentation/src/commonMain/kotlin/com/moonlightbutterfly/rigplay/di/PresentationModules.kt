package com.moonlightbutterfly.rigplay.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.moonlightbutterfly.rigplay.dispatchers.Dispatchers
import com.moonlightbutterfly.rigplay.dispatchers.DispatchersImpl
import com.moonlightbutterfly.rigplay.gamedetails.controller.GameDetailsControllerFactory
import com.moonlightbutterfly.rigplay.gamedetails.store.GameDetailsStoreFactory
import com.moonlightbutterfly.rigplay.gamelist.controller.GameListControllerFactory
import com.moonlightbutterfly.rigplay.gamelist.store.GameListStoreFactory
import org.koin.dsl.module

val presentationModule = module {
    single<Dispatchers> { DispatchersImpl() }
    single<StoreFactory> { DefaultStoreFactory() }

    // GameList
    single {
        val dispatchers: Dispatchers = get()
        GameListStoreFactory(get(), get(), dispatchers.main, dispatchers.io).create()
    }
    single { GameListControllerFactory(get(), get()) }

    // GameDetails
    single {
        val dispatchers: Dispatchers = get()
        GameDetailsStoreFactory(get(), get(), dispatchers.main, dispatchers.io)
    }
    single { GameDetailsControllerFactory(get(), get()) }
}