package com.beertracker.shared.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Harmony(
    @SerialName("cod_harmonia")
    val codHarmonia: Int = 0,
    @SerialName("nome_harmonia")
    val nomeHarmonia: String = "",
    val descricao: String = "",
    val imagem: String = ""
)
