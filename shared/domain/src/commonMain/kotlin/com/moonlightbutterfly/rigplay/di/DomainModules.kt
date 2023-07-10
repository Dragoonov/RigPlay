package com.moonlightbutterfly.rigplay.di

import com.moonlightbutterfly.rigplay.repository.GamesRepository
import org.koin.dsl.module

val domainModule = module {
    single { GamesRepository(get()) }
}