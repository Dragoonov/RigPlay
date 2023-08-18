package com.moonlightbutterfly.rigplay.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.moonlightbutterfly.rigplay.dispatchers.Dispatchers
import com.moonlightbutterfly.rigplay.dispatchers.DispatchersImpl
import com.moonlightbutterfly.rigplay.gamedetails.store.GameDetailsStoreFactory
import com.moonlightbutterfly.rigplay.gamelist.store.GameListStoreFactory
import com.moonlightbutterfly.rigplay.root.RootFactory
import org.koin.dsl.module

val presentationModule = module {
    single<Dispatchers> { DispatchersImpl() }
    single<StoreFactory> { DefaultStoreFactory() }

    // GameList
    single {
        val dispatchers: Dispatchers = get()
        GameListStoreFactory(get(), get(), dispatchers.main, dispatchers.io).create()
    }

    // GameDetails
    single {
        val dispatchers: Dispatchers = get()
        GameDetailsStoreFactory(get(), get(), dispatchers.main, dispatchers.io)
    }

    // Root
    single<StoreFactory> { DefaultStoreFactory() }

    single {
        val dispatchers: Dispatchers = get()
        RootFactory(get(), get(), get(), dispatchers.main, dispatchers.io)
    }
}