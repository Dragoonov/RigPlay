package com.moonlightbutterfly.rigplay.data.dto

import com.moonlightbutterfly.rigplay.model.Platform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformDTO(
    val id: Int,
    val name: String,
    @SerialName("image_background") val image: String,
)

fun PlatformDTO.toPlatform(): Platform = Platform(this.name, this.image)