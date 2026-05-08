package com.beertracker.shared.repository

import com.beertracker.shared.model.User
import com.beertracker.shared.network.BeerTrackerApi

class AuthRepository(
    private val api: BeerTrackerApi = BeerTrackerApi()
) {
    suspend fun login(username: String, password: String): Result<String> {
        return try {
            val user = User(usuario = username, senha = password, email = username)
            val result = api.loginUser(user)
            if (result == "Sucess") {
                Result.success(result)
            } else {
                Result.failure(Exception("Login failed: $result"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(username: String, password: String, email: String): Result<String> {
        return try {
            val user = User(usuario = username, senha = password, email = email)
            val result = api.registerUser(user)
            if (result == "Sucess") {
                Result.success(result)
            } else {
                Result.failure(Exception("Registration failed: $result"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
