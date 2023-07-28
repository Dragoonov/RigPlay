package com.moonlightbutterfly.rigplay.data.dto

import com.moonlightbutterfly.rigplay.model.Developer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeveloperDTO(
    val id: Int,
    val name: String,
    @SerialName("image_background") val image: String,
)

fun DeveloperDTO.toDeveloper(): Developer = Developer(this.name, this.image)