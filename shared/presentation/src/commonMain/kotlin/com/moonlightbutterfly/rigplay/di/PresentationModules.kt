package com.moonlightbutterfly.rigplay.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.moonlightbutterfly.rigplay.Dispatchers
import com.moonlightbutterfly.rigplay.home.DispatchersImpl
import com.moonlightbutterfly.rigplay.home.GameListControllerFactory
import com.moonlightbutterfly.rigplay.home.GameListStoreFactory
import org.koin.dsl.module

val presentationModule = module {
    single<Dispatchers> { DispatchersImpl() }
    single<StoreFactory> { DefaultStoreFactory() }
    single {
        val dispatchers: Dispatchers = get()
        GameListStoreFactory(get(), get(), dispatchers.main, dispatchers.io).create()
    }
    single { GameListControllerFactory(get(), get()) }
}