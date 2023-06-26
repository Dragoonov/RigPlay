package com.moonlightbutterfly.rigplay

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform