package com.moonlightbutterfly.rigplay.usecase

import com.moonlightbutterfly.rigplay.model.Game
import kotlinx.coroutines.flow.Flow

fun interface GetGamesUseCase : (Int) -> Flow<List<Game>>
fun interface GetGameDetailsUseCase : (Int) -> Flow<Game>
fun interface IsGameLikedUseCase: suspend (Int) -> Boolean
fun interface LikeGameUseCase: suspend (Boolean) -> Boolean