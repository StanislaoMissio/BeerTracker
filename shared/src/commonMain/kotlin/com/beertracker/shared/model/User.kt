package com.beertracker.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val usuario: String = "",
    val senha: String = "",
    val email: String = ""
)
