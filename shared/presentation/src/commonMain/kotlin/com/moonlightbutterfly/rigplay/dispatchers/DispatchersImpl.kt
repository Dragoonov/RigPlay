package com.moonlightbutterfly.rigplay.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.IO

class DispatchersImpl: Dispatchers {
    override val main: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    override val io: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
    override val unconfined: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Unconfined
}