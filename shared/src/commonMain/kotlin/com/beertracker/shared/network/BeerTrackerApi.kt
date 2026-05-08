package com.beertracker.shared.network

import com.beertracker.shared.model.Harmony
import com.beertracker.shared.model.Style
import com.beertracker.shared.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class BeerTrackerApi {
    companion object {
        const val HOST = "http://10.0.2.2:5000/"
    }

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
    }

    suspend fun loginUser(user: User): String {
        val response = client.post("${HOST}login-user") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return response.body<UserResponse>().data
    }

    suspend fun registerUser(user: User): String {
        val response = client.post("${HOST}register-user") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return response.body<UserResponse>().data
    }

    suspend fun getAllStyles(): List<Style> {
        val response = client.get("${HOST}estilos/")
        return response.body<StyleResponse>().data
    }

    suspend fun getHarmonies(style: Style): List<Harmony> {
        val response = client.post("${HOST}harmonia-por-estilo/") {
            contentType(ContentType.Application.Json)
            setBody(style)
        }
        return response.body<HarmonyResponse>().data
    }
}
