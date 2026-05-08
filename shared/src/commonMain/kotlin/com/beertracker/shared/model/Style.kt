package com.beertracker.shared.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Style(
    @SerialName("cod_estilo")
    val codEstilo: Long = 0,
    @SerialName("nome_estilo")
    val nomeEstilo: String = "",
    val descricao: String = "",
    val preco: Double = 0.0,
    val image: String = ""
)
