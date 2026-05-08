package com.beertracker.shared.network

import com.beertracker.shared.model.Harmony
import com.beertracker.shared.model.Style
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: String = ""
)

@Serializable
data class StyleResponse(
    val data: List<Style> = emptyList()
)

@Serializable
data class HarmonyResponse(
    val data: List<Harmony> = emptyList()
)
