package com.moonlightbutterfly.rigplay.di

import com.moonlightbutterfly.rigplay.repository.GamesRepository
import com.moonlightbutterfly.rigplay.usecase.GetGameDetailsUseCase
import com.moonlightbutterfly.rigplay.usecase.GetGamesUseCase
import com.moonlightbutterfly.rigplay.usecase.IsGameLikedUseCase
import com.moonlightbutterfly.rigplay.usecase.LikeGameUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GamesRepository(get(), get()) }
    single { GetGamesUseCase(get<GamesRepository>()::getGames) }
    single { GetGameDetailsUseCase(get<GamesRepository>()::getGameDetails) }
    single { IsGameLikedUseCase(get<GamesRepository>()::isGameLiked) }
    single { LikeGameUseCase(get<GamesRepository>()::changeGameLike) }
}