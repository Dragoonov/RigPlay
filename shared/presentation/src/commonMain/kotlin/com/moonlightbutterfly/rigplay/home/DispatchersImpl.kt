package com.moonlightbutterfly.rigplay.home

import com.moonlightbutterfly.rigplay.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.IO

class DispatchersImpl: Dispatchers {
    override val main: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    override val io: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
    override val unconfined: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Unconfined
}