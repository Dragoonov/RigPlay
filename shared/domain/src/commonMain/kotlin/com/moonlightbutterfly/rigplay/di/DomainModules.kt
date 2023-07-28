package com.moonlightbutterfly.rigplay.di

import com.moonlightbutterfly.rigplay.repository.GamesRepository
import com.moonlightbutterfly.rigplay.usecase.GetGameDetailsUseCase
import com.moonlightbutterfly.rigplay.usecase.GetGamesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GamesRepository(get()) }
    single {
        val repository: GamesRepository = get()
        GetGamesUseCase(repository::getGames)
    }
    single {
        val repository: GamesRepository = get()
        GetGameDetailsUseCase(repository::getGameDetails)
    }
}