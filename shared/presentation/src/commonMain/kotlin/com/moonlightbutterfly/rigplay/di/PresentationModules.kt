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
        GameListStoreFactory(get(), get(), get<Dispatchers>().main, get<Dispatchers>().io).create()
    }

    // GameDetails
    single {
        GameDetailsStoreFactory(get(), get(), get(), get(), get<Dispatchers>().main, get<Dispatchers>().io)
    }

    // Root
    single<StoreFactory> { DefaultStoreFactory() }

    single {
        RootFactory(get(), get(), get(), get(), get(), get<Dispatchers>().main, get<Dispatchers>().io)
    }
}