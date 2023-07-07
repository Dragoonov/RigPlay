package com.moonlightbutterfly.rigplay

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}